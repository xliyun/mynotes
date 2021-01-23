package com.liyun.test.thead.wait_notify;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-05 12:27
 */
public class Target1 implements Runnable{

    private Demo3 demo;

    //为了拿到相同的demo
    public Target1(Demo3 demo){
        this.demo=demo;
    }

    @Override
    public void run() {
        demo.set();
    }
}
