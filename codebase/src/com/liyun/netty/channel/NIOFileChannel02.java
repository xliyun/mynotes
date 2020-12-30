package com.liyun.netty.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-30 11:05
 */
public class NIOFileChannel02 {
    public static void main(String[] args) throws IOException {

        //创建文件的输入流
        File file = new File("d:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //通过fileInputStream获取对应的FileChannel -> 实际类型FileChannelImplement
        FileChannel fileChannel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //将 通道中的数据读入到buffer
        fileChannel.read(byteBuffer);

        //将byteBuffer 的字节数据转成字符串
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
