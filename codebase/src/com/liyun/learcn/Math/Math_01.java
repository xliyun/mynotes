package com.liyun.learcn.Math;
/*
java.util.Math类是数学相关的工具类，里面提供了大量的静态方法
public static double abs(double num): 获取绝对值。
public static double ceil(double num): 向上取整。12.1取13
public static double floor(double num): 向下取整。
public static long round(double num): 四舍五入
Math.Pi代表近似的圆周率常量
 */
public class Math_01 {
    public static void main(String[] args) {
        System.out.println("绝对值："+Math.abs(-3.14)+"\n向上取整: "+Math.ceil(3.14)+"\n向下取整: "+Math.floor(3.14)
        +"\n四舍五入: "+Math.round(3.14));
        System.out.println();
    }
}
