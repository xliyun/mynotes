package com.thread.test;



/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-06-24 10:16
 */
public class demo {

    public volatile  int value;

    public demo(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        demo d=new demo(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.value++;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.value++;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.value++;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.value++;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.value++;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.value++;
            }
        }).start();

        System.out.println(d.value);
    }
}
