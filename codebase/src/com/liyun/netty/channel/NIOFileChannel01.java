package com.liyun.netty.channel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-30 11:05
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello,netty";
        //创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\file01.txt");
        //通过 FileOutputStream获取对应的FileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer =  ByteBuffer.allocate(1024);

        //将str放入byteBuffer
        byteBuffer.put(str.getBytes());

        //对byteBuffer进行翻转
        byteBuffer.flip();

        //将byteBuffer数据写入到fileChasnnel
        fileChannel.write(byteBuffer);
        //关闭最底层的流
        fileOutputStream.close();
    }
}
