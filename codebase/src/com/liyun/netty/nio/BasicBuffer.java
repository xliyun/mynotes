package com.liyun.netty.nio;

import java.nio.Buffer;
import java.nio.IntBuffer;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-11-08 20:59
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //Buffer的使用（简单说明）
        //创建一个Buffer,大小为5，可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer 存放数据
//        intBuffer.put(10);
//        intBuffer.put(11);
//        intBuffer.put(12);
//        intBuffer.put(13);
//        intBuffer.put(14);
         for(int i=0;i<intBuffer.capacity();i++){
             intBuffer.put(i*2);
         }

         //将position赋值给Limit，下次读的时候，最大位置就是当前position的位置了
//        public final Buffer flip() {
//            limit = position;
//            position = 0;
//            mark = -1;
//            return this;
//        }
         //如何从buffer读取数据
        //将buffer转换，读写切换
        intBuffer.flip();
        intBuffer.position(1);//从下标1开始
        intBuffer.limit(3);//不能超过3

         while(intBuffer.hasRemaining()){
             System.out.println(intBuffer.get());
         }
    }
}
