package com.liyun.DesignPattern.builder.improve;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-10 18:10
 */

// 抽象的建造者
public abstract class HouserBuilder {

    protected House house=new House();

    //将建造的流程写好，抽象的方法
    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();

    //建造好房子，将产品（房子）返回
    public House buildHouse(){
        return house;
    }
}
