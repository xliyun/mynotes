package com.thread.t2;

/**
 * 线程优先级，最小为1，最大为10 不同的操作系统对线程的支持不同
 * public final static int MIN_PRIORITY = 1;
 * public final static int MAX_PRIORITY = 10;
 */
public class t2Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new Target());
        Thread t2=new Thread(new Target());
        Thread t3=new Thread(new Target());
        Thread t4=new Thread(new Target());

        //设置优先级
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();

        Thread.sleep(2000);
        t1.interrupt();
        t2.interrupt();
    }
}
