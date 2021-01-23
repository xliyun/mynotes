package com.liyun.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-07 9:49
 */
public class MyInOutClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("服务器ip="+ctx.channel().remoteAddress());
        System.out.println("收到服务器数据="+msg);
        System.out.println("==================================");
    }

    //重写channelActive发送数据

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyInOutClientHandler 发送数据");
        //ctx.writeAndFlush(123456L);//发送的是一个Long
        //分析
        //1."abcdabcdabcdabcd"是16个字节
        //2.该处理器的前一个handler是MyLongToByteEndoder
        //3.父类是MessageToByteEncoder
        /*
         if (acceptOutboundMessage(msg)) { //判断当前msg， 是不是应该处理的类型，如果是就处理，不是就跳过encode
                @SuppressWarnings("unchecked")
                I cast = (I) msg;
                buf = allocateBuffer(ctx, cast, preferDirect);
                try {
                    encode(ctx, cast, buf);
                } finally {
                    ReferenceCountUtil.release(cast);
                }

                if (buf.isReadable()) {
                    ctx.write(buf, promise);
                } else {
                    buf.release();
                    ctx.write(Unpooled.EMPTY_BUFFER, promise);
                }
                buf = null;
            } else {
                ctx.write(msg, promise);
            }
         4.因此我们编写 Encoder是要注意传入的数据类型和处理的数据的类型一致
         */
        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));
    }
}
