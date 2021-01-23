package com.liyun.DesignPattern.flyweight;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 14:03
 */
public class ConcreteWebSite extends WebSite {

    //共享的部分，内部状态
    private String type="";//网站发布的类型


    public ConcreteWebSite(String type) {
        this.type = type;
    }

    //外部状态
    @Override
    public void use(User user) {
        System.out.println("网站的发布形式为："+type+","+user.getName()+"正在使用中...");
    }
}
