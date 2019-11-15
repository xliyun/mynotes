package com.liyun.DesignPattern.adapter.classadapter;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-14 13:20
 */
//适配器类
public class VoltageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public int output5V() {
        //获取到220V的电压
        int srcV=output220();
        //转换电压
        int dstV=srcV/44;
        return dstV;
    }
}
