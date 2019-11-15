package com.liyun.DesignPattern.flyweight;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 14:12
 */
public class Client {
    public static void main(String[] args) {
        //创建一个工厂
        WebSiteFacotry facotry = new WebSiteFacotry();

        //客户要一个以新闻形式发布的网站
        WebSite webSite1 = facotry.getWebSiteCategory("新闻");
        webSite1.use(new User("张三"));

        //博客形式发布的网站
        WebSite webSite2 = facotry.getWebSiteCategory("博客");
        webSite2.use(new User("李四"));

        WebSite webSite3 = facotry.getWebSiteCategory("新闻");
        webSite3.use(new User("王小明"));

        System.out.println("网站的分类共："+facotry.getWebSiteCount());
    }
}
