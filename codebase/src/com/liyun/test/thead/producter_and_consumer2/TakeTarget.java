package com.liyun.test.thead.producter_and_consumer2;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-05 13:06
 */
public class TakeTarget implements Runnable {

    private Tmall tmall;
    public  TakeTarget(Tmall tmall){
        this.tmall=tmall;
    }
    @Override
    public void run() {
        while (true){
            tmall.take();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
