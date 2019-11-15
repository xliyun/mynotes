package com.thread.t3;

public class Singleton {
    public static void main(String[] args) {
        Singleton s1=Singleton.getInstance();
        Singleton s2=Singleton.getInstance();

        System.out.println("s1的hash值: "+s1+"s2的hash值: "+s2);
    }
    //通过私有化构造方法，保证实例唯一,别人不能new
    private Singleton(){}

    private static Singleton instance=new Singleton();

    //既然不让别人new了,显然要提供一个获取实例的方法
    //饿汉式(没有线程安全性问题)，一开始就创建了对象，这里只是返回对象
    public static Singleton getInstance(){
        return instance;
    }

    //出现线程安全问题的条件
    //多线程的环境下
    //必须有共享资源
    //对资源进行非原子性操作


}
