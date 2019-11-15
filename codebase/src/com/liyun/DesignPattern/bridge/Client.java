package com.liyun.DesignPattern.bridge;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-15 15:20
 */
public class Client {
    public static void main(String[] args) {
        //获取折叠式手机（样式 + 品牌）
        Phone phone1 = new FoldedPhone(new Xiaomi());
        phone1.open();
        phone1.call();
        phone1.close();

        System.out.println("====================");
        Phone phone2 = new FoldedPhone(new Vivo());
        phone2.open();
        phone2.call();
        phone2.close();
    }
}
