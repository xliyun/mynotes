package com.liyun.DesignPattern.command;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-22 13:44
 */
public class Client {
    public static void main(String[] args) {
        //使用命令设计模式完成通过遥控器对电灯的操作

        //创建电灯的对象（接受者）
        LightReceiver lightRecever = new LightReceiver();

        //创建电灯相关的开关命令
        LightOnCommand lightOnCommand = new LightOnCommand(lightRecever);

        //创建电灯关闭的命令
        LightOffCommand lightOffCommand = new LightOffCommand(lightRecever);

        //需要一个遥控器
        RemoteController remoteController = new RemoteController();

        //给我们的遥控器设置相关命令，比如 num = 0的是电灯的开关的操作
        remoteController.setCommand(0,lightOnCommand,lightOffCommand);

        System.out.println("---按下灯的开按钮---");
        remoteController.onButtonWasPushed(0);
        System.out.println("---按下灯的关按钮---");
        remoteController.offButtonWasPushed(0);
        System.out.println("---撤销按钮---");
        remoteController.undoButtonWasPushed();

        //需要增加电视的时候，只需要增加 TVOnCommand和tvOffCommand和TVReceiver
        //RemoteController就不需要做任何修改
        System.out.println("=====使用遥控器 操作电视机=====");
        TVReceiver tvReceiver = new TVReceiver();
        TVOnConmmand tvOnConmmand = new TVOnConmmand(tvReceiver);
        TVOffCommand tvOffCommand = new TVOffCommand(tvReceiver);

        //给我们的遥控器设置相关命令，比如 num = 1的是电视机的开关的操作
        remoteController.setCommand(1,tvOnConmmand,tvOffCommand);
        System.out.println("---按下电视机的开按钮---");
        remoteController.onButtonWasPushed(0);
        System.out.println("---按下电视机的关按钮---");
        remoteController.offButtonWasPushed(0);
        System.out.println("---撤销按钮---");
        remoteController.undoButtonWasPushed();
    }
}
