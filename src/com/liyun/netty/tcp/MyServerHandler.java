package com.liyun.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-08 14:12
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       // System.out.println("服务端channelActive");
    }

    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        //将buffer转成字符串
        String message = new String(buffer, CharsetUtil.UTF_8);
        System.out.println("服务器端接收到数据"+message);
        System.out.println("服务器端接收到消息量="+(++this.count));

        //服务器回送数据给客户端,回送一个随机id
        ByteBuf responseByteBuf = Unpooled.copiedBuffer(UUID.randomUUID().toString()+" ", Charset.forName("utf-8"));
        ctx.writeAndFlush(responseByteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
