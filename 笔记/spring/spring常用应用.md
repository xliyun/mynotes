factory-method

合并父类



## facotrymethod的使用

### 简介

在spring中，创建bean对象的方式有：

直接在xml配置文件中，通过bean标签创建对象

通过注解如@Bean、@Service等创建对象

通过FactoryBean工厂类创建对象

通过factory-method属性创建对象

### factory-method属性创建对象

通过factory-method属性有两种方式，一种是静态的(方法必须是静态的)，另一种是动态的。使用上不复杂，这里直接上代码，解释请看注释。

#### 定义类Stu.java

```java
public class Stu {
    public String stuId;
    public Stu(String stuId) {
        this.stuId = stuId;
    }
}
```

#### 定义工厂类StuFactory.java

```java
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
public class StuFactory {
    static Map<String,Stu> stuMap = new HashMap<>();
    static{
        //初始化
        Stream.iterate(1,n->n+1).limit(5).map(String::valueOf).forEach(t-> stuMap.put(t, new Stu(t)));
    }

    //静态创建类，方法必须是静态(static修饰)
    public static Stu getStaticStu(String stuId){
        return stuMap.get(stuId);
    }

    //动态创建类
    public Stu getDynamicStu(String stuId){
        return new Stu(stuId);
    }
}
```

#### 定义spring配置文件springFactoryMethodConfig.xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--静态获取对象-->
    <bean id="staticStu" class="com.dragon.study.study20190618.spring.springFactoryMethod.StuFactory"
          factory-method="getStaticStu">
        <!--传入getStaticStu方法的参数-->
        <constructor-arg value="1"/>
    </bean>


    <!--生成对象的工厂-->
    <bean id="stuFactory" class="com.dragon.study.study20190618.spring.springFactoryMethod.StuFactory"/>
    <!--动态获取对象-->
    <bean id="dynamicStu" factory-bean="stuFactory" factory-method="getDynamicStu">
        <!--传入getDynamicStu方法的参数-->
        <constructor-arg value="11"/>
    </bean>
</beans>
```

#### 使用

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static com.sun.jmx.mbeanserver.Util.cast;

public class SpringFactoryMethod {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:springFactoryMethodConfig.xml");

        //获取静态Bean
        Stu staticStu = cast(ac.getBean("staticStu"));

        //获取动态Bean
        Stu dynamicStu =cast(ac.getBean("dynamicStu"));
    }
}
```

## @Autowired对构造函数进行注释

### 问题：

在编写代码的时候，使用@Autowired注解是，发现IDE报的一个警告，如下：

Spring Team recommends "Always use constructor based dependency injection in  your beans. Always use assertions for mandatory dependencies.

翻译：
Spring建议，总是在您的bean中使用构造函数建立依赖注入。总是使用断言强制依赖。

### 初始化顺序

不考虑父类，初始化的顺序是

静态变量或静态代码块>实例变量或初始化语句块>构造方法>@Autowired

ps:静态变量或静态语句块的初始化顺序是自上到下的

### 问题代码：

```java
@Autowired
private User user;
private School school;

public UserServiceImpl(){
    this.school.id = user.getSchoolId();
}

```

由于java先执行构造方法，导致

this.school = user.getSchool();

报空指针异常（虽然这个例子正常人都不会这样写...

### 解决方法：使用构造器注入

```java
private User user;
private String schoolId;

@Autowired
public UserServiceImpl(User user){
    this.user = user;
    this.schoolId = user.getSchoolId();
}
```

### 单例使用final

而且若是你是个单例的模式（bean没写@scope，默认为单例，

那么spring还建议你在bean的声明上加final，这个的解析就简单粗暴了。

因为加上final只会在程序启动的时候初始化一次，并且在程序运行的时候不会再改变。

```java
    private final EnterpriseDbService service;

    @Autowired
    public EnterpriseDbController(EnterpriseDbService service) {
       this.service = service;
    }
```

## PropertyEditorSupport实现不同类型的注入

redisTemplate注入到ValueOperations，避免了ValueOperations<String,  Object> valueOperations = redisTemplate.opsForValue();  这样来获取ValueOperations；

```
今天看到Spring操作redis  是可以将redisTemplate注入到ValueOperations，避免了ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue(); 这样来获取ValueOperations；
```

redisTemplate并不是ValueOperations的实现类，这两个在继承上毫无联系的两个类是如何注入的呢。

```java
// Check if required type matches the type of the actual bean instance.
        if (requiredType != null && bean != null && !requiredType.isInstance(bean)) {
            try {
                return getTypeConverter().convertIfNecessary(bean, requiredType);
            }
            catch (TypeMismatchException ex) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Failed to convert bean '" + name + "' to required type '" +
                            ClassUtils.getQualifiedName(requiredType) + "'", ex);
                }
                throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
            }
        }
```

spring会去加载 ValueOperations+Editor，即ValueOperationsEditor的类。且此类必须要实现PropertyEditor接口。

而我们在ValueOperations的包目录下确实会找到ValueOperationsEditor。

```java
class ValueOperationsEditor extends PropertyEditorSupport {

    public void setValue(Object value) {
        if (value instanceof RedisOperations) {
            super.setValue(((RedisOperations) value).opsForValue());
        } else {
            throw new java.lang.IllegalArgumentException("Editor supports only conversion of type " + RedisOperations.class);
        }
    }
}
```

这个类非常简单，它重写了setValue方法，将redisTemplate中的opsForValue()返回值set进去，而opsForValue()返回值就是继承了ValueOperations的DefaultValueOperations。

这样我们用editor get value的时候就能获取到DefaultValueOperations了。就可以将DefaultValueOperations注入到ValueOperations中去了。

## ImportBeanDefinitionRegistrar注入不加注解的bean

继承ImportBeanDefinitionRegistrar接口实现postProecessBeanFactory方法