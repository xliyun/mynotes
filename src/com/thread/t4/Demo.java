package com.thread.t4;

public class Demo {
    public static void main(String[] args) {
        Demo demo=new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {

                demo.a();
                //如果锁不是重入的，a方法调用了b，想要用b方法，必须等a方法结束
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                demo.b();
                //如果锁不是重入的，a方法调用了b，等于获取了两次锁，
            }
        }).start();
    }
    //这样用synchronized加锁，就是用的对象的实例
    public synchronized void a(){
        System.out.println("a");
        b();
    }

    public synchronized void b(){
        System.out.println("b");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
