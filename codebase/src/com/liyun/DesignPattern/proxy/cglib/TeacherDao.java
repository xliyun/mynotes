package com.liyun.DesignPattern.proxy.cglib;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-21 14:41
 */
public class TeacherDao {
    public void teach(){
        System.out.println(" 老师开始上课...，我是cglib代理，不需要实现接口");
    }
}
