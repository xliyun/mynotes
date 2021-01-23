package com.liyun.DesignPattern.observer.improve;

//接口，让WeahterData来实现
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
