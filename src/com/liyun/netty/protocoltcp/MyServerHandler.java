package com.liyun.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-08 14:12
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       // System.out.println("服务端channelActive");
    }

    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        //接收数据并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务端接收到信息如下：");
        System.out.println("长度="+len);
        System.out.println("内容="+new String(content,CharsetUtil.UTF_8));

        //回复消息
        String responseContent = UUID.randomUUID().toString();
        int responseLen = responseContent.getBytes(CharsetUtil.UTF_8).length;//拿到utf8编码下的长度
        //构建一个协议包
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(responseLen);
        messageProtocol.setContent(responseContent.getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
