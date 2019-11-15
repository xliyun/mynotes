package com.thread.tc6;

public class Main {
    public static void main(String[] args) {
        Shop tmall=new Tmall_BLOCKING_QUEUE();

        PushTarget p=new PushTarget(tmall);
        TakeTarget t=new TakeTarget(tmall);

        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();

        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();


    }
}
