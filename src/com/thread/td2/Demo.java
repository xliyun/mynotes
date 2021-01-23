package com.thread.td2;

import java.util.concurrent.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-06-02 20:04
 */
public class Demo {
    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(10);

        //10个线程来处理大量的任务
        //ThreadPoolExecutor pool=new ThreadPoolExecutor(10,10,0, TimeUnit.MICROSECONDS,new LinkedBlockingDeque<>());

        ThreadFactory tf=new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t=new Thread(r);
                return t;
            }
        };
        while(true){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程是: "+Thread.currentThread().getName());

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
