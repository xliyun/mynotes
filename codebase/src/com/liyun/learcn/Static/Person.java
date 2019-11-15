package com.liyun.learcn.Static;
/*
静态代码块
特点，第一次使用到本类时，静态代码块执行唯一的一次。
静态内容总是优先于非静态，所以静态代码块比构造方法先执行

静态代码块的典型用途：
用来一次性地对静态成员变量进行赋值
 */
public class Person {
    public static void main(String[] args) {
        Person one=new Person();
    }
    static{
        System.out.println("静态代码块执行，只执行一次！");
    }

    public Person() {
        System.out.println("构造方法");
    }
}
