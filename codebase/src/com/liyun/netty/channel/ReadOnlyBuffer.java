package com.liyun.netty.channel;

import java.nio.ByteBuffer;


public class ReadOnlyBuffer {
    public static void main(String[] args) {
        //创建一个Buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);
        for(int i=0;i<64;i++){
            buffer.put((byte) i);
        }

        //读取
        buffer.flip();
        //得到一个只读的buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        while(readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.put((byte)100);//ReadOnlyBufferException
    }
}
