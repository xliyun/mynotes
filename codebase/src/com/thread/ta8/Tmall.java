package com.thread.ta8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tmall {
    private int count;

    Lock lock=new ReentrantLock();

    Condition push=lock.newCondition();
    Condition take=lock.newCondition();

    public final int MAX_COUNT=10;

    public  void push(){
        lock.lock();
        while(count>=MAX_COUNT) {//防止生产者被叫醒之后直接运行下面的++，也不管count是不是大于10
            try {
                System.out.println(Thread.currentThread().getName()+" 生产者库存数量达到上限，生产者停止生产...");
                push.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName()+" 生产和生产，当前库存为"+count);
        take.signal();
        lock.unlock();
    }

    public  void take(){
        lock.lock();
        while(count<=0) {
            try {
                System.out.println(Thread.currentThread().getName()+" 库存为零，消费者等待...");
                take.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName()+" 消费者消费，当前库存为"+count);
        push.signal();
        lock.unlock();
    }
}
