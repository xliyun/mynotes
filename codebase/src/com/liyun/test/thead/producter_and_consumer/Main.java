package com.liyun.test.thead.producter_and_consumer;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-05 13:08
 */
public class Main {
    public static void main(String[] args) {
        Tmall tmall=new Tmall();
        PushTarget p=new PushTarget(tmall);
        TakeTarget t=new TakeTarget(tmall);

        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();

        new Thread(t).start();
    }
}
