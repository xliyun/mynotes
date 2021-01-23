package com.thread.ta4;

import com.thread.t2.Target;
/*
wait方法是Object类里的方法，当一个线程执行到wait()方法时，它就进入到一个和该对象相关的等待池中，同时释放了锁对象，等待期间可以调用里面的同步方法，其他线程可以访问，等待时不拥有CPU的执行权，否则其他线程无法获取执行权。当一个线程执行了wait方法后，必须调用notify或者notifyAll方法才能唤醒，而且是随机唤醒，若是被其他线程抢到了CPU执行权，该线程会继续进入等待状态。由于锁对象可以时任意对象，所以wait方法必须定义在Object类中，因为Obeject类是所有类的基类。
 */

import java.util.concurrent.TimeUnit;
/*线程间通信
用都能看见的signal作为通信变量
wait和notify必须在synchronized代码块或者方法中使用
 */
public class Demo3 {
    private volatile int signal;

    public synchronized void set(int value){
        this.signal=value;
        //notify();//notify方法会随机叫醒一个处于wait状态的线程
        notifyAll();//叫醒所有的处于wait线程，争夺到时间片的线程只有一个
        System.out.println("叫醒线程叫醒之后休眠3秒...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized int get(){
        System.out.println(Thread.currentThread().getName()+"方法执行了...");
        if(signal!=1){
            try {
                //执行到wait()时会释放syncronized的锁
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"方法执行完毕...");
        return signal;
    }

    public static void main(String[] args) {
         Demo3 demo3=new Demo3();
         Target1 t1=new Target1(demo3);
        Target2 t2=new Target2(demo3);


        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();

        try {
            //跟Thread.sleep都是休眠
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t1).start();
    }
}
