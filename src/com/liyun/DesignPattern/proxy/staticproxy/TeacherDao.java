package com.liyun.DesignPattern.proxy.staticproxy;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-21 9:12
 */
public class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("老师上课中...");
    }
}
