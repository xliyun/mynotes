package com.liyun.learn.Integer;
/*
基本类型与字符串类型之间的相互转换三种方式
基本类型->字符串(String)
    1.基本类型的值+""  最简单的方法(工作中常用)
    2.包装类的静态方法toString(参数)，不是Object类的toString()方法
    3.String类的静态方法valueOf(int i)返回int 参数

    字符串(String)->基本类型
        使用包装类的静态方法parseXXX("数值类型的字符串");
        Integer类：static int parseInt(String s)
        Double类: static double parseDouble(String s)
 */
public class Integer_03 {
    public static void main(String[] args) {
        //基本类型->字符串(String)
        int i1=100;
        String s1=i1+"";
        System.out.println(s1+200);//100200

        String s2 = Integer.toString(111);
        System.out.println(s2+200);//111200

        String s3 = String.valueOf(222);
        System.out.println(s3+200);//222200

        //字符串(String) --> 基本类型
        int in1 = Integer.parseInt("234");
        System.out.println(in1+200);//434
    }
}
