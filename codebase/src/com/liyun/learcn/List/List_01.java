package com.liyun.learcn.List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
java.util.List接口 extends Collection接口
List接口的特点:
    1.有序的集合，存储源苏苏和取出元素的顺序是一致的
    2.有索引，包含了一些带索引的方法
    3.允许存储重复的元素
List接口中带索引的方法
    public void add(int index,E element): 将指定的元素，添加到该集合中的指定位置上。
    public E get(int index): 返回集合中指定位置的元素。
    public E remove(int index): 一处列表中指定位置的元素，返回的是被移除的元素。
    public E set(int index,E element): 用指定元素替换集合中指定位置的元素，返回值更新前的元素
注意:
    操作索引的时候，一定要防止索引越界异常。
 */
public class List_01 {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("a");
        list.add(3,"hello");
        System.out.println(list);

        list.remove(2);
        //替换把最后一个a,替换为A，返回被替换的值
        String str1=list.set(4,"A");
        System.out.println(str1);

        //List集合遍历的三种方法
        //for循环遍历
        for(int i=0;i<list.size();i++){
            String s=list.get(i);
            System.out.println(s);
        }
        //使用迭代器
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String next=iterator.next();
            System.out.println(next);
        }
        //使用增强for循环
        for (String s : list) {
            System.out.println(s);
        }
    }
}
