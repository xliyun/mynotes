package com.liyun.DesignPattern.singleton.type7;


import com.thread.ta4.Single;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-06 14:54
 */
public class SingletonTest07 {
    public static void main(String[] args) {
        System.out.println("使用静态内部类完成单例模式");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance==instance2);
        System.out.println("instance.hashCode："+instance.hashCode()+"  instacne2.hashCode2: "+instance2.hashCode());

    }
}

//静态内部类完成
class Singleton{

    //构造器私有化
    private Singleton(){

    }
    //写一个静态内部类，该类中有一个静态的属性Singleton
    private static class SingletonInstance{
        private static final Singleton INSTANCE= new Singleton();
    }

    //提供静态共有方法，直接返回SingletonInstance.INSTANCE
    public static  Singleton getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
