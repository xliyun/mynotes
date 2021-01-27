# Spring使用构造函数初始化bean

## 把所有准备初始化的bean准备好

### 1.bean初始化的时机

Bean初始化是从AnnotationConfigApplicationContext的refresh()函数调用的时候开始。

```java
annotationConfigApplicationContext.refresh();
```

### 2.开始对bean进行初始化

```
AbstractApplicationContext.finishBeanFactoryInitialization中执行到beanFactory.preInstantiateSingletons();
```

3.preInstantiateSingletons()方法默认是使用DefaultListableBeanFactory的，这个方法里面把所有扫描出来的beanName拿出来，然后开始初始化所有非静态、单例、非懒加载的bean，主要就是看getBean()方法

下面代码的重点是

- 拿出扫描出来的所有beanName
- 将非抽象、非单例、非懒加载的bean进行初始化

```java
	//准备实现单例
	@Override
	public void preInstantiateSingletons() throws BeansException {
		if (logger.isTraceEnabled()) {
			logger.trace("Pre-instantiating singletons in " + this);
		}
		// 1.创建beanDefinitionNames的副本beanNames用于后续的遍历，以允许init等方法注册新的bean定义
		List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);

		//触发所有非延迟加载单例beans的初始化，主要步骤为调用getBean
		for (String beanName : beanNames) {
			//合并父类的bd，在xml配置中用
			RootBeanDefinition bd = getMergedLocalBeanDefinition(beanName);
			//如果不是抽象 && 是单例 && 不是懒加载的
			if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) {
				if (isFactoryBean(beanName)) {
					Object bean = getBean(FACTORY_BEAN_PREFIX + beanName);
					//判断是不是FactoryBean
					if (bean instanceof FactoryBean) {
						final FactoryBean<?> factory = (FactoryBean<?>) bean;
						boolean isEagerInit;
						if (System.getSecurityManager() != null && factory instanceof SmartFactoryBean) {
							isEagerInit = AccessController.doPrivileged((PrivilegedAction<Boolean>)
											((SmartFactoryBean<?>) factory)::isEagerInit,
									getAccessControlContext());
						}
						else {
							isEagerInit = (factory instanceof SmartFactoryBean &&
									((SmartFactoryBean<?>) factory).isEagerInit());
						}
						if (isEagerInit) {
							getBean(beanName);
						}
					}
				}
                //我们的普通类都是走这里
				else {
					getBean(beanName);
				}
			}
		}
		// Trigger post-initialization callback for all applicable beans...
		for (String beanName : beanNames) {
			Object singletonInstance = getSingleton(beanName);
			if (singletonInstance instanceof SmartInitializingSingleton) {
				final SmartInitializingSingleton smartSingleton = (SmartInitializingSingleton) singletonInstance;
				if (System.getSecurityManager() != null) {
					AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
						smartSingleton.afterSingletonsInstantiated();
						return null;
					}, getAccessControlContext());
				}
				else {
					smartSingleton.afterSingletonsInstantiated();
				}
			}
		}
	}		
```

getBean()里面调用dogetBean()方法，这里就是正式 开始初始化

调用链是：



## doGetBean方法（bean真正创建地方）

先创建一个空白的bean对象，

然后

