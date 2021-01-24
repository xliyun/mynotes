package com.liyun.test.lambda.l1;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-25 9:19
 */
public class lamdbda_01 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"当前线程建立了");
            }
        }).start();

        //使用lambdad表达式，实现多线程
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }).start();

        //优化省略
        new Thread(()-> System.out.println(Thread.currentThread().getName()+"省略版lambda表达式启动了")).start();
    }
}
