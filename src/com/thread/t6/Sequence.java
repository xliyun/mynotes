package com.thread.t6;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

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
    //原子性操作的Integer
    private AtomicInteger value=new AtomicInteger(0);

    private int [] s={2,1,4,6};

    //定长的
    AtomicIntegerArray a=new AtomicIntegerArray(s);

    //不是修改user的属性，是每次替换user
    AtomicReference<User> usr=new AtomicReference<>();

    //更新要改变的类的成员变量old，抽象类 初始化时写入要更新的字段
    AtomicIntegerFieldUpdater<User> old=AtomicIntegerFieldUpdater.newUpdater(User.class,"old");

    public synchronized int getNext(){
        User user=new User();
        old.getAndIncrement(user);
        old.getAndIncrement(user);
        old.getAndIncrement(user);
        System.out.println(user.getOld());

        //原子操作仅仅是对usr实例进行get和set


        //给2号位置++
        a.getAndIncrement(2);
        //给2号位置加3
        a.getAndAdd(2,3);



        //获取然后++
        return value.getAndIncrement();
    }

}
