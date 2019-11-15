package com.liyun.DesignPattern.visitor;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 15:56
 */
public class SuccessVisitor extends Visitor {
    @Override
    public void getManResult(Man man) {
        System.out.println(" 男人给的评价是该歌手很成功！");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println(" 女人给的评价是该歌手很成功");
    }
}
