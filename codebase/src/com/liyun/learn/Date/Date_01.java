package com.liyun.learn.Date;
/*
java.util.Date: 表示日期和时间的类，表示特定的瞬间，精确到毫秒

把日期转换为毫秒:
    当前日期:2019-01-01
    时间原点(0毫秒)：1970年1月1日 00:00:00(英国格林威治)
    就是计算当期日期到事件原点一共经历了多少毫秒(1550727648422)
把毫秒转换为日期:
     1天 = 24 * 60 * 60 =86400秒 =86400 * 1000 =86400 000毫秒
注意:
     中国属于东八区，会把时间增加8个小时
     1970年1月1日 08:00:00
 */
public class Date_01 {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
