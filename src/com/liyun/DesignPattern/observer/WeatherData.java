package com.liyun.DesignPattern.observer;

/**
 * 类是核心
 * 1.包含最新的天气情况信息
 * 2.含有CurrentConditions对象
 * 3.当数据有更新时，就主动的调用CurrentConditions对象的update方法(含display),这样他们(接入方)就看到最新的信息
 *
 */
public class WeatherData {
    private float temperatrue;

    private float pressure;

    private float humidity;

    private CurrentConditions currentConditions;
    //如果有新的气象网站要加入，需要在这里加入新的第三方，比如加入墨迹天气


    public float getTemperatrue() {
        return temperatrue;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public WeatherData(CurrentConditions currentConditions) {
        this.currentConditions = currentConditions;
    }

    public void dataChange(){
        //调用接入方的update
        currentConditions.update(getTemperatrue(),getPressure(),getHumidity());
    }

    //当数据有更新时，就调用setData
    public void setData(float temperatrue,float pressure,float humidity){
        this.temperatrue = temperatrue;
        this.pressure = pressure;
        this.humidity = humidity;
        //调用dataChange,将最新信息推送给接入方
        dataChange();
    }

}
