package com.liyun.DesignPattern.singleton.type8;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-06 16:15
 */
public class SingletonTest08 {
    public static void main(String[] args) {
        Singleton instance=Singleton.INSTANCE;
        Singleton instance2=Singleton.INSTANCE;
        System.out.println(instance==instance2);
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());

        instance.sayOK();
    }
}

//使用枚举，可以实现单例，推荐使用
enum Singleton{
    INSTANCE;
    public void sayOK(){
        System.out.println("OK!");
    }
}