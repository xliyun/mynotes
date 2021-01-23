package com.liyun.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-01 17:05
 */
public class NettySever {
    public static void main(String[] args) throws InterruptedException {

        //创建BossGroup 和 WorkGroup
        //说明
        //1.创建两个线程组 bossGroup 和 workerGroup
        //2.boosGroup 只是处理连接请求，真正和客户端业务处理，会交给workGroup完成
        //3.两个都是无限循环
        //4.bossGroup和workerGroup含有的子线程(NioEventLoop)的个数
        //默认实际 cpu核数*2
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup worKerGroup = new NioEventLoopGroup();

        try{
            //创建服务端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //使用链式变成来进行设置
            bootstrap.group(bossGroup,worKerGroup)//设置两个线程组
            .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel作为服务器的通道实现
            .option(ChannelOption.SO_BACKLOG,128)//设置线程队列得到连接个数
            .childOption(ChannelOption.SO_KEEPALIVE,true)//设置保持活动连接状态
            //.handler(null)//该handler对应bossGroup,childHandler对应workgroup
            .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化对象（匿名对象）
                //给pipeline 设置处理器
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    System.out.println("客户socketchannel hashcode="+ch.hashCode());//可以使用一个集合管理SocketChannel,再推送消息时，可以将业务加入到各个channel对应的NIOEventLoop的taskQueue或者scheduleTaskQueue
                    ch.pipeline().addLast(new NettyServerHandler());

                }
            }); //给我们的workerGroup 的 EventLoop 对应的管道设置处理器

            //绑定一个端口并且同步，生成一个ChannerlFuture对象
            //启动服务器(并绑定端口)
            ChannelFuture cf = bootstrap.bind(6668).sync();

            //给cf注册监听器，监控我们关心的事件
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(cf.isSuccess()){
                        System.out.println("监听端口6668成功!");
                    }else{
                        System.out.println("监听端口6668失败!");
                    }
                }
            });

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            worKerGroup.shutdownGracefully();
        }
    }
}
