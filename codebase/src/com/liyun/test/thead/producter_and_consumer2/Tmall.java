package com.liyun.test.thead.producter_and_consumer2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-05 13:00
 */
public class Tmall {
    private int count;

    private Lock lock=new ReentrantLock();

    Condition p=lock.newCondition();
    Condition t=lock.newCondition();

    public final int MAX_COUNT=10;

    public void push(){
        lock.lock();
        while(count>=MAX_COUNT){//不能用if,如果用if (简单的将就是一直生产)线程调用wait会释放synchronized锁，当在wait处的生产者线程被叫醒时，就往下执行，结束了不生产了
            try {
                System.out.println(Thread.currentThread().getName()+" 库存数量达到上限，生产者停止生产");
                p.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName()+" 生产者生产，当前库存为："+count);
        t.signal();//通知所有消费者消费
        lock.unlock();
    }

    public void take(){
        lock.lock();
        System.out.println("消费前");
        while(count<=0){
            try {
                System.out.println(Thread.currentThread().getName()+" 库存数量为0，消费者等待...");
                t.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName()+" 消费者消费，当前库存为："+count);
        p.signal();//叫醒所有的生产者
        lock.unlock();
    }
}
