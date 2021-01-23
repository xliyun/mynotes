package com.liyun.DesignPattern.flyweight;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 14:29
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
