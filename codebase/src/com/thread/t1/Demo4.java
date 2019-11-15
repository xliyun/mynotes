package com.thread.t1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * public class FutureTask<V> implements RunnableFuture<V>
 *   RunnableFuture<V> extends Runnable, Future<V>
 */
public class Demo4 implements Callable<Integer> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Demo4 d=new Demo4();

        FutureTask<Integer> task=new FutureTask<>(d);
        Thread t=new Thread(task);
        t.start();

        //打印线程执行结果
        Integer it=task.get();
        System.out.println("线程执行的结果为："+it);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("正在执行线程");
        Thread.sleep(3000);
        return 1;
    }
}
