package com.thread.t9_reentrantLock;

public class Test {
    private int value;

    private MyLock2 lock=new MyLock2();

    public int next(){
        lock.lock();
        try {
            Thread.sleep(300);
            return value++;
        } catch (InterruptedException e) {
            //因为catch没有返回值，这里就抛出个异常
            throw new RuntimeException();
        }finally {
             lock.unlock();
        }

    }
    //判断是否重入
    public void a(){
        lock.lock();
        System.out.println("aaaaa");
        b();
        lock.unlock();
    }

    public void b(){
        lock.lock();
        System.out.println("bbbbb");
        lock.unlock();
    }

    public static void main(String[] args) {
        Test test = new Test();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(Thread.currentThread().getName()+"====="+test.next());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(Thread.currentThread().getName()+"====="+test.next());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(Thread.currentThread().getName()+"====="+test.next());
                }
            }
        }).start();
    }
}
