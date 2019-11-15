package com.liyun.DesignPattern.adapter.objectadapter;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-14 13:23
 */
public class Phone {

    //充电
    public void charging(IVoltage5V iVoltage5V){
        if(iVoltage5V.output5V()==5){
            System.out.println("电压为5V，可以充电。");
        }else if(iVoltage5V.output5V()>5){
            System.out.println("电压大于5V，不可以充电！");
        }
    }
}
