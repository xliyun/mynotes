package com.liyun.DesignPattern.observer.improve;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-07 23:10
 */
public class Client {
    public static void main(String[] args) {
        //创建一个WeatherData
        WeatherData weatherData = new WeatherData();
        //创建观察者
        CurrentConditions currentConditions = new CurrentConditions();
        //将观察者注册到weatherData
        weatherData.registerObserver(currentConditions);
        weatherData.registerObserver(new BaiduSite());

        //测试
        System.out.println("通知各个注册的观察者，查看信息");
        weatherData.setData(10f,100f,30.3f);
    }
}
