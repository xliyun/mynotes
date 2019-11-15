package com.liyun.learcn.Collection2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    java.utils.Collections是集合工具类，用来对集合进行操作。部分方法如下:
        public static <T> void sort(List<T> list,Comparator<? super T>）:将集合中元素按照指定规则排序。

    Comparator和Comparable的区别
        Comparable:自己(this）和别人(参数)比较，自己需要实现Comparable接口，重写比较的规则compareTo方法
        Comparator:相当于找一个第三方的裁判比较两个人
            o1-o2升序
 */
public class Comparator_01 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(2);
        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;//升序
            }
        });
        System.out.println(arrayList);
    }
}
