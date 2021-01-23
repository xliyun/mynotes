package com.thread.ta5;

public class Tmall {
    private int count;

    public final int MAX_COUNT=10;

    public synchronized void push(){
        while(count>=MAX_COUNT) {//防止生产者被叫醒之后直接运行下面的++，也不管count是不是大于10
            try {
                System.out.println(Thread.currentThread().getName()+" 生产者库存数量达到上限，生产者停止生产...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName()+" 生产和生产，当前库存为"+count);
        notifyAll();
    }

    public synchronized void take(){
        while(count<=0) {
            try {
                System.out.println(Thread.currentThread().getName()+" 库存为零，消费者等待...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName()+" 消费者消费，当前库存为"+count);
        notifyAll();
    }
}
