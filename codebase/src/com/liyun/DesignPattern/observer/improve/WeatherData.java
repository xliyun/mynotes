package com.liyun.DesignPattern.observer.improve;

import com.liyun.DesignPattern.observer.CurrentConditions;

import java.util.ArrayList;

/**
 * 类是核心
 * 1.包含最新的天气情况信息
 * 2.含有观察者集合，使用ArrayList管理
 * 3.当数据有更新时，就主动的调用ArrayList,通知所有的（接入方）就看到最新的信息
 *
 */
public class WeatherData implements Subject {
    private float temperatrue;

    private float pressure;

    private float humidity;
    //观察者集合
    private ArrayList<Observer> observers;

    //如果有新的气象网站要加入，需要在这里加入新的第三方，比如加入墨迹天气


    public WeatherData() {
        observers = new ArrayList<Observer>();
    }

    public float getTemperatrue() {
        return temperatrue;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }


    public void dataChange(){
       notifyObservers();
    }

    //当数据有更新时，就调用setData
    public void setData(float temperatrue,float pressure,float humidity){
        this.temperatrue = temperatrue;
        this.pressure = pressure;
        this.humidity = humidity;
        //调用dataChange,将最新信息推送给接入方
        dataChange();
    }
    //注册一个观察者
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    //移除一个观察者
    @Override
    public void removeObserver(Observer o) {
     if(observers.contains(o)){
         observers.add(o);
     }
    }

    //遍历所有的观察者，并通知
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperatrue,pressure,humidity);
        }
    }
}
