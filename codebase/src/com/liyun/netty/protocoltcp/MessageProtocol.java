package com.liyun.netty.protocoltcp;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-08 15:41
 */
public class MessageProtocol {
    private int len; //关键
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
