## spring是如何解决循环依赖问题

简单总结版(A类和B类循环引用)：

1.获取A类时，一级缓存singletonObjects获取不到，并且正在创建set（singletonsCurrentlyInCreation）中没有A类

2.从二级缓存（earlySingletonObjects）中获取，还是没有

3.三级缓存singletonFactories存放的都是继承了ObjectFactory的对象，有个getObject方法，通过lambda执行调用匿名函数中的createBean()

4.通过createBean() -->docreateBean()创建类(选择哪个构造函数的代码在这)，并将A放进三级缓存（同时从二级缓存删除）（如果mbd.isSingleton() && this.allowCircularReferences）

5.如果A需要代理，提前生成代理，后续把注入的bean换成代理Bean

5.然后populateBean填充属性B

6.实例化B，将B放入三级缓存

7.B类getSingleton(A)从三级缓存中获取到A的工厂，调用工厂的getObject()获取到A，然后把A从三级缓存删除，放到二级缓存

8创建B成功，返回A

8.A创建成功

三级缓存放的是生成A的

DefaultSingletonBeanRegistry有三个缓存map

```java
	/** Cache of singleton objects: bean name to bean instance. */
	//用于存放完全初始化好的 bean从该缓存中取出的 bean可以直接使用 一级缓存
	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

	/** Cache of singleton factories: bean name to ObjectFactory. */
	//存放 bean工厂对象解决循环依赖 三级缓存
	private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

	/** Cache of early singleton objects: bean name to bean instance. */
	//存放原始的bean对象用于解决循环依赖,注意：存到里面的对象还没有被填充属性 二级缓存
	private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);
	private final Set<String> singletonsCurrentlyInCreation =
			Collections.newSetFromMap(new ConcurrentHashMap<>(16));
```

假如A类有成员变量B b;B类有成员变量A a，属于循环依赖的情况

1.A类创建bean的时候调用getBean(a) --> doCreateBean(a)  --> populateBean(b) --> resolveDependency(b) --> doResovleDependency(b) --> resovleCandidate(b) --> getBean(b)

2.A类在调用getBean(a)方法的时候就通过DefaultSingletonBeanRegistry.getSingleton(a)将a

3.A类在doCreateBean(a)的时候就加入到singletonFactories

4.B类被A类依赖，开始初始化getBean(b) --> doCreateBean(b)  --> populateBean(a) --> resolveDependency(a) --> doResovleDependency(a) --> resovleCandidate() --> getBean(a)



### AbstractBeanFactory.doGetBean(a)中开始初始化A类

1.当A类开始初始化bean执行AbstranctBeanFactory.doGetBean(a),并第一次调用AbstractBeanFactory.getBean(a) --> DefaultSingletonBeanRegistry.getSingleton(String beanName, boolean allowEarlyReference) singletonObjects没有A，并且isSingletonCurrentlyInCreation(beanName)也是false，所以返回null

DefaultSingletonBeanRegistry.protected Object getSingleton(String beanName, boolean allowEarlyReference) 

```java
	/**
	 * Return the (raw) singleton object registered under the given name.
	 * <p>Checks already instantiated singletons and also allows for an early
	 * reference to a currently created singleton (resolving a circular reference).
	 * @param beanName the name of the bean to look for
	 * @param allowEarlyReference whether early references should be created or not
	 * @return the registered singleton object, or {@code null} if none found
	 */
	//本方法主要用来处理循环依赖
	/**
	 * allowEarlyReference参数的含义是Spring是否允许循环依赖，默认为true
	 * 所以当allowEarlyReference设置为false的时候，当项目存在循环依赖，会启动失败
	 */
	@Nullable
	protected Object getSingleton(String beanName, boolean allowEarlyReference) {
		//从map中获取bean如果不为空直接返回，不再进行初始化工作
		//本方法主要用于程序员在外部使用annotationConfigApplicationContext.getBean()的时候使用
		/**
		 * 1.按照代码的逻辑，我们第一次调用到这里的时候，记录正在创建的bean的set里面是空的，所以isSingletonCurrentlyInCreation(beanName)返回false
		 * 所以本次getSingleton返回null
		 * 2.第二次调用的是下面的getSingleton(String beanName, ObjectFactory<?> singletonFactory)方法
		 * spring已经做了该做的验证，如果为空则创建对象
		 */
		Object singletonObject = this.singletonObjects.get(beanName);
		//如果从singletonObjects取不到bean,并且正在创建当中
		if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
			synchronized (this.singletonObjects) {
				singletonObject = this.earlySingletonObjects.get(beanName);
				if (singletonObject == null && allowEarlyReference) {
					ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
					if (singletonFactory != null) {
						singletonObject = singletonFactory.getObject();
						this.earlySingletonObjects.put(beanName, singletonObject);
						this.singletonFactories.remove(beanName);
					}
				}
			}
		}
		return singletonObject;
	}
```

