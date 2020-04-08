package com.liyun.learn.enumtest;

/**
 * 一、枚举的使用
 * 1.枚举的理解：类的对象只有有限个，确定的。我们称此为枚举类
 * 2.当需要定义一组常量时，强烈建议使用枚举类
 * 3.如果枚举类中有一个对象，则可以作为单例模式的实现方式
 *
 * 二、如何定义枚举类
 * 方式一：jdk5.0之前，自定义枚举类
 * 方式二：jdk5.0，可以使用enum关键字定义 枚举类
 *
 *三、Enum类中的常用方法
 * values()方法：返回枚举类型的对象数组。该方法可以很方便 的遍历所有的枚举值。
 * valueOf(String str)：可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对象的“名字”。如不是，会有运行时异常：IllegalAragumentException
 * toString():返回当前枚举类对象常量的名称
 *
 * 四、使用enum关键字定义的枚举类实现接口的情况
 *  情况一：实现接口，在enum勒种实现抽象方法
 *  情况二：
 */
public class SeasonTest {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;
        //toString方法
        System.out.println(summer);

        System.out.println(Season.class.getSuperclass());

        //values方法
        Season1[] values = Season1.values();
        for (Season1 value : values) {
            System.out.println(value);
        }

        Thread.State[] values1 = Thread.State.values();
        for (Thread.State state : values1) {
            System.out.println(state);
        }

        //valueOf(String str)：返回枚举类中对象名是objName的对象
        //如果没有objName的枚举类对象，则抛异常:IllegalArgumentException
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);

        winter.show();
    }
}

