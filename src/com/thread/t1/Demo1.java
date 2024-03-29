package com.thread.t1;

/**
 * 继承Thread类方式
 */
public class Demo1 extends Thread {

    public Demo1(String name){
        super(name);
    }

    @Override
    public void run(){
        //这里不能用while(true)不然主线程调用了中断，它都会继续执行
        while(!interrupted()){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName()+"线程执行了..");
        }
    }

    public static void main(String[] args) {
        Demo1 demo1=new Demo1("first-Thread");
        Demo1 demo2=new Demo1("second-Thread");

        //支持性线程，主线程执行完毕后，如果这个线程没有执行完毕，依然会退出
        demo1.setDaemon(true);

        //判断当前线程是否中断
        System.out.println(demo1.isInterrupted());
        demo2.setDaemon(true);
        demo1.start();
        demo2.start();

        /**
         * 中断线程,1.6之前使用stop,但是这个方法没有释放锁和资源
         * interrupt()是尽量释放资源后中断,所以上面while(true)不会中断，
         * 改成while(!interrupted()){}
         */

        //demo1调用了中断
        demo1.interrupt();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
