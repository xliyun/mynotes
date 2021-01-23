package com.liyun.netty.channel;

import java.nio.ByteBuffer;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-30 15:14
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        //创建一个Buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('融');
        buffer.putShort((short)4);

        //取出
        buffer.flip();
        System.out.println();
        System.out.println(buffer.getShort());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getLong());
    }
}
