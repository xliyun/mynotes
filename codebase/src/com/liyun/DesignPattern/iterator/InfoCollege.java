package com.liyun.DesignPattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-07 11:04
 */
public class InfoCollege implements College{

    List<Deparment> deparmentList;

    public InfoCollege() {
        deparmentList = new ArrayList<>();
        addDepartment("信息安全专业","信息安全专业描述");
        addDepartment("网络安全专业","网络安全专业描述");
        addDepartment("爬虫安全专业","爬虫安全专业");
    }

    @Override
    public String getName() {
        return "信息学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        Deparment deparment = new Deparment(name,desc);
        deparmentList.add(deparment);
    }

    @Override
    public Iterator createInterator() {
        return new InforCollegeIterator(deparmentList);
    }
}
