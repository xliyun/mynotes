package com.liyun.learcn.enumtest;

//自定义枚举类
public class Season {
    //1.声明Season对象的属性：private final 修饰
    private final String sessonName;
    private final String sessonDesc;

    //2.私有化类的构造器，并给对象属性赋值
    public Season(String sessonName, String sessonDesc) {
        this.sessonName = sessonName;
        this.sessonDesc = sessonDesc;
    }

    //3.听当前枚举类的多个对象：public static final的
    public static final Season SPRING = new Season("春天","春天来了");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","冰天雪地");

    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName(){
        return sessonName;
    }

    public String getSessonDesc(){
        return sessonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "sessonName='" + sessonName + '\'' +
                ", sessonDesc='" + sessonDesc + '\'' +
                '}';
    }
}