```
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

### 1.createBeanInstance创建一个空的bean对象

1. 如果bean有factory-method，就用factory-method初始化bean

2. 如果对象已经被创建过（比如这个对象是prototype类型的），工厂里就会保存有创建过的构造函数和参数，可以直接根据缓存的构造函数和参数

   - 无参构造函数instantiateBean创建被BeanWrapper包裹的bean

   - 有参构造函数autowireConstructor

3. 如果是第一次初始化bean，由后置处理器决定要用哪些构造方法

```java
	protected BeanWrapper createBeanInstance(String beanName, RootBeanDefinition mbd, @Nullable Object[] args) {
		// Make sure bean class is actually resolved at this point.
		Class<?> beanClass = resolveBeanClass(mbd, beanName);

		/**
		 * 检测一个类的访问权限spring默认情况下对于非public的类是允许访问的。
		 */
		if (beanClass != null && !Modifier.isPublic(beanClass.getModifiers()) && !mbd.isNonPublicAccessAllowed()) {
			throw new BeanCreationException(mbd.getResourceDescription(), beanName,
					"Bean class isn't public, and non-public access not allowed: " + beanClass.getName());
		}

		//跳过
		Supplier<?> instanceSupplier = mbd.getInstanceSupplier();
		if (instanceSupplier != null) {
			return obtainFromSupplier(instanceSupplier, beanName);
		}

		/**
		 *
		 * 如果工厂方法不为空，则通过工厂方法构建 bean 对象
		 * 这种构建 bean 的方式可以自己写个demo去试试
		 * 源码就不做深入分析了，有兴趣的同学可以和我私下讨论
		 */
		if (mbd.getFactoryMethodName() != null) {
			return instantiateUsingFactoryMethod(beanName, mbd, args);
		}

		// Shortcut when re-creating the same bean...
		/**
		 * 从spring的原始注释可以知道这个是一个Shortcut，什么意思呢？
		 * 当多次构建同一个 bean 时，可以使用这个Shortcut，
		 * 也就是说不在需要次推断应该使用哪种方式构造bean
		 *  比如在多次构建同一个prototype类型的 bean 时，就可以走此处的hortcut
		 * 这里的 resolved 和 mbd.constructorArgumentsResolved 将会在 bean 第一次实例
		 * 化的过程中被设置，后面来证明
		 */
		boolean resolved = false;
		boolean autowireNecessary = false;
		if (args == null) {
			synchronized (mbd.constructorArgumentLock) {
				//如果bd是由factoryMethod创建
				if (mbd.resolvedConstructorOrFactoryMethod != null) {
					resolved = true;
					//如果已经解析了构造方法的参数，则必须要通过一个带参构造方法来实例
					autowireNecessary = mbd.constructorArgumentsResolved;
				}
			}
		}
		//如果是factoryMethod创建的方法
		if (resolved) {
			if (autowireNecessary) {
				//有参
				// 通过构造方法自动装配的方式构造 bean 对象
				return autowireConstructor(beanName, mbd, null, null);
			}
			else {
				//通过默认的无参构造方法进行
				return instantiateBean(beanName, mbd);
			}
		}

		/**
		 * 下面的逻辑是，如果只有默认的无参构造方法，ctors是null 直接使用默认的无参构造方法进行初始化
		 * 如果有有参构造方法，
		 */
		// Candidate constructors for autowiring?
		//由后置处理器决定返回哪些构造方法
		Constructor<?>[] ctors = determineConstructorsFromBeanPostProcessors(beanClass, beanName);
		//getResolvedAutowireMode()获取spring自动装配的模型
		/**
		 * spring 自动装配的模型 != 自动装配的技术
		 * 比如这里，getResolvedAutowireMode()返回0，AUTOWIRE_NO，默认是通过类型自动装配
		 * 但是和byType不是一回事
		 *
		 */
		if (ctors != null || mbd.getResolvedAutowireMode() == AUTOWIRE_CONSTRUCTOR ||
				mbd.hasConstructorArgumentValues() || !ObjectUtils.isEmpty(args)) {
			return autowireConstructor(beanName, mbd, ctors, args);
		}

		// Preferred constructors for default construction?
		ctors = mbd.getPreferredConstructors();
		if (ctors != null) {
			return autowireConstructor(beanName, mbd, ctors, null);
		}

		// No special handling: simply use no-arg constructor.
		//使用默认的无参构造方法进行初始化
		return instantiateBean(beanName, mbd);
	}
