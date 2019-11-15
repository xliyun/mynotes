package com.liyun.DesignPattern.facade;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 10:57
 */
public class DVDPlayer {

    //使用单例模式，使用饿汉式
    private static DVDPlayer instance=new DVDPlayer();

    public static DVDPlayer getInstance(){
        return instance;
    }

    public void on(){
        System.out.println("DVD打开");
    }

    public void off(){
        System.out.println("DVD关闭");
    }

    public void play(){
        System.out.println("DVD播放");
    }

    public void pause(){
        System.out.println("DVD暂停");
    }
}
