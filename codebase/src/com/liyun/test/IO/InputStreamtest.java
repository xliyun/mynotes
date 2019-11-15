package com.liyun.test.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-03 13:03
 */
public class InputStreamtest {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("G:\\test\\a.txt");
            byte[] bytes2=new byte[1024];
            int len2=0;
            while((len2=fis.read(bytes2))!=-1){
                System.out.println(new String(bytes2));
            }

            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