```

### 2.通过后置处理器的determineConstructorsFromBeanPostProcessors方法决定使用哪些构造函数

AutowiredAnnotationBeanPostProcessor后置处理器通过determineCandidateConstructors方法决定使用哪些构造方法

candidateConstructors --最终要返回的构造函数，如果缓存中有就直接返回
candidates --最终适用的构造函数
requiredConstructor --带有required=true的构造函数
defaultConstructor --默认构造函数

先遍历一遍遍历所有的构造函数

1.如果当前构造函数有注解

​	a.requiredConstructor不为空，说明前面已经存在过一个requred=true的构造函数，抛异常

​	b.当前构造函数是required=true的，判断candidates是否为空，为空的话说明前面已经有过加注解的构造函数，抛异常

​    c.前面过滤条件都满足，将当前requird=true的构造函数赋值给requiredConstructor

​	d.前面没有带注解的构造函数，或者当前构造函数required=true条件满足，将当前构造函数加入candidates

​     e.如果加了注解的构造函数中没有required=true的构造函数，将无参构造函数加入到candidateConstructors

 2.如果当前构造函数没有注解并且参数是0个，将当前的无参构造函数赋值给defaultConstructor

​	a.在构造函数只有一个且有参时，将此唯一有参构造函数加入candidates

​    b.在构造函数有两个的时候，并且存在无参构造函数，将defaultConstructo(上面的无参构造函数)加入candidates

​	c.在构造器数量大于两个，并且存在无参构造器的情况下，将返回一个空的candidateConstructors集合，也就是没有找到构造器。

```java
	@Override
	@Nullable
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, final String beanName)
			throws BeanCreationException {

		// Let's check for lookup methods here...
		if (!this.lookupMethodsChecked.contains(beanName)) {
			try {
				ReflectionUtils.doWithMethods(beanClass, method -> {
					Lookup lookup = method.getAnnotation(Lookup.class);
					if (lookup != null) {
						Assert.state(this.beanFactory != null, "No BeanFactory available");
						LookupOverride override = new LookupOverride(method, lookup.value());
						try {
							RootBeanDefinition mbd = (RootBeanDefinition)
									this.beanFactory.getMergedBeanDefinition(beanName);
							mbd.getMethodOverrides().addOverride(override);
						}
						catch (NoSuchBeanDefinitionException ex) {
							throw new BeanCreationException(beanName,
									"Cannot apply @Lookup to beans without corresponding bean definition");
						}
					}
				});
			}
			catch (IllegalStateException ex) {
				throw new BeanCreationException(beanName, "Lookup method resolution failed", ex);
			}
			this.lookupMethodsChecked.add(beanName);
		}

		// Quick check on the concurrent map first, with minimal locking.
		//从构造方法的缓存当中去拿一个
		Constructor<?>[] candidateConstructors = this.candidateConstructorsCache.get(beanClass);
		//如果缓存中没有
		if (candidateConstructors == null) {
			// Fully synchronized resolution now...
			synchronized (this.candidateConstructorsCache) {
				candidateConstructors = this.candidateConstructorsCache.get(beanClass);
				//双重判断，避免多线程并发问题
				if (candidateConstructors == null) {
					Constructor<?>[] rawCandidates;
					try {
						//获取所有的构造函数
						rawCandidates = beanClass.getDeclaredConstructors();
					}
					catch (Throwable ex) {
						throw new BeanCreationException(beanName,
								"Resolution of declared constructors on bean Class [" + beanClass.getName() +
								"] from ClassLoader [" + beanClass.getClassLoader() + "] failed", ex);
					}
					//最终适用的构造器集合（加了注解的）
					List<Constructor<?>> candidates = new ArrayList<>(rawCandidates.length);
					//存放依赖注入的required=true的构造器
					Constructor<?> requiredConstructor = null;
					//存放默认构造器
					Constructor<?> defaultConstructor = null;
					//根据前面primaryConstructor 表示kotlin形式,,不管是什么构造方法，先执行init{初始化逻辑}，后执行构造方法
					Constructor<?> primaryConstructor = BeanUtils.findPrimaryConstructor(beanClass);
					int nonSyntheticConstructors = 0;
					//循环拿出来的所有构造方法
					for (Constructor<?> candidate : rawCandidates) {
						if (!candidate.isSynthetic()) {
							nonSyntheticConstructors++;
						}
						else if (primaryConstructor != null) {
							continue;
						}
						//查找当前构造器上的@AutoWired @Value注解
						AnnotationAttributes ann = findAutowiredAnnotation(candidate);
						//如果没有注解，再看看父类的注解
						if (ann == null) {
							Class<?> userClass = ClassUtils.getUserClass(beanClass);
							if (userClass != beanClass) {
								try {
									Constructor<?> superCtor =
											//根据当前遍历的构造函数，获取构造函数信息
											userClass.getDeclaredConstructor(candidate.getParameterTypes());
									ann = findAutowiredAnnotation(superCtor);
								}
								catch (NoSuchMethodException ex) {
									// Simply proceed, no equivalent superclass constructor found...
								}
							}
						}
						/**
						 * 1.如果当前构造函数有注解，
						 *   a.requiredConstructor不为空，说明前面已经存在过一个requred=true的构造函数，抛异常(就是不能存在两个required=true或者 一个required=true和一个required=false)
						 * 	 b.当前构造函数是required=true的，判断candidates是否为空，为空的话说明前面已经有过加注解的构造函数，抛异常
						 * 	 c.前面过滤条件都满足，将当前requird=true的构造函数赋值给requiredConstructor
						 * 	 d.前面没有带注解的构造函数，或者当前构造函数required=true条件满足，将当前构造函数加入candidates
						 * 2.如果当前构造函数没有注解并且参数是0个，将当前的无参构造函数赋值给defaultConstructor
						 */
						//若有注解
						if (ann != null) {
							//已经存在一个required=true的构造器了，抛出异常，一个类只允许一个带有required=true注解的构造函数
							if (requiredConstructor != null) {
								throw new BeanCreationException(beanName,
										"Invalid autowire-marked constructor: " + candidate +
										". Found constructor with 'required' Autowired annotation already: " +
										requiredConstructor);
							}
							//判断此注解上的required属性
							boolean required = determineRequiredStatus(ann);
							if (required) {
								if (!candidates.isEmpty()) {
									throw new BeanCreationException(beanName,
											"Invalid autowire-marked constructors: " + candidates +
											". Found constructor with 'required' Autowired annotation: " +
											candidate);
								}
								requiredConstructor = candidate;
							}
							//如果当前构造函数加了注解，就会先放到candidates当中，就是@AutoWired(requred=false)或者@AutoWired(required=true)都会加入进去
							candidates.add(candidate);
						}
						//若没有注解，再判断构造函数上的参数个数是否为0
						//当某个构造函数的参数是0个，也就是无参构造函数，赋值给defaultConstructor
						else if (candidate.getParameterCount() == 0) {
							defaultConstructor = candidate;
						}
					}
					/**
					 * 将所有加了@AutoWired
					 *
					 */
					//===任一构造函数有注解的情况
					//构造函数头上有注解（此构造函数有注解），candidates放的是有注解的情况
					//这里最终的逻辑是，如果有requried=true的构造函数，返回requried = true的构造函数，如果没有required=true的函数，有无参构造函数，返回无参构造函数
					if (!candidates.isEmpty()) {
						// Add default constructor to list of optional constructors, as fallback.
						//若没有required=true的构造器
						if (requiredConstructor == null) {
							if (defaultConstructor != null) {
								//将defaultConstructor集合的构造器加入适用构造器集合
								candidates.add(defaultConstructor);
							}
							//构造函数有注解，但是不是requred=true注解
							else if (candidates.size() == 1 && logger.isInfoEnabled()) {
								logger.info("Inconsistent constructor declaration on bean with name '" + beanName +
										"': single autowire-marked constructor flagged as optional - " +
										"this constructor is effectively required since there is no " +
										"default constructor to fall back to: " + candidates.get(0));
							}
						}
						//将适用构造器集合赋值给将要返回的构造器集合
						//如果有required=true的构造函数直接加入，或者没有required=true,有默认构造函数
						candidateConstructors = candidates.toArray(new Constructor<?>[0]);
					}
					//===剩下的是没有注解的情况
					//如果bean的构造函数只有一个，并且这个构造函数的参数大于0
					else if (rawCandidates.length == 1 && rawCandidates[0].getParameterCount() > 0) {
						candidateConstructors = new Constructor<?>[] {rawCandidates[0]};
					}
					//当bean的构造函数有两个 &&  提供了主要的构造函数 && 默认构造函数不为空 && 主要构造函数不等于默认构造方法
					else if (nonSyntheticConstructors == 2 && primaryConstructor != null &&
							defaultConstructor != null && !primaryConstructor.equals(defaultConstructor)) {
						candidateConstructors = new Constructor<?>[] {primaryConstructor, defaultConstructor};
					}
					//构造
					else if (nonSyntheticConstructors == 1 && primaryConstructor != null) {
						candidateConstructors = new Constructor<?>[] {primaryConstructor};
					}
					else {
						//上述条件都不符合的话，返回空集合
						candidateConstructors = new Constructor<?>[0];
					}
					//放入缓存，方便下一次调用
					this.candidateConstructorsCache.put(beanClass, candidateConstructors);
				}
			}
		}
		return (candidateConstructors.length > 0 ? candidateConstructors : null);
	}
