package com.liyun.DesignPattern.builder.improve;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-14 9:35
 */
//指挥者，这里取指定制作流程，返回产品
public class HouseDirector {
    HouserBuilder houserBuilder=null;

    //构造器传入 houseBuilder

    public HouseDirector(HouserBuilder houserBuilder) {
        this.houserBuilder = houserBuilder;
    }

    //通过setter 传入houseBuilder


    public void setHouserBuilder(HouserBuilder houserBuilder) {
        this.houserBuilder = houserBuilder;
    }

    //如何建造房子的流程，交给指挥者
    public House construcHouse(){
        houserBuilder.buildBasic();
        houserBuilder.buildWalls();
        houserBuilder.roofed();
        return houserBuilder.buildHouse();
    }
}
