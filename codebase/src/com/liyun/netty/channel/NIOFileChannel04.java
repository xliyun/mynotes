package com.liyun.netty.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-30 14:55
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception{
        //创建相关的流
        FileInputStream fileInputStream = new FileInputStream("d:\\a.jpg");
        FileOutputStream fileOutputStream =new FileOutputStream("d:\\b.jpg");

        //获取各个流对应的FileChannel
        FileChannel fileChannelFrom = fileInputStream.getChannel();
        FileChannel fileChannelTo = fileOutputStream.getChannel();

        //使用transferFrom完成拷贝
        fileChannelTo.transferFrom(fileChannelFrom,0,fileChannelFrom.size());

        fileChannelFrom.close();
        fileChannelTo.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}

