package com.thread.t8;

public class Sequnce {
    private MyLock lock=new MyLock();

    private int value;

    public int getNext(){
        lock.lock();

        value++;

        lock.unlock();
        return value;
    }

    public static void main(String[] args) {
        Sequnce sequnce=new Sequnce();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(Thread.currentThread().getName()+" "+sequnce.getNext());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(Thread.currentThread().getName()+" "+sequnce.getNext());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(Thread.currentThread().getName()+" "+sequnce.getNext());
                }
            }
        }).start();
    }
}
