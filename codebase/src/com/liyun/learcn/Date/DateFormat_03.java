package com.liyun.learcn.Date;

/*
 java.text.DateFormat是日期/时间格式化子类的抽象类
 作用：
      格式化(也就是日期 -> 文本)、解析(文本 -> 日期)
  成员方法：
       String format(Date date) 按照指定的模式，把Date日期，格式化为符合模式的字符串
       Date parse(String source) 把符合模式的字符串，解析为Date日期
  DateFormat类是一个抽象类，无法直接创建对象使用，可以使用DateFormat类的子类

  java.text.SimpleDateFormat extends DateFormat

  构造方法：
    SimpleDateFormat(String pattern)
    用给定的模式和默认语言环境的日期格式符号构造SimpleDateFormat。
  参数：
     String pattern：传递指定的模式
     y 年 m月 d日 H小时 m分钟 s秒
  注意：
     模式中的字母不能更改，连接模式的符号可以改变。

 */
public class DateFormat_03 {

}
