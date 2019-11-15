package com.liyun.test.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputStreamChinese {
    public static void main(String[] args) {
        try {
            FileInputStream fis=new FileInputStream("G:\\test\\a.txt");
            int len=0;
            while((len=fis.read())!=-1){
                System.out.println((char)len);//idea右下角是编码格式，utf-8 一个汉字三个字节，我们读取一个字节就强转字节就会乱码
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}