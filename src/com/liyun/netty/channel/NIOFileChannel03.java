package com.liyun.netty.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-30 11:05
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("codebase/src/com/liyun/netty/channel/from.txt");
        FileChannel fileChannelFrom = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("codebase/src/com/liyun/netty/channel/to.txt");
        FileChannel fileChannelTo = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while(true){//循环读取
            //这里有一个重要的操作，一定不要忘了
            byteBuffer.clear();//清空buffer,如果不复位，前一次刚好读完，limit等于position,就返回0了
            int readSize = fileChannelFrom.read(byteBuffer);
            System.out.println("read读取数据的大小："+readSize);
            if(readSize ==-1){//-1表示读取完毕
                break;
            }
            byteBuffer.flip();
            //将buffer中的数据写入到fileChannelTo  ==>to.txt
            fileChannelTo.write(byteBuffer);
        }

        //关闭相关的流
        fileInputStream.close();
        fileOutputStream.close();
    }
}
