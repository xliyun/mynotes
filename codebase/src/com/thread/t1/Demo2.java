package com.thread.t1;

/**
 * run作为线程任务存在
 * 继承Runnable()方法，匿名内部类方法
 *
 */
public class Demo2 implements Runnable {
    public static void main(String[] args) {
        Thread thread=new Thread(new Demo2());
        thread.setDaemon(true);
        //就是调用thread类的run方法
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(true){
            System.out.println("线程正在执行");
        }
    }
}
