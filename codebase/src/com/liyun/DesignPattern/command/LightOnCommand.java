package com.liyun.DesignPattern.command;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 11:29
 */
public class LightOnCommand implements Command {
    //聚合LightReceviver
    LightReceiver lightReceiver;

    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        //调用接受者的方法
        lightReceiver.on();
    }

    @Override
    public void undo() {
        //调用接受者的关闭方法
        lightReceiver.off();
    }
}
