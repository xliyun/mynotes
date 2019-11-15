package com.liyun.DesignPattern.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 16:17
 */
//数据结构，管理很多人（Man，Woman）
public class ObjectStructure {

    //维护了一个集合
    private List<Person> persons = new LinkedList<>();

    //增加到list
    public void attach(Person p){
        persons.add(p);
    }

    //移除
    public void detach(Person p){
        persons.remove(p);
    }

    //显示测评情况
    public void display(Visitor action){
        for (Person person : persons) {
            person.accept(action);
        }
    }
}
