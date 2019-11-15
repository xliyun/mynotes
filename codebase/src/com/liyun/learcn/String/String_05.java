package com.liyun.learcn.String;

/*
字符串的截取方法：
public String substring(int index): 截取从参数位置一直到字符串末尾，返回新字符串
public String substring(int begin,int end): 截取从begin开始,一直到end结束，中间的字符串
备注: [begin,end)，左闭右开区间，包含左边，不包含右边。
 */
public class String_05 {
    public static void main(String[] args) {
        String str1="HelloWorld";
        String str2=str1.substring(5);
        System.out.println(str1);//Hellworld 原封不动
        System.out.println(str2);//world 新字符串

        String str3=str1.substring(4,7);//shift+左方向键数字符位置
        System.out.println(str3);

        String strA="Hello";
        System.out.println();
    }
}
