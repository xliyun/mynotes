package com.liyun.DesignPattern.facade;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 11:04
 */
public class Screen {
    private static Screen instance=new Screen();

    public static Screen getInstance(){
        return instance;
    }

    public void up(){
        System.out.println(" 屏幕 上升");
    }

    public void down(){
        System.out.println(" 屏幕 下降");
    }

    public void on(){
        System.out.println(" 屏幕 打开 ");
    }

    public void off(){
        System.out.println(" 屏幕 关闭");
    }
}
