package com.liyun.thread;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-26 10:36
 */
public class BudgetCheckReturnCO {
    private String messageLevel;             //控制层级
    private String errorMessage;            //返回错误信息,当messageLevel为NO_MESSAGE，无返回信息

    public BudgetCheckReturnCO(String messageLevel, String errorMessage){
        this.messageLevel = messageLevel;
        this.errorMessage = errorMessage;
    };

    public String getMessageLevel() {
        return messageLevel;
    }

    public void setMessageLevel(String messageLevel) {
        this.messageLevel = messageLevel;
    }
}
