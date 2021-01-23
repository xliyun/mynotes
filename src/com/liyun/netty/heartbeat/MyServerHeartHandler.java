package com.liyun.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-05 15:46
 */
public class MyServerHeartHandler extends ChannelInboundHandlerAdapter {
    
    /*
     * @Param ctx 上下文
     * @Param evt 事件
     * @throw Exception
     **/
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){

            //将evt向下转型成IdleStateEvent
            IdleStateEvent evnt = (IdleStateEvent) evt;
            String eventType = null;
            switch (evnt.state()){
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "--超时事件--"+eventType);
        }
    }
}