2.然后doGetBean中判断返回的sharedInstance为null，执行DefaultSingletonBeanRegistry.getSingleton(String beanName, ObjectFactory<?> singletonFactory)，注意两个getsingleton参数不一样。这时A的beanName通过beforeSingletonCreation(beanName);被放入**singletonsCurrentlyInCreation**这个set

```java
	public Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {
		Assert.notNull(beanName, "Bean name must not be null");
		synchronized (this.singletonObjects) {
			Object singletonObject = this.singletonObjects.get(beanName);
			if (singletonObject == null) {
				if (this.singletonsCurrentlyInDestruction) {
					throw new BeanCreationNotAllowedException(beanName,
							"Singleton bean creation not allowed while singletons of this factory are in destruction " +
							"(Do not request a bean from a BeanFactory in a destroy method implementation!)");
				}
				if (logger.isDebugEnabled()) {
					logger.debug("Creating shared instance of singleton bean '" + beanName + "'");
				}
				/**
				 * 将beanName添加到singletonsCurrentlyInCreation这样一个集合中
				 * 表示beanName对应的bean正在创建中
				 */
				beforeSingletonCreation(beanName);
				boolean newSingleton = false;
				boolean recordSuppressedExceptions = (this.suppressedExceptions == null);
				if (recordSuppressedExceptions) {
					this.suppressedExceptions = new LinkedHashSet<>();
				}
				try {
					//在这里创建bean 这里下一步是调用的是lambda表达式return createBean(beanName, mbd, args);
					singletonObject = singletonFactory.getObject();
					newSingleton = true;
				}
				catch (IllegalStateException ex) {
					// Has the singleton object implicitly appeared in the meantime ->
					// if yes, proceed with it since the exception indicates that state.
					singletonObject = this.singletonObjects.get(beanName);
					if (singletonObject == null) {
						throw ex;
					}
				}
				catch (BeanCreationException ex) {
					if (recordSuppressedExceptions) {
						for (Exception suppressedException : this.suppressedExceptions) {
							ex.addRelatedCause(suppressedException);
						}
					}
					throw ex;
				}
				finally {
					if (recordSuppressedExceptions) {
						this.suppressedExceptions = null;
					}
					afterSingletonCreation(beanName);
				}
				if (newSingleton) {
					addSingleton(beanName, singletonObject);
				}
			}
			return singletonObject;
		}
	}
```

3.继续执行doGetBean() --> AbstractAutowireCapableBeanFactory.createBean() --> AbstractAutowireCapableBeanFactory.doCreateBean()

在doCreateBean()当中通过createBeanInstance()方法反射创建对象

然后在isSingletonCurrentlyInCreation(beanName)方法中判断当前beanName是否被加到singletonsCurrentlyInCreation这个set当中，刚才第2步已经添加完了

