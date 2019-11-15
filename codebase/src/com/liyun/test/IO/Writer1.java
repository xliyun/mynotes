package com.liyun.test.IO;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-03 17:43
 */
public class Writer1 {
    public static void main(String[] args) {
        try {
            FileWriter fw=new FileWriter("G:\\test\\a.txt");
            char[] cs={'a','b','c','d','e'};
            fw.write(cs);
            fw.write(cs,1,3);
            fw.write("测试");
            fw.write("哈哈哈哈",2,2);
            fw.write(97);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
