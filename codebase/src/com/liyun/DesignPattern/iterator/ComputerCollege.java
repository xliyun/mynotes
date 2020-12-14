package com.liyun.DesignPattern.iterator;

import java.util.Iterator;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-07 10:58
 */
public class ComputerCollege implements College {

    Deparment[] deparments;

    int numOfDepartment = 0;//保存当前数组的对象个数

    public ComputerCollege() {
        deparments = new Deparment[5];
        addDepartment("java方向","java方向");
        addDepartment("python方向","python方向");
        addDepartment("大数据方向","大数据方向");
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        Deparment deparment = new Deparment(name, desc);
        deparments[numOfDepartment] = deparment;
        numOfDepartment+=1;
    }

    @Override
    public Iterator createInterator() {
        return new ComputerCollegeIterator(deparments);
    }
}
