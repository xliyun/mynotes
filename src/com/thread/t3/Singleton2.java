package com.thread.t3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Singleton2 {
    public static void main(String[] args) {
        ExecutorService threadPool=Executors.newFixedThreadPool(20);

        for (int i = 0; i < 20; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":"+Singleton2.getInstance());
                }
            });
        }
        threadPool.shutdown();
    }
    //通过私有化构造方法，保证实例唯一,别人不能new
    private Singleton2(){}

    private static volatile Singleton2 instance;


    /**
     * 偏向锁，单个线程在访问的时候，不释放锁，如果有别的线程来竞争，再释放锁
     * 轻量级锁，第一个线程拿到锁，第二个线程一直获取锁(自旋)，但是这个方法会sleep200毫秒，第二个线程自旋200毫秒，消耗cpu资源比如下面
     * 只有第一次初始化的时候才会出现线程性问题，所以应该只在初始化的时候加锁
     * @return
     */
    //懒汉模式，只有在第一次使用的时候才创建对象
    //自旋，消耗资源
/*    public static synchronized Singleton2 getInstance(){
        //非原子性操作
        if(instance==null) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton2();
        }
        return instance;
    }*/
        //双重检查加锁，依然不是线程安全，指令重排序问题
    public static  Singleton2 getInstance(){
        //非原子性操作
        if(instance==null) {
            synchronized(Singleton2.class) {
                if(instance==null)
                instance = new Singleton2();//对这一条指令进行重排序
                /**
                 * 为了提高指令的性能，虚拟机会对指令进行优化，在不影响程序最终结果的情况下，
                 * 把一些后面的指令移到前面去
                 *
                 * 1.申请一块内存空间
                 * 2.在这块空间里实例化对象
                 * 3.instance引用指向这块空间地址
                 *
                 * 执行的时候很用可能是1.3.2
                 * 出现的问题：
                 * 比如线程A进来，在执行完3，但是还没有执行2时，线程B判断instance是否为空，就会判断错误
                 *
                 *
                 * 使用volatile禁止指令重排序
                 */
            }
        }
        return instance;
    }
}
