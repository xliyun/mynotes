package com.liyun.DesignPattern.command;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 11:20
 */
//创建命令接口
public interface Command {
    //执行动作（操作）
    public void execute();
    //撤销动作（操作）
    public void undo();
}
