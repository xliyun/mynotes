package com.thread.tc6;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
 put take是阻塞方法 没有了会等待生产，满了会等待消费
 add remove是抛异常方法，没有了抛异常，满了抛异常
 **/

public class Tmall_BLOCKING_QUEUE implements Shop {

    public final int MAX_COUNT=10;

    private BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(MAX_COUNT);

    public  void push(){
        queue.add(1);
    }

    public  void take(){
        queue.remove();
    }

    public void size(){
        System.out.println("当前队列的长度为: "+queue.size());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
