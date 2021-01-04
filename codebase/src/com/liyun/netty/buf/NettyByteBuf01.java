package com.liyun.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-03 16:41
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {
        //创建一个ByteBuf
        //说明
        //1.创建一个对象，该对象包含一个数组arr,是一个byte[10]
        //2.在netty的buffer中，不需要flip进行反转
        //  底层维护了一个readerindex和writerIndex
        //3.通过readerIndex 和 writeIndex 和capacity,将buffer分成三个区域
        //discardable bytes(已经读取的区域)  可读区域                   可写的区域
        //0--rederIndex                   rederIndex--writerIndex  writeIndex--capacity
         ByteBuf buffer = Unpooled.buffer(10);
        for(int i=0;i<10;i++){
            buffer.writeByte(i);
        }
        System.out.println("capacity="+buffer.capacity());
        //输出
        for(int i=0;i<buffer.capacity();i++){
            System.out.println(buffer.getByte(i));//这种方式不会改变rederIndex
            System.out.println(buffer.readByte());
        }
        System.out.println("执行完毕");
    }
}
