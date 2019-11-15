package com.liyun.DesignPattern.facade;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 11:13
 */
public class HomeTheaterFacade {

    //定义各个子系统的对象
    private TheaterLight theaterLight;
    private Popcorn popcorn;
    private Stereo stereo;
    private Projector projector;
    private Screen screen;
    private DVDPlayer dvdPlayer;

    public HomeTheaterFacade() {
        this.theaterLight = TheaterLight.getInstance();
        this.popcorn = Popcorn.getInstance();
        this.stereo = Stereo.getInstance();
        this.projector = Projector.getInstance();
        this.screen = Screen.getInstance();
        this.dvdPlayer = DVDPlayer.getInstance();
    }

    //操作分成四部
    public void ready(){
        popcorn.on();
        popcorn.pop();

        screen.down();
        projector.on();
        stereo.on();
        dvdPlayer.on();
        theaterLight.dim();
    }

    //播放
    public void play(){
        dvdPlayer.play();
    }

    //暂停
    public void pause(){
        dvdPlayer.pause();
    }

    //结束
    public void end(){
        popcorn.off();
        popcorn.off();

        screen.off();
        projector.off();
        stereo.off();
        dvdPlayer.off();
        theaterLight.off();
    }
   }
