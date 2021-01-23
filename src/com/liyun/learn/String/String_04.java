package com.liyun.learn.String;

/*
String当中与获取相关的常用方法有:
public int length(): 获取字符串当中含有的字符个数，拿到字符串长度。
publiic String concat(String str): 将当前字符串和参数字符串拼接成为返回值新的字符串。
public charAt(int index): 获取指定索引位置的单个字符。(索引从0开始)
public indexOf(String str): 查找参数字符串在本字符串当中首次出现的索引位置，如果没有，返回-1值。
 */
public class String_04 {
    public static void main(String[] args) {
        int length = "sljgasljglajg'alg'asd;".length();
        System.out.println("字符串的长度："+length);

        //拼接字符串
        String str1="Hello";
        String str2="World";
        String str3=str1.concat(str2);//这是一个全新的字符串
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

        //获取指定索引位置的单个字符
        char ch="Hello".charAt(2);
        System.out.println("在2号索引位置的字符是："+ch);

        //查找参数字符串在本来字符串当中出现的第一次索引的位置
        //如果根本没有，返回-1值
        String original="HelloWorld";
        int index = original.indexOf("ll");
        System.out.println("第一次索引值是："+index);

    }
}
