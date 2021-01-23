package com.liyun.DesignPattern.iterator;

import java.util.Iterator;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-07 10:30
 */
public class ComputerCollegeIterator implements Iterator {

    //这里我们需要知道Department是以怎样的方式存放的
    Deparment[] deparments;
    int position = 0;//遍历的位置

    public ComputerCollegeIterator(Deparment[] deparments) {
        this.deparments = deparments;
    }

    //判断是否还有下一个
    @Override
    public boolean hasNext() {
        if(position>=deparments.length ||deparments[position]==null){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        Deparment deparment = deparments[position];
        position+=1;
        return deparment;
    }

    //删除的方法默认不实现
    public void remove(){

    }
}
