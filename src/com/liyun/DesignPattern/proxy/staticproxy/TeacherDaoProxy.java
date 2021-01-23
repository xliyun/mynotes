package com.liyun.DesignPattern.proxy.staticproxy;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-21 9:12
 */
//代理对象，静态代理
public class TeacherDaoProxy implements ITeacherDao {

    private ITeacherDao target;//目标对象，通过接口管理

    //构造器
    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("开始代理，完成某些操作...");
        target.teach();
        System.out.println("代理结束...");
    }
}
