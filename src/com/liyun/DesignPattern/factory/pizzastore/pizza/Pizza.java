package com.liyun.DesignPattern.factory.pizzastore.pizza;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-06 16:50
 */
//将披萨类做成抽象
public abstract class Pizza {
    protected String name;//名字

    //准备原材料，不同的披萨是不一样的，因此我们做成抽象方法
    public abstract void prepare();
    public void bake() {
        System.out.println(name + " baking;");
    }
    public void cut() {
        System.out.println(name + " cutting;");
    }

    //打包
    public void box() {
        System.out.println(name + " boxing;");
    }
    public void setName(String name) {
        this.name = name;
    }
}
