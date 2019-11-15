package com.thread.t1;

public class Demo3 {
    public static void main(String[] args) {
        /**
         * 如果线程只执行一次
         * 匿名内部类继承Thread类 new Thread(){}
         */
        new Thread(){
            public void run(){
                System.out.println("继承Thread方法的线程执行了..");
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("重写Runnable方法的线程执行了...");
            }
        }).start();
    }
}
