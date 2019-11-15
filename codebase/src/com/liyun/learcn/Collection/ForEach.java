package com.liyun.learcn.Collection;

import java.util.ArrayList;

/*
增强for循环：底层使用的也是迭代器，使用for循环的格式，简化了迭代器的书写
是JDK1.5之后出现的新特性
Collection<E> extends Ierable<E>:所有的单列集合都可以使用增强for
public interface Iterable<T> 实现这个接口允许对象成为foreach语句的目标

增强for循环:用来变量集合和数组
 */
public class ForEach {
    public static void main(String[] args) {
        int [] arr={1,2,3,4,5};
        for(int i:arr){
            System.out.println(i);
        }

        ArrayList<String> list=new ArrayList<>();
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
