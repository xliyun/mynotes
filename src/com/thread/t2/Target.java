package com.thread.t2;

import static java.lang.Thread.interrupted;

public class Target implements Runnable {
    @Override
    public void run() {
        while(!interrupted()){
            System.out.println(Thread.currentThread().getName()+"执行了...");
        }
    }
}
