package com.liyun.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {


    /**
     *
     * @param ctx 上下文对象
     * @param in 入栈的ByteBuf
     * @param out List集合，将解码后的数据传给下一个handler处理
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        //因为 long 是8个字节,需要判断有8个字节，才能读取一个Long
        if(in.readableBytes()>=8){
            out.add(in.readLong());

        }
    }
}
