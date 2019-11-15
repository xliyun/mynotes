package com.liyun.learcn.Collection2;

import java.util.ArrayList;
import java.util.Collections;

/*
    java.utils.Collections是集合工具类，用来对集合进行操作。部分方法如下:
    public static <T> boolean addAll(Collection<T> c, T... elements):往集合中添加一些元素
    public static void shuffle(List<?> list) 打乱顺序:打乱集合顺序
    public static <T> void sort(list<T> list): 将集合元素按照默认规则排序
    public static <T> void sort(List<T> list,Comparator<? super T>): 将集合中元素安装指定规则排序
 */
public class Collections_01 {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        //往集合中添加多个元素
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Collections.addAll(list,"汪苏泷","周杰伦");
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);

        Collections.sort(list);
    }
}
