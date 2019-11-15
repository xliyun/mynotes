package com.liyun.DesignPattern.prototypepattern.deepclone;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-10 16:51
 */
public class Client {
    public static void main(String[] args) {
        DeepProtoType p=new DeepProtoType();
        p.name="刘亦菲";
        p.deepCloneableTarget=new DeepCloneableTarget("AA","BB");

        DeepProtoType p2= (DeepProtoType) p.deepClone();

        System.out.println(p.hashCode());
        System.out.println(p2.hashCode());
    }
}
