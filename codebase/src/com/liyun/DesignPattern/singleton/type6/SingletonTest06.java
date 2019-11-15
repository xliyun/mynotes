package com.liyun.DesignPattern.singleton.type6;




/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-06 14:54
 */
public class SingletonTest06 {
    public static void main(String[] args) {
        System.out.println("双重检查，线程安全");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance==instance2);
        System.out.println("instance.hashCode："+instance.hashCode()+"  instacne2.hashCode2: "+instance2.hashCode());

    }
}

class Singleton{
    private static volatile Singleton instance;

    private Singleton(){

    }

    //加入同步代码块，解决线程不安全问题
    public static synchronized Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
