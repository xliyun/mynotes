package com.liyun.DesignPattern.decorator;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-16 14:31
 */
//装饰者
public class Decorator extends Drink {

    private Drink obj;

    public Decorator(Drink obj) {//组合
        this.obj = obj;
    }

    @Override
    public float cost() {

        //getPirce 自己的价格加
        return super.getPrice() + obj.cost();
    }

    @Override
    public String getDes() {

        //obj.getDes() 输出被装饰者的信息
        return super.getDes() + " " + super.getPrice()
                +" && "+obj.getDes();
    }
}
