package com.liyun.DesignPattern.visitor;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 16:20
 */
public class Client {
    public static void main(String[] args) {
        //创建ObjectStructure
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man("小明2"));
        objectStructure.attach(new Woman("小红"));


        //成功
        SuccessVisitor success = new SuccessVisitor();
        objectStructure.display(success);

        FailVisitor fail = new FailVisitor();
        objectStructure.display(fail);
    }
}
