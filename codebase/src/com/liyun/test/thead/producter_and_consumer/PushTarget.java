package com.liyun.test.thead.producter_and_consumer;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-05 13:01
 */
public class PushTarget implements Runnable {

    private Tmall tmall;

    public PushTarget(Tmall tmall){
        this.tmall=tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.push();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
