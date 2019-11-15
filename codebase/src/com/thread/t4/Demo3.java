package com.thread.t4;

public class Demo3 {
    public static void main(String[] args) {
        Demo3 demo3=new Demo3();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo3.a();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo3.b();
            }
        }).start();
    }
    private Object obj1=new Object();
    private Object obj2=new Object();

    public void a(){
        //拿到1锁后去拿2锁
        synchronized (obj1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(obj2){
                System.out.println("a方法");
            }
        }
    }

    public void b(){
        //拿到2锁后去拿1锁
        synchronized (obj2){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1){
                System.out.println("b方法");
            }
        }
    }


}
