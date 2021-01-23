package com.liyun.test.thead;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-04 21:17
 */
public class Demo2 {
    private volatile int signal;

    public void set(int value){
        this.signal=value;
    }

    public int get(){
        return signal;
    }

    public static void main(String[] args) {
        //
        Demo2 d=new Demo2();
        //第一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (d) {
                    System.out.println("修改状态的线程执行...");

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    d.set(1);
                    System.out.println("状态值修改成功");
                    //状态值修改成功后，叫醒线程
                    d.notify();
                }
            }
        }).start();

        //第二个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (d) {
                    //等待signal为1开始执行，否则不能执行
                    while (d.get() != 1) {//一直抢占cpu对性能损耗

                        try {
                            d.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //当信号为1时，执行代码
                    System.out.println("模拟代码执行...");
                }
            }
        }).start();
    }
}
