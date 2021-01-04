package com.liyun.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-03 16:41
 */
public class NettyByteBuf02 {
    public static void main(String[] args) {
       //创建ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,world!北京", Charset.forName("utf-8"));

        //使用相关的方法
        if(byteBuf.hasArray()){
            byte[] content = byteBuf.array();
            //将content转成字符串
            System.out.println(new String(content,CharsetUtil.UTF_8));
            System.out.println("byteBuf="+byteBuf);
            System.out.println(byteBuf.arrayOffset());//0
            System.out.println(byteBuf.readerIndex());//0
            System.out.println(byteBuf.writerIndex());//18
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.readableBytes());//可读取的字节数，这里应该是18

            for(int i = 0;i<byteBuf.readableBytes();i++){
                System.out.println((char)byteBuf.getByte(i));
            }

            System.out.println(byteBuf.getCharSequence(0,4,CharsetUtil.UTF_8));
        }
    }
}
