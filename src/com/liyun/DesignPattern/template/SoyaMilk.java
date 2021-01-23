package com.liyun.DesignPattern.template;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 10:07
 */
//抽象类，表示豆浆
public abstract class SoyaMilk {
    //模板方法，make模板方法，不子类去覆盖
    final void make(){
        select();
        addCodiments();
        soak();
        beat();
    }

    //选材料
    void select(){
        System.out.println("第一步：选择新鲜的黄豆");
    }

    //添加不同的配料，抽象方法，子类自行实现
    abstract void addCodiments();

    //浸泡
    void soak(){
        System.out.println("第三步：黄豆和配料浸泡三小时");
    }

    void beat(){
        System.out.println("第四步：黄豆和配料放到豆浆机去打碎");
    }
}
