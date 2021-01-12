package com.liyun.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-07 9:37
 */
public class MyLongToByteEndoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLongToByteEndoder encode方法被调用");
        System.out.println("msg="+msg);
        out.writeLong(msg);
    }
}
