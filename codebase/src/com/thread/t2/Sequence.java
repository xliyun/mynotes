package com.thread.t2;

import static java.lang.Thread.interrupted;

public class Sequence {
    public static void main(String[] args) {
        Sequence sequence = new Sequence();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!interrupted()){
                    System.out.println(Thread.currentThread().getName()+" "+ sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!interrupted()){
                    System.out.println(Thread.currentThread().getName()+" "+ sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private static int value;

    /**
     * synchronized放在普通方法上，内置锁就是当前类的实例
     * 只有该线程执行完毕，释放锁，其他的线程才能再来获取锁，执行线程
     * 保证方法执行的原子性
     */
    public synchronized int getNext(){
        /**
         * ，类的实例化的对象在堆当中，是线程共享的，程序计数器是线程所独享的
         * 在字节码中分为两步
         * 读到栈里加1
         * 放到堆里
         * 如果加完1还没有放回去，被别的线程抢占了进程，那么就变成了2
         */

        return value++;
    }

    /**
     * 修饰静态方法，静态方法不是类的实例，内置锁(属于一个互斥锁，只能一个进)是当前class字节码对象Sequense.class
     */
    public static synchronized int getPrevious(){
        return value--;
    }

    public int xx(){
        /**
         * synchronized后面放对象，this或者Integer.valueOf(value)
         * 或者Sequence.class(字节码文件)
         * 如果锁的是一个对象，所有持有该对象的线程都能进入，如果锁的是一个字节码文件(字节码文件是全局唯一)，有锁重入问题
         */
        //monitorenter
        synchronized (Sequence.class) {
            if (value > 0)
                return value;
            else
                return -1;
        }
        //monitorexit
    }
    /**
     * synchronized关键字可用于修饰代码块、普通方法与静态方法；synchronized修饰代码块的时候其可以获取内置锁的对象包括this、XXX.class以及其他对象。
     *
     * 其中根据JVM内存原理，可以明确this与XXX.class不是一个对象。this指向的是该类通过new创建一个新对象后的映射;XXX.class等效于this.getClass(),即其指向的都是java运行时加载在栈中的类型。
     *
     * 三.synchronized修饰的普通方法与静态方法分别获取了什么对象的内置锁呢？
     *
     * 答案是synchronized修饰的普通方法获取的是等效于this的指向对象的内置锁，即新创建的对象本身；synchronized修饰的静态方法获取的是该方法所在类的内置锁，即XXX.class
     *
     */
}
