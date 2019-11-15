package com.liyun.test.thead.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-05 16:51
 */
public class Demo2 {

    private int signal;

    Lock lock=new ReentrantLock();
    Condition a=lock.newCondition();
    Condition b=lock.newCondition();
    Condition c=lock.newCondition();

    public void a(){
        lock.lock();
        while(signal!=0){
            try {
                a.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a线程执行完毕");
        signal++;
        b.signal();
        lock.unlock();
    }

    public void b(){
        lock.lock();
        while(signal!=1){
            try {
                b.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b线程执行完毕");
        signal++;
        c.signal();
        lock.unlock();
    }

    public void c(){
        lock.lock();
        while(signal!=2){
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c线程执行完毕");
        signal=0;
        c.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        Demo2 d=new Demo2();
        A a=new A(d);
        B b=new B(d);
        C c=new C(d);

        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }
}

class A implements Runnable{

    private Demo2 demo;

    public A(Demo2 demo){
        this.demo=demo;
    }

    @Override
    public void run() {
        while (true){
            demo.a();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class B implements Runnable{

    private Demo2 demo;

    public B(Demo2 demo){
        this.demo=demo;
    }

    @Override
    public void run() {
        while (true){
            demo.b();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class C implements Runnable{

    private Demo2 demo;

    public C(Demo2 demo){
        this.demo=demo;
    }

    @Override
    public void run() {
        while (true){
            demo.c();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}