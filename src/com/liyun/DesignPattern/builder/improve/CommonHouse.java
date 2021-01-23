package com.liyun.DesignPattern.builder.improve;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-10 18:13
 */
public class CommonHouse extends HouserBuilder {
    @Override
    public void buildBasic() {
        System.out.println(" 普通房子打地基5米 ");
    }

    @Override
    public void buildWalls() {
        System.out.println(" 普通房子砌墙10cm ");
    }

    @Override
    public void roofed() {
        System.out.println(" 普通房子建屋顶 ");
    }
}
