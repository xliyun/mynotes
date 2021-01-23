package com.liyun.jdk8.lambda;

import java.util.Optional;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-01-29 19:13
 */
public class NewMan {
    //有的男人心中可能有女神也有可能没有
    private Optional<Godness> godness = Optional.empty();

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }

    public NewMan() {
    }

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }


}
