package com.liyun.DesignPattern.proxy.dynamic;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-21 11:01
 */
public class ProxyFactory {

    //维护一个目标对象，Object
    private Object target;

    //构造器，对target进行初始化
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance(){
        /*
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
    1.ClassLoader loader:指定当前目标对象使用的类加载器，获取加载器的方法固定
    2.Class<?>[] interface:目标对象实现的接口类型，使用泛型方式确认类型
    3.InvocationHandler h:事件处理，执行目标方法时，会去触发事件处理器方法，会把当前 执行的目标对象方法作为一个参数
      简而言之就是怎么去调用当前的方法
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("===JDK代理开始===");
                        //反射机制调用目标对象的方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("===JDK代理提交啦===");
                        return returnValue;
                    }
                });
    }
}
