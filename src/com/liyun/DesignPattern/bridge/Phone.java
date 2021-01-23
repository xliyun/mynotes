package com.liyun.DesignPattern.bridge;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-15 15:09
 */
public abstract class Phone {

    //组合品牌
    private Brand brand;

    //构造器
    public Phone(Brand brand) {
        this.brand = brand;
    }

    protected void open(){
        this.brand.open();
    }

    protected void close(){
        this.brand.close();
    }

    protected void call(){
        this.brand.call();
    }
}
