package com.thread.t7;

import com.thread.t6.User;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.interrupted;

public class Sequence {
    public static void main(String[] args) {
        Sequence sequence = new Sequence();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!interrupted()){
                    System.out.println(Thread.currentThread().getName()+" "+ sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!interrupted()){
                    System.out.println(Thread.currentThread().getName()+" "+ sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private int value;
    Lock lock=new ReentrantLock();
    public synchronized int getNext(){


        lock.lock();
         value++;
        lock.unlock();
        return value;
    }

}
