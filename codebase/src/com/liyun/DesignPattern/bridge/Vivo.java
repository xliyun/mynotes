package com.liyun.DesignPattern.bridge;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-15 15:06
 */
public class Vivo implements Brand {
    @Override
    public void open() {
        System.out.println(" Vivo手机开机 ");
    }

    @Override
    public void close() {
        System.out.println(" Vivo手机关机 ");
    }

    @Override
    public void call() {
        System.out.println(" Vivo手机打电话 ");
    }
}
