package com.liyun.DesignPattern.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-07 11:33
 */
public class OutPutImpl {
    public OutPutImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    //学院集合
    List<College> collegeList;

    //遍历所有的学院，调用printDepartment输出各个学院的系
    public void printColleget(){
        //从collegeList取出所有的college,Java的list已经实现了Iterator
        Iterator<College> iterator = collegeList.iterator();
        while(iterator.hasNext()){
            College college = iterator.next();
            System.out.println("========"+college.getName()+"========");
            Iterator interator = college.createInterator();//得到对应的迭代器
            printDepartment(interator);
        }
    }

    //输出 学院输出系
    public void printDepartment(Iterator iterator){
        while(iterator.hasNext()){
            Deparment d = (Deparment) iterator.next();
            System.out.println(d.getName());
        }
    }
}
