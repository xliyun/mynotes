package com.liyun.DesignPattern.proxy.dynamic;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-21 14:18
 */
public class Client {
    public static void main(String[] args) {
        //创建一个目标对象
        ITreacherDao teacherDao = new TeacherDao();

        //给目标对象创建代理对象
        ProxyFactory proxyFactory = new ProxyFactory(teacherDao);
        ITreacherDao proxyInstance = (ITreacherDao) proxyFactory.getProxyInstance();

        System.out.println("proxyInstance="+proxyInstance);
        //proxyInstance=class com.sun.proxy.$Proxy0 内存中动态生成代理对象
        System.out.println("类的类型="+proxyInstance.getClass());
        //通过代理对象，调用目标的方法
        proxyInstance.teach();
    }
}
