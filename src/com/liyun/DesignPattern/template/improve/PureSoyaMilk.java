package com.liyun.DesignPattern.template.improve;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 10:38
 */
public class PureSoyaMilk extends SoyaMilk {
    @Override
    void addCodiments() {
        //空实现
    }

    //不实现豆浆
    @Override
    boolean customerWantCondiments() {
        return false;
    }
}
