package com.liyun.DesignPattern.visitor;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 15:52
 */
public abstract class Visitor {

    //得到男性 的测评
    public abstract void getManResult(Man man);

    //得到女的 测评
    public abstract void getWomanResult(Woman woman);

}
