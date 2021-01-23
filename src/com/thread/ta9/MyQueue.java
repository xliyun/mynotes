package com.thread.ta9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue<E> {

    private Object[] obj;

    private int addIndex;
    private int removeIndex;
    private int queueSize;

    private Lock lock=new ReentrantLock();

    Condition addCondition=lock.newCondition();
    Condition removeCondition=lock.newCondition();

    public MyQueue(int count) {
        obj=new Object[count];
    }

    public void push(E e){
        lock.lock();
        //生产满了就挂起生产者
        while(queueSize==obj.length){
            try {
                addCondition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        obj[addIndex]=e;
        if(++addIndex==obj.length-1){
            addIndex=0;
        }
        queueSize++;
        //生产者生产商品后唤醒消费者
        removeCondition.signal();
        lock.unlock();
    }

    public void pop(E e){
        lock.lock();
        //如果队列里没有商品了，就挂起消费者
        while(queueSize==0){
            try {
                System.out.println(Thread.currentThread().getName()+"队列为空，不进行移除...");
                removeCondition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        obj[removeIndex++]=null;
        if(++removeIndex==obj.length){
            removeIndex=0;
        }
        queueSize--;
        addCondition.signal();
        lock.unlock();
    }

}
