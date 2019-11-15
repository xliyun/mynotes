package com.liyun.learcn.Calendar;

import java.util.Calendar;

/*
java.util.Clendar: 日历类
Calendar类是一个抽象类，里边提供了很多操作日历字段的方法(YEAR MONTH DAY_OF_MONTH HOUR)
Calendar类无法直接创建对象使用，里边有一个静态方法叫getInstance()，该方法返回了canlendar类的子类对象GregorianCalendar
static Calendar getInstance() 使用默认时区和语言环境获得一个日历。
 */
public class Calender_01 {
    public static void main(String[] args) {
        Calendar c= Calendar.getInstance();//多态，让一个父类，接收一个子类
        // YEAR=2019,MONTH=1,WEEK_OF_YEAR=8,WEEK_OF_MONTH=4,DAY_OF_MONTH=22,DAY_OF_YEAR=53,DAY_OF_WEEK=6,DAY_OF_WEEK_IN_MONTH=4,AM_PM=1,HOUR=2,HOUR_OF_DAY=14,
        // MINUTE=6,SECOND=37,MILLISECOND=209,ZONE_OFFSET=28800000,DST_OFFSET=0]
        System.out.println(c);
    }
}
