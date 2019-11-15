package com.thread.t1;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务
 * TimerTast也是实现了Runnable
 */
public class Demo5  {
    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //实现定时任务，延时0秒，每隔1秒执行一次
                System.out.println("TimerTask执行了");
            }
        },0,1000);
    }
}
