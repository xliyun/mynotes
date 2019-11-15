package com.thread.t5;

/**
 * 轻量级锁就好像while等待
 * volatile可见性只能保证变量的原子性操作，
 */
public class Demo2 {
    private volatile boolean run=false;

    public boolean isRun() {
        //volatile可见性只能保证变量的原子性操作，非原子性操作不能保证，读
        //run=!run;
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public static void main(String[] args) {
        Demo2 demo2=new Demo2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("执行了第"+i+"次");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                demo2.setRun(true);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!demo2.isRun()){
                    //执行
                }
                /*
                 如果run不是被volatile修饰，那么就不会执行下面的代码，volatile修改后会强制将修改的值立即写入主存，导致while线程的变量demo2.isRun()的缓存行无效，所以会立刻去主存中取最新值。
                 因为while线程会将!demo2.isRun()拷贝一份放到高速缓存中，线程对变量的操作都在高速缓存中进行，而不能直接对内存进行操作，并且每个线程不能访问其他线程的工作内存(高速缓存)
                 demo2.setRun(true);没有
                 */
                System.out.println("线程2执行了");

            }
        }).start();
    }
}
