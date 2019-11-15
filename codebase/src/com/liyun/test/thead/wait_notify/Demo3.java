package com.liyun.test.thead.wait_notify;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-04 21:17
 */
public class Demo3 {
    private volatile int signal;

    public synchronized void set(){
        this.signal=1;
        //这里释放的就是当前对象 所以不需要加d.notify()
        //notify();//notify方法会随机叫醒一个处于wait状态的线程
        notifyAll();//叫醒所有的处于wait等待的线程，争夺时间片的线程只有一个
    }

    public synchronized int get(){
        System.out.println(Thread.currentThread().getName()+" 执行了...");
            if(signal!=1){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        System.out.println(Thread.currentThread().getName()+" 方法执行完毕...");
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
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //通过t1获取
        new Thread(t1).start();
    }
}
