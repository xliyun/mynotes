package com.liyun.learn.Set;

import java.util.HashSet;

/*
HashSet存储自定义类型元素
    Set集合报错元素唯一
        存储的元素(String,Integer,...Student,Person...),必须重写hashCode方法和equals方法
    要求:
        同名同年龄的人，视为同一个人，只能存储一次
 */
public class HashSet_02 {
    public static void main(String[] args) {
        HashSet<Person> set=new HashSet<>();
        Person p1=new Person("周杰伦",33);
        Person p2=new Person("林俊杰",35);
        Person p3=new Person("周杰伦",33);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println(set);
    }
}
