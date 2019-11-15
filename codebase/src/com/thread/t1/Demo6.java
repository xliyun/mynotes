package com.thread.t1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo6 {
    public static void main(String[] args) {
        //带有十个线程的线程池
        ExecutorService threadPool= Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"正在执行");
                }
            });
        }
        threadPool.shutdown();
    }
}
