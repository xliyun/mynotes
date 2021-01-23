package com.liyun.learn.Arrays;

import java.util.Arrays;

/*
 java.util.Arrays是一个与数组相关的工具类，里面提供了大量静态方法，用来实现数组常见的操作。

 public static String toString(数组): 将参数数组变成字符串(按照默认格式：[元素1，元素2..])
 public static void sort(数组) :  按照默认升序对数组的元素进行排序。
备注:
1.如果是数组，sort按照升序从小到大
2.如果是字符串，sort默认按照字符升序
3.如果是自定义的类型，那么这个自定义的类需要有Comparable或者Comparator接口的支持。
 */
public class Arrays_01 {
    public static void main(String[] args) {
        int [] intArray={20,10,70,30};
        String intStr= Arrays.toString(intArray);
        System.out.println(intStr);
        Arrays.sort(intArray);
        System.out.println(Arrays.toString(intArray));

        String[] array2={"bbb","aaa","ccc"};
        System.out.println();
    }
}
