package com.liyun.DesignPattern.command;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 13:56
 */
public class TVOnConmmand implements Command{
    //聚合LightReceviver
    TVReceiver tvReceiver;


    public TVOnConmmand(TVReceiver tvReceiver) {
        this.tvReceiver = tvReceiver;
    }

    @Override
    public void execute() {
        tvReceiver.on();
    }

    @Override
    public void undo() {
        tvReceiver.off();
    }
}