```java
	protected Object doCreateBean(final String beanName, final RootBeanDefinition mbd, final @Nullable Object[] args)
			throws BeanCreationException {

		// Instantiate the bean.
		BeanWrapper instanceWrapper = null;
		if (mbd.isSingleton()) {
			instanceWrapper = this.factoryBeanInstanceCache.remove(beanName);
		}
		if (instanceWrapper == null) {
			/**
			 * 创建 bean 实例，并将实例包裹在 BeanWrapper 实现类对象中返回。
			 * createBeanInstance中包含三种创建 bean 实例的方式：
			 *   1. 通过工厂方法创建 bean 实例
			 *   2. 通过构造方法自动注入（autowire by constructor）的方式创建 bean 实例
			 *   3. 通过无参构造方法方法创建 bean 实例
			 *
			 * 若 bean 的配置信息中配置了 lookup-method 和 replace-method，则会使用 CGLIB
			 * 增强 bean 实例。关于lookup-method和replace-method后面再说。
			 */
			instanceWrapper = createBeanInstance(beanName, mbd, args);
		}
		final Object bean = instanceWrapper.getWrappedInstance();
		Class<?> beanType = instanceWrapper.getWrappedClass();
		if (beanType != NullBean.class) {
			mbd.resolvedTargetType = beanType;
		}

		// Allow post-processors to modify the merged bean definition.
		synchronized (mbd.postProcessingLock) {
			if (!mbd.postProcessed) {
				try {
					applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
				}
				catch (Throwable ex) {
					throw new BeanCreationException(mbd.getResourceDescription(), beanName,
							"Post-processing of merged bean definition failed", ex);
				}
				mbd.postProcessed = true;
			}
		}

		// Eagerly cache singletons to be able to resolve circular references
		// even when triggered by lifecycle interfaces like BeanFactoryAware.
		//判断
		boolean earlySingletonExposure = (mbd.isSingleton() && this.allowCircularReferences &&
				isSingletonCurrentlyInCreation(beanName));
		if (earlySingletonExposure) {
			if (logger.isTraceEnabled()) {
				logger.trace("Eagerly caching bean '" + beanName +
						"' to allow for resolving potential circular references");
			}
			//把bean从DefaultSingletonBeanRegistry.earlySingletonObjects中移除
			addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, mbd, bean));
		}

		// Initialize the bean instance.
		//这是时候还是原生的bean对象
		Object exposedObject = bean;
		try {
			//设置属性，非常重要
			populateBean(beanName, mbd, instanceWrapper);
			//执行后置处理器，aop就是在这里完成的处理!!!
			exposedObject = initializeBean(beanName, exposedObject, mbd);
		}
		catch (Throwable ex) {
			if (ex instanceof BeanCreationException && beanName.equals(((BeanCreationException) ex).getBeanName())) {
				throw (BeanCreationException) ex;
			}
			else {
				throw new BeanCreationException(
						mbd.getResourceDescription(), beanName, "Initialization of bean failed", ex);
			}
		}

        
		if (earlySingletonExposure) {
			Object earlySingletonReference = getSingleton(beanName, false);
			if (earlySingletonReference != null) {
				if (exposedObject == bean) {
					exposedObject = earlySingletonReference;
				}
				else if (!this.allowRawInjectionDespiteWrapping && hasDependentBean(beanName)) {
					String[] dependentBeans = getDependentBeans(beanName);
					Set<String> actualDependentBeans = new LinkedHashSet<>(dependentBeans.length);
					for (String dependentBean : dependentBeans) {
						if (!removeSingletonIfCreatedForTypeCheckOnly(dependentBean)) {
							actualDependentBeans.add(dependentBean);
						}
					}
					if (!actualDependentBeans.isEmpty()) {
						throw new BeanCurrentlyInCreationException(beanName,
								"Bean with name '" + beanName + "' has been injected into other beans [" +
								StringUtils.collectionToCommaDelimitedString(actualDependentBeans) +
								"] in its raw version as part of a circular reference, but has eventually been " +
								"wrapped. This means that said other beans do not use the final version of the " +
								"bean. This is often the result of over-eager type matching - consider using " +
								"'getBeanNamesOfType' with the 'allowEagerInit' flag turned off, for example.");
					}
				}
			}
		}

		// Register bean as disposable.
		try {
			registerDisposableBeanIfNecessary(beanName, bean, mbd);
		}
		catch (BeanDefinitionValidationException ex) {
			throw new BeanCreationException(
					mbd.getResourceDescription(), beanName, "Invalid destruction signature", ex);
		}

		return exposedObject;
	}
```

