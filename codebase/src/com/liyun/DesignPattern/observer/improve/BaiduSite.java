package com.liyun.DesignPattern.observer.improve;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-07 23:13
 */
public class BaiduSite implements Observer{
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
        System.out.println("===========百度网站============");
        System.out.println("***百度天气 温度是："+tempaerature+"***");
        System.out.println("***百度天气 气压是："+pressure+"***");
        System.out.println("***百度天气 湿度是："+humidity+"***");

    }
}
