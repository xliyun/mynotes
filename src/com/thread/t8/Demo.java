package com.thread.t8;

public class Demo {
    public static void main(String[] args) {
        Demo demo=new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.a();
            }
        }).start();
    }
    MyLock lock=new MyLock();
    public void a(){
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }
    public void b(){
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }
}