4.往三级缓存singletonFactories添加beanName数据，并移除二级缓存earlySingletonObjects中的beanName



```java
	protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
		Assert.notNull(singletonFactory, "Singleton factory must not be null");
		synchronized (this.singletonObjects) {
			if (!this.singletonObjects.containsKey(beanName)) {
				this.singletonFactories.put(beanName, singletonFactory);
				//把bean从earlySingletonObjects移除
				this.earlySingletonObjects.remove(beanName);
				this.registeredSingletons.add(beanName);
			}
		}
	}
```

ObjectFactory是一个接口，只有一个方法getObject ，符合lambda表达式的，下面的代码就是实现这个接口，实际上是调用@EnableAspectJAutoProxy注解导入的AnnotationAwareAspectJAutoProxyCreator。可以直接理解为返回了一个A的对象

```java
	protected Object getEarlyBeanReference(String beanName, RootBeanDefinition mbd, Object bean) {
		Object exposedObject = bean;
		if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
			for (BeanPostProcessor bp : getBeanPostProcessors()) {
				if (bp instanceof SmartInstantiationAwareBeanPostProcessor) {
					SmartInstantiationAwareBeanPostProcessor ibp = (SmartInstantiationAwareBeanPostProcessor) bp;
					exposedObject = ibp.getEarlyBeanReference(exposedObject, beanName);
				}
			}
		}
		return exposedObject;
	}
```

5.生成对象以后通过AbstractAutowireCapableBeanFactory.populateBean填充对象属性

我们从populateBean()方法的autowireByType()方法去看。这时候需要填充A的属性，发现需要B类，调用DefaultLisatableBeanFactory.resolveDependency(b) --> getBean(b)初始化B类

6.当B类填充属性A时，再次调用getSingleton(String beanName, boolean allowEarlyReference)

这一次调用getSingleton逻辑就不一样了，singletonObject一级缓存中还是获取不到，二级缓存earlySingletonObjects也获取不到，然后从三级缓存中能获取到上面第4步放到singletonFactories中的未完全初始化的a对象。

删除三级缓存的数据，将a对象放到二级缓存，返回a对象

```java
	//本方法主要用来处理循环依赖
	/**
	 * allowEarlyReference参数的含义是Spring是否允许循环依赖，默认为true
	 * 所以当allowEarlyReference设置为false的时候，当项目存在循环依赖，会启动失败
	 */
	@Nullable
	protected Object getSingleton(String beanName, boolean allowEarlyReference) {
		//从map中获取bean如果不为空直接返回，不再进行初始化工作
		//本方法主要用于程序员在外部使用annotationConfigApplicationContext.getBean()的时候使用
		/**
		 * 1.按照代码的逻辑，我们第一次调用到这里的时候，记录正在创建的bean的set里面是空的，所以isSingletonCurrentlyInCreation(beanName)返回false
		 * 所以本次getSingleton返回null
		 * 2.第二次调用的是下面的getSingleton(String beanName, ObjectFactory<?> singletonFactory)方法
		 * spring已经做了该做的验证，如果为空则创建对象
		 */
		Object singletonObject = this.singletonObjects.get(beanName);
		//如果从singletonObjects取不到bean,并且正在创建当中
		if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
			synchronized (this.singletonObjects) {
				singletonObject = this.earlySingletonObjects.get(beanName);
				if (singletonObject == null && allowEarlyReference) {
					ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
					if (singletonFactory != null) {
						singletonObject = singletonFactory.getObject();
						this.earlySingletonObjects.put(beanName, singletonObject);
						this.singletonFactories.remove(beanName);
					}
				}
			}
		}
		return singletonObject;
	}
```

https://www.jianshu.com/p/16a44c25c9d9

https://javazhiyin.blog.csdn.net/article/details/107602783

通俗易懂的三级缓存解释循环依赖https://blog.csdn.net/f641385712/article/details/92801300

循环依赖代理提前暴露讲解https://blog.csdn.net/u012098021/article/details/107352463