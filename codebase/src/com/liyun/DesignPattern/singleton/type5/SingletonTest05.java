package com.liyun.DesignPattern.singleton.type5;




/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-06 14:54
 */
public class SingletonTest05 {
    public static void main(String[] args) {
        System.out.println("懒汉式3，线程不安全");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance==instance2);
        System.out.println("instance.hashCode："+instance.hashCode()+"  instacne2.hashCode2: "+instance2.hashCode());

    }
}

class Singleton{
    private static Singleton instance;

    private Singleton(){

    }

    //加入同步代码块，解决线程不安全问题
    public static  Singleton getInstance(){
        if(instance == null){
            synchronized(Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
