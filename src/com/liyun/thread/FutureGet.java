package com.liyun.thread;

import java.util.concurrent.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-26 9:45
 */
public class FutureGet {
    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {

      Thread thread1  = new Thread(new Runnable() {
          @Override
          public void run() {

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


          }
      });

        Thread thread2  = new Thread(new Runnable() {
            @Override
            public void run() {

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

            }
        });

        thread1.start();
        thread2.start();


    }
}
