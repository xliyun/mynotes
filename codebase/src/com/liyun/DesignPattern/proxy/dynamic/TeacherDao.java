package com.liyun.DesignPattern.proxy.dynamic;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-21 11:00
 */
public class TeacherDao implements ITreacherDao {
    @Override
    public void teach() {
        System.out.println(" 老师正在上课中...");
    }
}
