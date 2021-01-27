package com.liyun.thread;

import java.util.concurrent.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-26 9:45
 */
public class FutureGet2 {
    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        FutureGet2 futureGet2 = new FutureGet2();

        System.out.println( futureGet2.FutureGetReturn());
    }

    public String FutureGetReturn(){
        Future<String> submit1 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return "aaa";
            }
        });
        try {
            String s = submit1.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Future<String> submit2 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "bbb";
            }
        });
        try {
            String s = submit2.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return "哈喽";
    }
}
