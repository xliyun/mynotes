package com.liyun.artofjavaconcurrency.code4_7;

public class Interrupted {
    public static void main(String[] args) {
        //sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(),"SleepThread");
        sleepThread.setDaemon(true);
        //busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner(),"BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        //休眠5秒，让sleepThread和busyThread充分运行
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread的中断标志是： "+sleepThread.isInterrupted());
        System.out.println("BusyThread的中断标志是： "+busyThread.isInterrupted());

        //防止sleepThread和busyThread立刻退出
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SleepRunner implements Runnable{

        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements  Runnable{

        @Override
        public void run() {
            while(true){

            }
        }
    }
}
