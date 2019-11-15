package com.liyun.DesignPattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-17 11:11
 */

//Unibersity 就是Componsite，可以管理我们的College
public class University extends OrganizationComponent {

    //存放的是Colleage
    List<OrganizationComponent> organizationComponents=new ArrayList<>();

    public University(String name, String des) {
        super(name, des);
    }

    //重写add
    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    //重写remove
    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }

    @Override
    public String getName(){
        return super.getName();
    }

    @Override
    public String getDes(){
        return super.getDes();
    }

    //print方法，输出University包含的学院
    @Override
    protected void print() {
        System.out.println("--------------"+getName()+"------------------");
        //遍历 organizationComponent

        for (OrganizationComponent organizationComponent : organizationComponents) {
            organizationComponent.print();
        }
    }
}