```

### 3.autowireConstructor方法上文筛选的构造函数实例化一个对象

```java
	/**
	 * "autowire constructor" (with constructor arguments by type) behavior.
	 * Also applied if explicit constructor argument values are specified,
	 * matching all remaining arguments with beans from the bean factory.
	 * <p>This corresponds to constructor injection: In this mode, a Spring
	 * bean factory is able to host components that expect constructor-based
	 * dependency resolution.
	 * @param beanName the name of the bean
	 * @param mbd the merged bean definition for the bean
	 * @param chosenCtors chosen candidate constructors (or {@code null} if none)
	 * @param explicitArgs argument values passed in programmatically via the getBean method,
	 * or {@code null} if none (-> use constructor argument values from bean definition)
	 * @return a BeanWrapper for the new instance
	 */
	/**
	 * 我们要通过构造函数反射一个类，就需要
	 * Constructor 构造函数
	 * parameterType 参数类型
	 * parameterValue 参数值
	 */
	public BeanWrapper autowireConstructor(String beanName, RootBeanDefinition mbd,
			@Nullable Constructor<?>[] chosenCtors, @Nullable Object[] explicitArgs) {
		//实例一个BeanWrapperImpl 对象很好理解
		//前面外部返回的BeanWrapper 其实就是这个BeanWrapperImpl
		//因为BeanWrapper是个接口
		BeanWrapperImpl bw = new BeanWrapperImpl();//BeanWrapperImpl有一个WrapperObject的属性，用来存放真是对象，运行到这里的时候还没有被new出来
		//初始化父类的值，没有什么大的看头
		this.beanFactory.initBeanWrapper(bw);

		//这个变量定义你要使用哪个构造方法初始化对象
		Constructor<?> constructorToUse = null;
		//上面那行的构造方法需要哪些参数，
		ArgumentsHolder argsHolderToUse = null;
		Object[] argsToUse = null;

		//确定参数值列表
		//argsToUse可以有两种办法设置
		//第一种通过beanDefinition设置，mybatis通过setArugmentCounstructValues
		//第二种通过xml设置
		if (explicitArgs != null) {
			argsToUse = explicitArgs;
		}
		else {
			//
			Object[] argsToResolve = null;
			synchronized (mbd.constructorArgumentLock) {
				//获取已解析的构造方法
				//一般不会有，因为构造方法一般会提供一个
				//除非有多个。那么才会存在已经解析完成的构造方法
				constructorToUse = (Constructor<?>) mbd.resolvedConstructorOrFactoryMethod;
				if (constructorToUse != null && mbd.constructorArgumentsResolved) {
					// Found a cached constructor...
					argsToUse = mbd.resolvedConstructorArguments;
					if (argsToUse == null) {
						argsToResolve = mbd.preparedConstructorArguments;
					}
				}
			}
			//argsToResolve 参数解析器
			if (argsToResolve != null) {
				argsToUse = resolvePreparedArguments(beanName, mbd, bw, constructorToUse, argsToResolve, true);
			}
		}
		//如果不存在已解析的构造方法或者 需要的参数
		if (constructorToUse == null || argsToUse == null) {
			// Take specified constructors, if any.
			//如果没有已经解析的构造方法
			//则需要去解析构造方法
			// Need to resolve the constructor.
			//判断构造方法是否为空，判断是否根据构造方法自动注入
			Constructor<?>[] candidates = chosenCtors;
			if (candidates == null) {
				Class<?> beanClass = mbd.getBeanClass();
				try {
					candidates = (mbd.isNonPublicAccessAllowed() ?
							beanClass.getDeclaredConstructors() : beanClass.getConstructors());
				}
				catch (Throwable ex) {
					throw new BeanCreationException(mbd.getResourceDescription(), beanName,
							"Resolution of declared constructors on bean Class [" + beanClass.getName() +
							"] from ClassLoader [" + beanClass.getClassLoader() + "] failed", ex);
				}
			}

			if (candidates.length == 1 && explicitArgs == null && !mbd.hasConstructorArgumentValues()) {
				Constructor<?> uniqueCandidate = candidates[0];
				if (uniqueCandidate.getParameterCount() == 0) {
					synchronized (mbd.constructorArgumentLock) {
						mbd.resolvedConstructorOrFactoryMethod = uniqueCandidate;
						mbd.constructorArgumentsResolved = true;
						mbd.resolvedConstructorArguments = EMPTY_ARGS;
					}
					bw.setBeanInstance(instantiate(beanName, mbd, uniqueCandidate, EMPTY_ARGS));
					return bw;
				}
			}

			// Need to resolve the constructor.
			boolean autowiring = (chosenCtors != null ||
					mbd.getResolvedAutowireMode() == AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR);
			ConstructorArgumentValues resolvedValues = null;

			//定义了最小参数个数
			//如果你给构造方法的参数列表给定了具体的值
			//那么这些值得个数就是构造方法参数的个数
			int minNrOfArgs;
			if (explicitArgs != null) {
				minNrOfArgs = explicitArgs.length;
			}
			else {
				//实例一个对象，用来存放构造方法的参数值
				//当中主要存放了参数值和参数值所对应的下表
				//获得的是通过mbd.getConstructorArgumentValues().addGenericArgumentValue("com.xliyun.spring.springioc.dao.IndexDao");设置的构造函数必须有的参数类型
				ConstructorArgumentValues cargs = mbd.getConstructorArgumentValues();
				//ConstrucorArgumentValues是存放构造方法的值的，有两种数据结构list(无序的参数) map(有序的参数)
				//xml配置的bean里面构造函数<construct-arg index="0" value=""></construct-arg>
				resolvedValues = new ConstructorArgumentValues();
				/**
				 * 确定构造方法参数数量,假设有如下配置：
				 *     <bean id="luban" class="com.luban.Luban">
				 *         <constructor-arg index="0" value="str1"/>
				 *         <constructor-arg index="1" value="1"/>
				 *         <constructor-arg index="2" value="str2"/>
				 *     </bean>
				 *
				 *     在通过spring内部给了一个值得情况那么表示你的构造方法的最小参数个数一定
				 *
				 * minNrOfArgs = 3
				 */
				minNrOfArgs = resolveConstructorArguments(beanName, mbd, bw, cargs, resolvedValues);
			}

			//根据构造方法的访问权限级别和参数数量进行排序
			//怎么排序的呢？
			/**
			 *  有限反问权限，继而参数个数
			 *  这个自己可以写个测试去看看到底是不是和我说的一样
			 * 1. public Luban(Object o1, Object o2, Object o3)
			 * 2. public Luban(Object o1, Object o2)
			 * 3. public Luban(Object o1)
			 * 4. protected Luban(Integer i, Object o1, Object o2, Object o3)
			 * 5. protected Luban(Integer i, Object o1, Object o2)
			 * 6. protected Luban(Integer i, Object o1)
			 */
			AutowireUtils.sortConstructors(candidates);
			//定义了一个差异变量，这个变量很有分量，后面有注释
			int minTypeDiffWeight = Integer.MAX_VALUE;
			Set<Constructor<?>> ambiguousConstructors = null;
			LinkedList<UnsatisfiedDependencyException> causes = null;

			//循环所有的构造方法
			for (Constructor<?> candidate : candidates) {
				Class<?>[] paramTypes = candidate.getParameterTypes();

				/**
				 * 这个判断别看只有一行代码理解起来很费劲
				 * 首先constructorToUse != null这个很好理解，e
				 * 前面已经说过首先constructorToUse主要是用来装已经解析过了并且在使用的构造方法
				 * 只有在他等于空的情况下，才有继续的意义，因为下面如果解析到了一个符合的构造方法
				 * 就会赋值给这个变量（下面注释有写）。故而如果这个变量不等于null就不需要再进行解析了，说明spring已经
				 * 找到一个合适的构造方法，直接使用便可以
				 * argsToUse.length > paramTypes.length这个代码就相当复杂了
				 * 首先假设 argsToUse = [1,"luban"，obj]
				 * 那么回去匹配到上面的构造方法的1和5
				 * 由于构造方法1有更高的访问权限，所有选择1，尽管5看起来更加匹配
				 * 但是我们看2,直接参数个数就不对所以直接忽略
				 *
				 *
				 */
				if (constructorToUse != null && argsToUse != null && argsToUse.length > paramTypes.length) {
					// Already found greedy constructor that can be satisfied ->
					// do not look any further, there are only less greedy constructors left.
					break;
				}
				if (paramTypes.length < minNrOfArgs) {
					continue;
				}

				ArgumentsHolder argsHolder;
				if (resolvedValues != null) {
					try {
						//判断是否加了ConstructorProperties注解如果加了则把值取出来
						//可以写个代码测试一下
						//@ConstructorProperties(value = {"xxx", "111"})
						String[] paramNames = ConstructorPropertiesChecker.evaluate(candidate, paramTypes.length);
						if (paramNames == null) {
							ParameterNameDiscoverer pnd = this.beanFactory.getParameterNameDiscoverer();
							if (pnd != null) {
								//获取构造方法参数名称列表
								/**
								 * 假设你有一个（String xiaoliyu,Object zilu）
								 * 则paramNames=[luban,xiaoliyu]
								 */
								paramNames = pnd.getParameterNames(candidate);
							}
						}
						//获取构造方法参数值列表
						/**
						 * 这个方法比较复杂
						 * 因为spring只能提供字符串的参数值，比如我们配置
						 * paramNames是参数的名字
						 *                  <bean id="luban" class="com.luban.Luban">
						 * 				          <constructor-arg index="0" value="另一个bean的id,但是填在这里的是字符串，需要通过createArgumentArray转换"/>
						 * 				     </bean>
						 * 				     或者
						 * 				     public void indexDao(String xiaoliyu)
						 * 故而需要进行转换
						 * argsHolder所包含的值就是xiaoliyun转换之后的对象
						 */
						argsHolder = createArgumentArray(beanName, mbd, resolvedValues, bw, paramTypes, paramNames,
								getUserDeclaredConstructor(candidate), autowiring, candidates.length == 1);
					}
					catch (UnsatisfiedDependencyException ex) {
						if (logger.isTraceEnabled()) {
							logger.trace("Ignoring constructor [" + candidate + "] of bean '" + beanName + "': " + ex);
						}
						// Swallow and try next constructor.
						if (causes == null) {
							causes = new LinkedList<>();
						}
						causes.add(ex);
						continue;
					}
				}
				else {
					// Explicit arguments given -> arguments length must match exactly.
					if (paramTypes.length != explicitArgs.length) {
						continue;
					}
					argsHolder = new ArgumentsHolder(explicitArgs);
				}

				/**
				 * typeDiffWeight 差异量，何谓差异量呢？
				 * argsHolder.arguments和paramTypes之间的差异
				 * 每个参数值得类型与构造方法参数列表的类型直接的差异
				 * 通过这个差异量来衡量或者确定一个合适的构造方法
				 *
				 * 值得注意的是constructorToUse=candidate
				 *
				 * 第一次循环一定会typeDiffWeight < minTypeDiffWeight，因为minTypeDiffWeight的值非常大
				 * 然后每次循环会把typeDiffWeight赋值给minTypeDiffWeight（minTypeDiffWeight = typeDiffWeight）
				 * else if (constructorToUse != null && typeDiffWeight == minTypeDiffWeight)
				 * 第一次循环肯定不会进入这个
				 * 第二次如果进入了这个分支代表什么？
				 * 代表有两个构造方法都符合我们要求？那么spring有迷茫了（spring经常在迷茫）
				 * spring迷茫了怎么办？
				 * ambiguousConstructors.add(candidate);
				 * 顾名思义。。。。
				 * ambiguousConstructors=null 非常重要？
				 * 为什么重要，因为需要清空
				 * 这也解释了为什么他找到两个符合要求的方法不直接抛异常的原因
				 * 如果这个ambiguousConstructors一直存在，spring会在循环外面去exception
				 * 很牛逼呀！！！！
				 */
				int typeDiffWeight = (mbd.isLenientConstructorResolution() ?
						argsHolder.getTypeDifferenceWeight(paramTypes) : argsHolder.getAssignabilityWeight(paramTypes));
				// Choose this constructor if it represents the closest match.
				if (typeDiffWeight < minTypeDiffWeight) {
					constructorToUse = candidate;//即将要用的构造方法
					argsHolderToUse = argsHolder;//要使用的参数对象
					argsToUse = argsHolder.arguments;//即将要用的参数
					minTypeDiffWeight = typeDiffWeight;//最小参数 -1024
					ambiguousConstructors = null;
				}
				else if (constructorToUse != null && typeDiffWeight == minTypeDiffWeight) {
					if (ambiguousConstructors == null) {
						ambiguousConstructors = new LinkedHashSet<>();
						ambiguousConstructors.add(constructorToUse);
					}
					ambiguousConstructors.add(candidate);
				}
			}

			//循环结束
			//没有找打合适的构造方法
			if (constructorToUse == null) {
				if (causes != null) {
					UnsatisfiedDependencyException ex = causes.removeLast();
					for (Exception cause : causes) {
						this.beanFactory.onSuppressedException(cause);
					}
					throw ex;
				}
				throw new BeanCreationException(mbd.getResourceDescription(), beanName,
						"Could not resolve matching constructor " +
						"(hint: specify index/type/name arguments for simple parameters to avoid type ambiguities)");
			}

			//如果ambiguousConstructors还存在则异常？为什么会在上面方法中直接exception？
			//上面注释当中有说明
			else if (ambiguousConstructors != null && !mbd.isLenientConstructorResolution()) {
				throw new BeanCreationException(mbd.getResourceDescription(), beanName,
						"Ambiguous constructor matches found in bean '" + beanName + "' " +
						"(hint: specify index/type/name arguments for simple parameters to avoid type ambiguities): " +
						ambiguousConstructors);
			}

			if (explicitArgs == null && argsHolderToUse != null) {
				/*
				 * 缓存相关信息，比如：
				 *   1. 已解析出的构造方法对象 resolvedConstructorOrFactoryMethod
				 *   2. 构造方法参数列表是否已解析标志 constructorArgumentsResolved
				 *   3. 参数值列表 resolvedConstructorArguments 或 preparedConstructorArguments
				 *   这些信息可用在其他地方，用于进行快捷判断
				 */
				argsHolderToUse.storeCache(mbd, constructorToUse);
			}
		}

		Assert.state(argsToUse != null, "Unresolved constructor arguments");
		//instantiate()生成bean
		bw.setBeanInstance(instantiate(beanName, mbd, constructorToUse, argsToUse));
		return bw;
	}
```

### 4.构造函数实例化对象时的参数如何实例化

**调用链AbstarctAutowireCpableBeanFactory.createBeanInstance ==> autowireConstructor ==> ConstructorResorlver.autowireConstructor**

假如最终适用的构造函数是有参数的构造函数，然后第一次初始化的时候调用

```java

argsHolder = createArgumentArray(beanName, mbd, resolvedValues, bw, paramTypes, paramNames,
								getUserDeclaredConstructor(candidate), autowiring, candidates.length == 1);
```

## populateBean为对象属性赋值

**AbstarctAutowireCpableBeanFactory.createBeanInstance ==> populateBean**



## spring是如何解决循环依赖问题

简单总结版：

1.获取A类时，一级缓存singletonObjects获取不到，并且正在创建set（singletonsCurrentlyInCreation）中没有A类

2.从二级缓存（earlySingletonObjects）中获取，还是没有

3.二级工厂singletonFactories存放的都是继承了ObjectFactory的对象，有个getObject方法，通过lambda执行调用

2.通过createBean() -->docreateBean()创建类(选择哪个构造函数的代码在这)

3.

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