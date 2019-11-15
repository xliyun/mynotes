package com.liyun.learcn.Calendar;

import java.util.Calendar;
import java.util.Date;

/*
根据Canlendar类的API文档，常用方法有：
    public int get(int field): 返回给定日历字段的值。
    public void set(int field,int value): 将给定的日历字段设置为给定值。
    public abstract void add(int field,int amount): 根据日历的规则，为给定的日历字段添加或减去指定的时间量
    public Date getTime(): 返回一个表示此Calendar时间值(从历元到现在的毫秒偏移量)的Date对象
 成员方法的参数：
    int filed:日历类的字段，可以使用Canlendar类的静态成员变量获取
        public static final int YEAR = 1; 年
        public static final int MONTH = 2; 月0-11
        public static final int DATE = 5; 月中的某一天
        public static final int DAY_OF_MONTH = 5; 月中的某一天 跟上一个一样
        public static final int HOUR = 10; 时
        public static final int MINUTE = 12; 分
        public static final int SECOND = 13; 秒
 */
public class Calendar_02 {
    public static void main(String[] args) {
         //使用getInstance方法获取Calendar对象
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        System.out.println(year);
        int month=c.get(Calendar.MARCH);//0-11
        System.out.println(month);
        c.set(Calendar.DATE,23);
        System.out.println(c.get(Calendar.DAY_OF_MONTH));
        //同时设置年月日,set方法的重载
        c.set(2099,9,22);

        //为给定的日历字段添加时间量
        c.add(Calendar.YEAR,-2);

        Date dd=c.getTime();
        System.out.println(dd);
    }
}
