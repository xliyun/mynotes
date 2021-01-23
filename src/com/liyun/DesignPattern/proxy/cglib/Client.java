package com.liyun.DesignPattern.proxy.cglib;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-21 15:33
 */
public class Client {
    public static void main(String[] args) {
        //创建目标对象
        TeacherDao teacherDao=new TeacherDao();
        //将目标对象传递给代理对象，并获取到代理对象
        TeacherDao proxyInstance = (TeacherDao) new ProxyFactory(teacherDao).getProxyInstance();
        //执行代理对象的方法，触发intercept，从而实现对目标对象的调用
        proxyInstance.teach();
    }
}
