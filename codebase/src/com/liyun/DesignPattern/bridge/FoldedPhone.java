package com.liyun.DesignPattern.bridge;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-15 15:11
 */
//折叠式手机的类，继承抽象类Phone
public class FoldedPhone extends Phone {

    //构造器
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open(){
        super.open();
        System.out.println(" 折叠样式的手机");
    }

    @Override
    public void close(){
        super.close();
        System.out.println(" 折叠样式的手机");
    }

    @Override
    public void call(){
        super.call();
        System.out.println(" 折叠样式的手机");
    }
}
