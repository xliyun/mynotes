package com.liyun.learn.reflection.java1;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-02-25 20:15
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }


}
