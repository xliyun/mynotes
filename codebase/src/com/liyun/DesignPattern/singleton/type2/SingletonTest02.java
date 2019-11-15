package com.liyun.DesignPattern.singleton.type2;

/**

 */
public class SingletonTest02 {
    public static void main(String[] args) {
        //测试
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance==instance2);
        System.out.println("instance.hashCode："+instance.hashCode()+"  instacne2.hashCode2: "+instance2.hashCode());
    }
}

//饿汉式（静态代码块）

class Singleton{

    //1.构造器私有化，外部不能new
    private Singleton(){

    }

    private  static Singleton instance;

    //在静态代码块中，创建实例对象
    static{
        instance=new Singleton();
    }

    //3.对外提供一个共有的静态方法，返回实例对象
    public static Singleton getInstance(){
        return instance;
    }
}