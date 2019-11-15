package com.liyun.DesignPattern.facade;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 11:05
 */
public class Stereo {
    private static Stereo instance=new Stereo();

    public static Stereo getInstance(){
        return instance;
    }

    public void on(){
        System.out.println(" 立体声 打开");
    }

    public void off(){
        System.out.println(" 立体声 关闭");
    }

    public void up(){
        System.out.println(" 立体声调大 ");
    }
}
