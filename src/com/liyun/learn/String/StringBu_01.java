package com.liyun.learn.String;

/*
java.lang.StringBudilder类：字符串缓冲区，可以提高字符串的效率
构造方法：
    StringBuilder()构造一个不带任何字符的字符串生成器，其初始容量为16个字符。
    StringBuilder(String str)
        构造一个字符串生成器，并初始化为指定的字符串内容。
常用方法:
    public StringBuilder append(): 添加任意类型数据的字符串形式(比如char CharSequence Object)，并返回当前对象本身
    public String toString(): 将当前StringBuilder对象转换为String对象
 */
public class StringBu_01 {
    public static void main(String[] args) {
        StringBuilder bu1 = new StringBuilder();
        System.out.println("bu1: "+bu1);

        StringBuilder bu2 = new StringBuilder("abc");
        System.out.println("bu2: "+bu2);

        //使用append犯法网StringBuilder中添加数据
        //apend方法返回的是this,调用方法的对象bu,this==bu
        StringBuilder bu3=bu1.append("123");//把bu1的地址赋值给了bu3
        System.out.println(bu1);//"123"
        System.out.println(bu3);//"123"
        System.out.println(bu1==bu3);//比较的是地址 true

        bu1.append("789").append("def");
        System.out.println(bu1);

        //String和StringBuilder互相转换
        String str=bu1.toString();
        bu1=new StringBuilder(str);
    }
}
