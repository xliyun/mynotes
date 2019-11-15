package com.liyun.DesignPattern.builder.improve;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-14 9:33
 */
public class HighBuilding extends HouserBuilder{

    @Override
    public void buildBasic() {
        System.out.println(" 高楼的打地基100米");
    }

    @Override
    public void buildWalls() {
        System.out.println(" 高楼的砌墙20cm");
    }

    @Override
    public void roofed() {
        System.out.println(" 高楼的透明屋顶");
    }
}
