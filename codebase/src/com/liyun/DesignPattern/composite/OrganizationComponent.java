package com.liyun.DesignPattern.composite;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-17 11:02
 */
public abstract class OrganizationComponent {
    private String name;
    private String des;

    //因为节点可能是叶子节点，也可能不是，并不是所有节点都需要添加子节点，所以不是抽象方法，而是默认实现
    protected void add(OrganizationComponent organizationComponent){
        //默认实现
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent organizationComponent){
        //默认实现
        throw new UnsupportedOperationException();
    }

    public OrganizationComponent(String name, String des) {
        this.name = name;
        this.des = des;
    }

    //方法打印，做成抽象方法，子类都需要实现
    protected abstract void print();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
