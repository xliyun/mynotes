package com.thread.tb6;

import java.util.concurrent.Semaphore;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-18 15:57
 */
public class Demo {
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
        Demo demo=new Demo();
        //最多同时有10个线程执行，其他的线程就等待
        Semaphore semaphore=new Semaphore(10);

        for(int i=0;i<100;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.method(semaphore);
                }
            }).start();
        }
    }
}
