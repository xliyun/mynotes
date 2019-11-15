package com.thread.t8;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private boolean isLocked=false;

    Thread lockBy = null;

    private int lockCount=0;
    @Override
    /**
     * 加了synchronized后,一次只能一个线程(或同一个线程)来执行这个方法
     * 第一个线程执行lock方法，获取MyLock.class的锁，并且将isLock置为true，lockBy赋值为当前线程，lockCount++然后执行被锁住的代码
     *
     *
     */
    public synchronized void lock() {
        Thread currentThread=Thread.currentThread();//当前线程实例

        // 第一个线程isLocked为false,就不会等待，然后将isLocked赋值为true
        while(isLocked && currentThread!=lockBy) //不能用if 只要isLocked不是true就等待，自旋
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isLocked=true;
        lockBy=currentThread;
        lockCount++;

    }

    @Override
    public synchronized void unlock() {
        //如果执行完毕的是同一个线程lockCount-- 当当前线程都执行完毕的时候，就可以唤醒了
        if(lockBy == Thread.currentThread()){
            lockCount--;
            if(lockCount==0){
                notify();
                isLocked=false;
            }
        }

        isLocked=false;
        notify();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
