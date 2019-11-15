package com.thread.ta4;

public class Single {
    private volatile int signal;

    public void set(int value){
        this.signal=value;
    }
    public int get(){
        return signal;
    }

    public static void main(String[] args) {

        Single s=new Single();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("修改状态的线程执行...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //线程为1后执行一些代码
                s.set(1);
                System.out.println("状态值修改成功...");
                notify();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //等待signle为1开始执行，否则不能执行
                while(s.get()!=1){//while太消耗资源
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //当信号为1时，执行代码
                System.out.println("状态值修改成功...");
            }
        }).start();

    }
}
