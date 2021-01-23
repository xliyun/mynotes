package com.liyun.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MyInOutServerHandler extends SimpleChannelInboundHandler<Long> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("从客户端"+ctx.channel().remoteAddress()+"读取到long:"+msg);

        //给客户端发送一个long
        ctx.writeAndFlush(98765L);
        System.out.println("=====================================");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      cause.printStackTrace();
      ctx.close();
    }
}
