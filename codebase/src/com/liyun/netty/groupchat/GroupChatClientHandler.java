package com.liyun.netty.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-05 14:03
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //直接打印从服务器端拿到的消息
        System.out.println(msg.trim());
    }
}
