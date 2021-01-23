package com.liyun.DesignPattern.facade;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 11:08
 */
public class TheaterLight {
    private static TheaterLight instance=new TheaterLight();

    public static TheaterLight getInstance(){
        return instance;
    }

    public void on(){
        System.out.println(" 灯光 打开");
    }

    public void off(){
        System.out.println(" 灯光 关闭");
    }

    public void dim(){
        System.out.println(" 灯光调暗 ");
    }

    public void bright(){
        System.out.println(" 灯光调亮 ");
    }
}
