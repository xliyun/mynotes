package com.liyun.test.IO;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-03 18:52
 */
public class BudderedOutputStream1 {
    public static void main(String[] args) {
        try {
            FileOutputStream fos=new FileOutputStream("G:\\test\\a.txt");
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            bos.write("我把数据写入到内部缓冲区".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
