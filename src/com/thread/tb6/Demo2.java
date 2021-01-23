package com.thread.tb6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-18 15:57
 */
public class Demo2 {
    public void method (Semaphore semaphore){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" 正在执行...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"执行完毕...");
        semaphore.release();
    }
    public static void main(String[] args) {
        Demo2 demo=new Demo2();
        //固定大小的线程池 和Semaphore的区别是 ExecutorService只能创建10个线程，执行完了才会创建新线程，
        ExecutorService threadPool= Executors.newFixedThreadPool(10);

        for(int i=0;i<100;i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" 执行完毕...");
                }
            });
        }
    }
}
