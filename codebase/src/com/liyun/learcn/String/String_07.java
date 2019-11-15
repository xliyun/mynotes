package com.liyun.learcn.String;
/*
分割字符串的方法:
public String[] split(String regex):按照参数的规则，将字符串切成若干部分
注意事项：
split方法的参数其实是一个正则表达式，如果按照英文句点"."进行切分，必须写"\\."(两个反斜杠)
 */
public class String_07 {
    public static void main(String[] args) {
        String str1="aaa,bbb,ccc";
        String[] array1=str1.split(",");
        for(int i=0;i<array1.length;i++)
            System.out.println(array1[i]);

        String str2="哈哈.嘿嘿.嘻嘻";
        String[] array2 = str2.split("\\.");
        for(int i=0;i<array2.length;i++)
            System.out.println(array2[i]);
    }
}
