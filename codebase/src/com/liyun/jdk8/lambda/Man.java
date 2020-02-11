package com.liyun.jdk8.lambda;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-01-29 19:11
 */
public class Man {

    private Godness godness;

    public Man(Godness godness) {
        this.godness = godness;
    }

    public Man() {
    }

    public Godness getGodness() {
        return godness;
    }

    public void setGodness(Godness godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "Man{" +
                "godness=" + godness +
                '}';
    }
}
