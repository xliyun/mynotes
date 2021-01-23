package com.liyun.DesignPattern.command;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 11:34
 */
public class LightOffCommand implements Command {

    //聚合LightReceviver
    LightReceiver lightRecever;

    public LightOffCommand(LightReceiver lightRecever) {
        this.lightRecever = lightRecever;
    }

    @Override
    public void execute() {
        lightRecever.off();
    }

    @Override
    public void undo() {
        lightRecever.on();
    }
}
