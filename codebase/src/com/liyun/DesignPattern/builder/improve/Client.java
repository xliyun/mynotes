package com.liyun.DesignPattern.builder.improve;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-14 9:45
 */
public class Client {
    public static void main(String[] args) {
        //盖普通房子
        CommonHouse commonHouse = new CommonHouse();
        //准备创建房子的指挥者
        HouseDirector houseDirector = new HouseDirector(commonHouse);

        //完成盖房子，返回产品（房子）
        House house = houseDirector.construcHouse();

        //System.out.println("输出流程：");
        System.out.println("-------------------------");

        //盖高楼
        HighBuilding highBuilding = new HighBuilding();

        //重置建造者
        houseDirector.setHouserBuilder(highBuilding);

        //完成盖房子，返回产品（高楼）
        houseDirector.construcHouse();
    }
}
