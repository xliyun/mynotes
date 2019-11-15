package com.liyun.learcn.String;
/*
字符串常量池：程序当中直接写上的双引号字符串，就在字符串常量池中,但是new String对象是在堆当中
对于基本类型来说，==是进行数值的比较。
对于引用类型来说，==是进行【地址值】的比较。
 */
public class String_02 {
    public static void main(String[] args) {
        String str1="abc";
        String str2="abc";

        char[] charArray={'a','b','c'};
        String str3=new String(charArray);

        String str4=new String("abc");
        String str5=new String("abc");

        //直接==比较的是地址
        System.out.println(str1==str2);//true
        System.out.println(str1==str3);//false
        System.out.println(str2==str3);//false

        //双引号赋值的在字符串常量池里面，而new的在堆里面，所以地址不一样
        System.out.println("str3==str4:"+(str3==str4));
        //两个new的对象虽然内容一样，但是在堆里是两个对象
        System.out.println("str4==str5:"+(str4==str5));
    }
}
