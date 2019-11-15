package com.liyun.DesignPattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 9:35
 */
public class Colleage extends OrganizationComponent {

    //List 中存放的是Department
    List<OrganizationComponent> organizationComponents=new ArrayList<>();

    public Colleage(String name, String des) {
        super(name, des);
    }

    //重写add
    @Override
    protected void add(OrganizationComponent organizationComponent) {
        //实际业务中，Colleage的add和Unibersity add不一定完全相同
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
