package com.thread.td1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**；；；、
 * @description:
 * @author: xiaoliyu
 * @date: 2019-06-02 9:09
 */
public class Demo {
    public static void main(String[] args) {
        ThreadPoolExecutor   threadPool=new ThreadPoolExecutor(10,20 ,10, TimeUnit.DAYS,new ArrayBlockingQueue<>(10),new ThreadPoolExecutor.DiscardOldestPolicy());

        AtomicInteger count=new AtomicInteger();
        for(int i=0;i<100;i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程是: "+Thread.currentThread().getName());
                    count.getAndIncrement();
                }
            });
        }
        threadPool.shutdown();

        while(Thread.activeCount()>1){

        }

        System.out.println(count.get());
    }
}
