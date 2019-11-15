package com.liyun.learcn.String;
/*
题目：
定义一个方法，把数组{1,2,3}按照指定格式拼接成一个字符串。格式参照如下：[word1#word2#word3]。

分析：
1.首先准备一个int[]数组，内容是：1、2、3
2.定义一个方法，用来将数组变成字符串
三要素

 */
public class String_08 {
    public static void main(String[] args) {
        int[] array={1,2,3};
        StringBuilder sb=new StringBuilder("abc");
    }
    public static String fromArrayToString(int[] array){
        String str="[";
        for (int i : array) {
            str+="word"+array[i];
        }
        return str+']';
    }
}
