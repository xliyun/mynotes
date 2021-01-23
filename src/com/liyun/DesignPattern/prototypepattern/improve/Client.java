package com.liyun.DesignPattern.prototypepattern.improve;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-10 10:07
 */
public class Client {
    public static void main(String[] args) {
        Sheep sheep=new Sheep("白羊",1,"白色");
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();
        System.out.println(sheep2.hashCode());
        System.out.println(sheep3.hashCode());
        System.out.println(sheep2);
    }
}
