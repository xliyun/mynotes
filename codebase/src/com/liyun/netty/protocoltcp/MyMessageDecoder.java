package com.liyun.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-08 16:59
 */
public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder.decode 方法被调用");
        //需要将字节得到的二进制字节码转成MessageProtocol数据包
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);

        //封装成 MessageProtocol对象，放入out,传给下一个handler进行业务处理
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(content);

        out.add(messageProtocol);
    }
}
