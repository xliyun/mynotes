package com.liyun.DesignPattern.adapter.objectadapter;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-14 13:20
 */
//适配器类
public class VoltageAdapter   implements IVoltage5V {

    private Voltage220V voltage220V;

    //通过构造器，传入一个Voltage220V 实例
    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {

        int dst=0;
        if(voltage220V!=null){
            //获取到220V的电压
            int src=voltage220V.output220();
            System.out.println("使用对象适配器，进行转换。。");
            dst=src/44;
            System.out.println("适配完成：输出的电压为："+dst);
        }
        return dst;
    }
}
