package com.liyun.DesignPattern.iterator;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-07 10:20
 */
//系
public class Deparment {
    public Deparment(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Deparment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String name;
    private String desc;
}
