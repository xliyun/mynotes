package com.liyun.DesignPattern.adapter.objectadapter;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-14 13:03
 */
//被适配的类
public class Voltage220V {
    //输出220V的电压
    public int output220(){
        int src=220;
        System.out.println("电压="+src+"伏");
        return src;
    }
}
