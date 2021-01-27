# cglib动态代理的使用

## 目标类接口

```java
public interface TargetInterface {
    public String sayHello(String name);

    public String sayThanks(String name);
}
```

## 目标类

```java
public class TargetInterfaceImpl implements TargetInterface {
    @Override
    public String sayHello(String name) {
        //限流
        return "Hello, "+name;
    }

    @Override
    public String sayThanks(String name) {
        //限流
        return "Thanks, "+name;
    }
}
```

## cglib动态代理

```java
/**
 * TargetProxy类还不是一个真正的代理类，是代理类的一个必须要素
 */
public class TargetProxy implements MethodInterceptor {

    //获取真正的代理类
    public <T> T getProxy(Class clazz){
        //字节码增强的一个类
        Enhancer enhancer = new Enhancer();
        //cglib是通过继承目标类实现
        //设置父类
        enhancer.setSuperclass(clazz);

        //设置回调类,就是实现MethodInterceptor extends Callback的类
        enhancer.setCallback(this);

        //创建代理类
        Object t = enhancer.create();

        return (T)t;
    }

    /**
     * 既可以拦截sayHello 也可以拦截sayThanks
     * @param obj
     * @param method
     * @param args
     * @param proxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(method.getName()+"服务限流.............");

        //调用目标方法
        return proxy.invokeSuper(obj,args);
    }
}
```

## 调用动态代理

```
public class Test {
    public static void main(String[] args) {

        //通过参数设置，把动态代理生成的class文件输出到磁盘上，默认是不会输出到磁盘上的，所以动态代理生成的类我们是看不着的
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:/");

        TargetProxy targetProxy = new TargetProxy();

        //拿到目标类的代理类，
        TargetInterfaceImpl t = targetProxy.getProxy(TargetInterfaceImpl.class);
        System.out.println(t.sayHello("小母牛"));


        //被final修饰的方法不会被增强，会直接调用
        UserService userService = targetProxy.getProxy(UserService.class);
        userService.sleeping();
    }
}
```

