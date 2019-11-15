package com.liyun.learcn.Exception;
/*
    如果finally有return语句，永远返回finally总的结果，避免该情况
 */
public class Finally_01 {
    public static void main(String[] args) {
        int a=getA();
        System.out.println(a);
    }

    private static int getA() {
        int a=10;
        try {
            return a;
        } catch (Exception e) {
            //一定会执行的代码
            return 100;
        }
    }
}
