package com.liyun.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //入栈的handler进行解码 MyByteToLongDecoder
        pipeline.addLast(new MyByteToLongDecoder());

        pipeline.addLast(new MyLongToByteEndoder());
        //自定义handler,处理业务逻辑
        pipeline.addLast(new MyInOutServerHandler());
    }
}
