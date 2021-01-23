package com.thread.tb5;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-12 11:10
 */
public class Demo {

    public void meeting(CyclicBarrier barrier){
        Random random=new Random();
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" 进入会场，准备开会");

        if(Thread.currentThread().getName().equals("Thread-1")){
            throw new RuntimeException();
        }

        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" 报道！");

    }

    public static void main(String[] args) {
        Demo demo=new Demo();
        CyclicBarrier barrier=new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("十个线程运行完毕，开始开会!");
            }
        });

        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.meeting(barrier);
                }
            }).start();
        }
    }
}
