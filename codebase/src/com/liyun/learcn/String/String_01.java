package com.liyun.learcn.String;
/*
java.lang.String 类代表字符串的类
API当中说：Java 程序中所有字符串字面值(如"abc")都作为此类的实例实现。
其实就是说:程序当中的所有双引号字符串，都是String类的对象。(就算没有new,也照样是)

字符串的特点:
1.字符串的内容永不可变
字符串是常量，它们的值在创建之后不能更改
2.正是因为字符串不可改变，所以字符串是可以共享使用的
3.字符串效果上相当于char[]字符数组，但是底层原理是byte[]字节数组,jdk1.8我自己看的是char[]

创建字符串的常见3+1种方式，三种构造方法，一种直接创建
三种构造方法:
public String(): 创建一个空白字符串，不含有任何内容。
public String(char[] array): 根据字符数组的内容，来创建对应的字符串
public String(byte[] array): 根据字节数组的内容，来创建对应的字符串
一种直接创建：
String str="Hello";
注意：直接写双引号的方式，jvm会帮你创建new String
 */
public class String_01 {
    public static void main(String[] args) {
        //使用空参构造
        String str1=new String();
        System.out.println("第一个字符串："+str1);

        //根据字符数组创建字符串
        char[] charArray={'A','B','C'};
        String str2=new String(charArray);
        System.out.println("第二个字符串："+str2);

        //根据字节数组创建字符串
        byte[] byteArra={97,98,99};//小写a b c ascii码97，98，99
        String str3=new String(byteArra);
        System.out.println("第三个字符串："+str3);

        //直接创建
        String str4="Hello";

    }
}
