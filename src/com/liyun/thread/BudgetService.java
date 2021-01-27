package com.liyun.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-26 10:38
 */
public class BudgetService {
    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        BudgetService budgetService = new BudgetService();


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    BudgetCheckReturnCO budgetCheckReturnCO = budgetService.saveBudgetCheckAsync("mythread-1");
                    System.out.println(budgetCheckReturnCO.getMessageLevel());
                }
            }, "mythread-1");


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                BudgetCheckReturnCO budgetCheckReturnCO = budgetService.saveBudgetCheckAsync("mythread-2");
                System.out.println(budgetCheckReturnCO.getMessageLevel());
            }
        }, "mythread-2");

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                BudgetCheckReturnCO budgetCheckReturnCO = budgetService.saveBudgetCheckAsync("mythread-3");
                System.out.println(budgetCheckReturnCO.getMessageLevel());
            }
        }, "mythread-3");


        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                BudgetCheckReturnCO budgetCheckReturnCO = budgetService.saveBudgetCheckAsync("mythread-4");
                System.out.println(budgetCheckReturnCO.getMessageLevel());
            }
        }, "mythread-4");
        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                BudgetCheckReturnCO budgetCheckReturnCO = budgetService.saveBudgetCheckAsync("mythread-5");
                System.out.println(budgetCheckReturnCO.getMessageLevel());
            }
        }, "mythread-5");
        Thread thread6 = new Thread(new Runnable() {
            @Override
            public void run() {
                BudgetCheckReturnCO budgetCheckReturnCO = budgetService.saveBudgetCheckAsync("mythread-6");
                System.out.println(budgetCheckReturnCO.getMessageLevel());
            }
        }, "mythread-6");


        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

    }

    public BudgetCheckReturnCO saveBudgetCheckAsync(String threadName){

        BudgetCheckReturnCO budgetCheckReturnCO = new BudgetCheckReturnCO(null,null);

        Future<BudgetCheckReturnCO> submit1 = executorService.submit(new Callable<BudgetCheckReturnCO>() {
            @Override
            public BudgetCheckReturnCO call() throws Exception {
                Random random = new Random();
                int i;
                i=random.nextInt(10000);
                Thread.sleep(Long.valueOf(i));
                budgetCheckReturnCO.setMessageLevel(threadName+"休眠："+i+"    对象hashcode:"+budgetCheckReturnCO);
                return budgetCheckReturnCO;
            }
        });
        try {
           return submit1.get();

        } catch (InterruptedException e) {
            budgetCheckReturnCO.setMessageLevel("InterruptedException");
            e.printStackTrace();
        } catch (ExecutionException e) {
            budgetCheckReturnCO.setMessageLevel("ExecutionException");
            e.printStackTrace();
        }

        return budgetCheckReturnCO;
    }
}
