package com.liyun.learn.String;

/*
String当中与转换相关的常用方法有;
public char[] toCharArray(): 将当前字符串拆分成为字符数组作为返回值
public byte[] getBytes():   获得当前字符串底层的字节数组
public String replace(CharSequence oldString,CharSequence newString):
将所有出现的老字符串替换成新的字符串，返回替换之后的结果新字符串
备注：charSequence意思就是可以接受字符串类型
 */
public class String_06 {
    public static void main(String[] args) {
        char[] chars = "Hello".toCharArray();
        System.out.println(chars[0]);
        System.out.println(chars.length);

        byte[] charArray="测试".getBytes();
        for(int i=0;i<charArray.length;i++)
            System.out.println(charArray[i]);

        String str1="How do you do?";
        String str2 = str1.replace("o", "*");
        System.out.println(str2);
    }
}
