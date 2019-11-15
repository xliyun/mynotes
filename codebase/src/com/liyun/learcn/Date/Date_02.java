package com.liyun.learcn.Date;

import java.util.Date;
/*
Date类的空参数构造方法
Date() 获取当前系统的日期和时间

Date(Long date): 传递毫秒值，把毫秒值转换为Date日期

getTime() 返回自1970年1月1日 00:00:00 GMT以来此Date对象表示的毫秒数
 */
public class Date_02 {
    public static void main(String[] args) {
        Date date =new Date();
        System.out.println(date);

        Date date2=new Date(0L);
        System.out.println(date2);

        Date date3=new Date();
        long time=date3.getTime();
        System.out.println("毫秒值："+time);
    }

}
