package com.liyun.netty.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-08 14:00
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        System.out.println("啊哈哈");
        pipeline.addLast(new MyClientHandler());
    }
}
