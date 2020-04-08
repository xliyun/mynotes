package com.liyun.learn.String;

import java.util.Scanner;

public class String_09 {
    public static void main(String[] args) {
        /*Scanner sc=new Scanner(System.in);
        int num=sc.nextInt();*/

        //匿名对象的方式
        int num=new Scanner(System.in).nextInt();
        System.out.println("输入的是： "+num);
        methodParam(new Scanner(System.in));
    }
    public static void methodParam(Scanner sc){
        System.out.println(sc.nextInt());
    }
}
