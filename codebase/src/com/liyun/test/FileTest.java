package com.liyun.test;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        File f1=new File("D:\\SPRING_BOOT\\a.txt");
        System.out.println(f1);
        File f2=new File("D:\\SPRING_BOOT");
        //父路径和子路径，可以单独书写，使用起来非常灵活；父路径和子路径都可以变化
        File f3=new File("D:\\SPRING_BOOT\\","b.txt");
        System.out.println(f3);
        //父路径是File类型，可以使用File的方法对路径进行一些操作，再使用路径创建对象
        File f4=new File(f2,"c.txt");
    }
}