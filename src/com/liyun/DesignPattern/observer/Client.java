package com.liyun.DesignPattern.observer;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-07 22:04
 */
public class Client {
    public static void main(String[] args) {
        //创建接入方currentConditions
        CurrentConditions currentConditions = new CurrentConditions();
        //创建WeatherData 并将接入方
        WeatherData weatherData =new WeatherData(currentConditions);
        //更新天气情况
        weatherData.setData(30,150,40);

        //天气情况变化
        System.out.println("===========天气情况发生了变化==========");
        weatherData.setData(40,160,20);

    }
}
