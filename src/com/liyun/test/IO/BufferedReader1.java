package com.liyun.test.IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-03 19:02
 */
public class BufferedReader1 {
    public static void main(String[] args) {
        try {
            BufferedReader br=new BufferedReader(new FileReader("G:\\test\\a.txt"));
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
}
