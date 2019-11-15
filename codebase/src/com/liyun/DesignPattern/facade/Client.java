package com.liyun.DesignPattern.facade;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 11:34
 */
public class Client {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.end();
    }
}
