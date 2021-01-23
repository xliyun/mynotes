package com.liyun.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-05 10:16
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler {

    //使用一个hashmap管理
    public static Map<String,Channel> channels = new HashMap<>();

    //定义一个channel组，管理所有的channel
    //GlobalEventExecutor.INSTANCE是全局事件执行器，是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //handlerAdded表示连接建立，一旦连接，第一个被执行
    //将当前channel加入到channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入两天的信息推送给其他在线的客户端
        /*
        该方法会将channelGroup中所有的channel遍历，并发送消息
        我们不需要自己遍历
         */
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"加入聊天"+sdf.format(new java.util.Date())+"\n");
        channelGroup.add(channel);

    }

    //断开连接，将xx客户离开信息推送给当前在线的客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"离开了\n");
        //channelGroup自动去掉这个channel
        System.out.println("当前channelGroup大小"+channelGroup.size());
    }

    //表示channel处于活动状态，提示xx上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //只在服务端提示
        System.out.println(ctx.channel().remoteAddress()+"上线了");
    }

    //表示channel处于不活动状态，提示xx离线
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"离线了");
    }

    //读取数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        //获取到当前channel
        Channel channel = ctx.channel();
        //这是我们遍历一下channelGroup，根据不同的情况，回送不同的消息
        channelGroup.forEach(ch->{
            if(channel!=ch){//不是当前的channel，转发消息
                ch.writeAndFlush("[客户]"+channel.remoteAddress()+" 发送了消息"+msg+"\n");
            }else{//回显自己发送的消息
                ch.writeAndFlush("[自己]发送了消息"+msg+"\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
    }
}
