package com.liyun.netty.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 说明
 * 1.MapperdBytpeBuffer 可以让文件直接在内存（堆外内存）修改，操作系统不需要拷贝一次
 *
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("codebase/src/com/liyun/netty/channel/from.txt", "rw");
        //获取对应的文件通道
        FileChannel channel = randomAccessFile.getChannel();

        /**
         * 参数1: FileChannel。MapMode.READ_WRITE 使用读写模式
         * 参数2: 0 可以直接修改的起始位置
         * 参数3: 5 是映射到内存的大小，即将1.txt的多少个字节映射到内存，下标5是不能修改的！！！
         * 可以直接修改的范围就是0-5
         * 实际类型是DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0,(byte)'H');
        mappedByteBuffer.put(3,(byte)'9');
        randomAccessFile.close();

    }
}
