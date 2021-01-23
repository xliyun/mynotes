package com.liyun.DesignPattern.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-07 10:45
 */
public class InforCollegeIterator implements Iterator {

    List<Deparment> deparments;//信息工程学院是以List的方式存放

    int index=-1;//索引

    public InforCollegeIterator(List<Deparment> deparments) {
        this.deparments = deparments;
    }

    //判断list中还有没有下一个元素
    @Override
    public boolean hasNext() {
        if(index>=deparments.size()-1){
            return false;
        }
        index+=1;
        return true;
    }

    @Override
    public Object next() {
        return deparments.get(index);
    }

    //删除的方法默认不实现
    public void remove(){

    }
}
