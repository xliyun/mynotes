package com.liyun.test.IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-03 13:53
 */
public class Reader1 {
    public static void main(String[] args) {
        char test=65279;
        System.out.println(test);
        try {
            FileReader fr=new FileReader("G:\\test\\a.txt");
            int len=0;
            while((len=fr.read())!=-1){
                System.out.println(len);
                System.out.println((char)len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
