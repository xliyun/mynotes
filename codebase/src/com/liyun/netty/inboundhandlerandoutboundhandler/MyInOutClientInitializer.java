package com.liyun.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-07 9:31
 */
public class MyInOutClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //这是一个入栈的解码器，进行编码
        pipeline.addLast(new MyByteToLongDecoder());
        //加入一个出栈的handler对数据进行编码
        pipeline.addLast(new MyLongToByteEndoder());
        //加入一个自定义的handler,处理业务逻辑
        pipeline.addLast(new MyInOutClientHandler());
    }
}
