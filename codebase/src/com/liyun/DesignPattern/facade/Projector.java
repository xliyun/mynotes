package com.liyun.DesignPattern.facade;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 11:03
 */
public class Projector {
    private static Projector instance=new Projector();

    public static Projector getInstance(){
        return instance;
    }

    public void on(){
        System.out.println(" 镜头 打开");
    }

    public void off(){
        System.out.println(" 镜头 关闭");
    }

    public void focus(){
        System.out.println(" 镜头 正在聚焦");
    }
}
