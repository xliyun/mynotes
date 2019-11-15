package com.thread.ta3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Demo {
    private Map<String,Object> map=new HashMap<>();

    private ReadWriteLock rwl=new ReentrantReadWriteLock();

    private Lock r=rwl.readLock();

    private Lock w=rwl.writeLock();

    private volatile boolean isUpdate;//保证线程可见

    //同时存在读和写两种操作
    public void readWrite(){
        r.lock();//加读锁，读取isUpdate
        if(isUpdate){
            r.unlock();
            //1.读锁释放以后，所有的写线程竞争写锁

            w.lock();
            map.put("xxxx","xxxx");
            r.lock();//2.写锁释放后，所有的写线程还会来竞争写锁，为了不让其他写线程进来，就将写锁降级，降成读锁
            w.unlock();
        }

        Object obj=map.get("xxxx");

        System.out.println(obj);
        r.unlock();
    }

    public Object get(String key){
        r.lock();
        System.out.println(Thread.currentThread().getName()+"读操作在执行===");

        try {
            try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
            return map.get(key);
        }finally{
            r.unlock();
            System.out.println(Thread.currentThread().getName()+"读操作执行完毕===");

        }

    }

    public void put(String key,Object value){
        w.lock();
        System.out.println(Thread.currentThread().getName()+"写操作在执行！！！");

        try {
            try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
            map.put(key, value);
        }finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName()+"写操作执行完毕！！！");
        }
    }
    public static void main(String[] args) {

        Demo d=new Demo();


/*      //读锁和写锁会互斥

      new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key1","value1");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key2","value2");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("key1"));
            }
        }).start();*/

        d.put("a",32);
        d.put("b",64);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("a"));
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("b"));
            }
        }).start();
    }
}
