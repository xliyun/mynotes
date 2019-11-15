package com.thread.tc1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable和Runnable的区别
 *
 * Runnable是被线程调用的，在run方法是异步执行的
 *
 * Callable的call方法，不是异步执行的，是由FutureTask的run调用的
 *
 *
 *
 *
 */
public class Demo {
    public static void main(String[] args) {
        Callable<Integer> call=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("正在抓取数据...");
                Thread.sleep(3000);
                return 1;
            }
        };

        //call里的run方法由FutureTask调用，详情看FutureTask的run方法
        FutureTask<Integer> task=new FutureTask<>(call);

        Thread thread=new Thread(task);
        thread.start();

        //去做其他事
        System.out.println("正在洗菜...");
        Integer result= null;
        try {
            result = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("拿到结果"+result);
    }
}
