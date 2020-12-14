package com.liyun.DesignPattern.observer.improve;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-07 22:45
 */
public class CurrentConditions implements Observer{
    //温度、气压、湿度
    private float tempaerature;
    private float pressure;
    private float humidity;

    //更新 天气情况，是由WeatherData来调用，我使用推送模式
    public void update(float temperature,float pressure,float humidity){
        this.tempaerature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    //显示
    public void display(){
        System.out.println("*** 今天的温度是："+tempaerature+"***");
        System.out.println("*** 今天的气压是："+pressure+"***");
        System.out.println("*** 今天的湿度是："+humidity+"***");

    }
}
