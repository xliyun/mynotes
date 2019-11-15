package com.liyun.test.IO;

import java.io.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-03 13:32
 */
public class FileCopy {
    public static void main(String[] args) {
        try {
            FileInputStream fis=new FileInputStream("G:\\test\\a.txt");
            File oFile = new File("G:\\test\\b.txt");
            oFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(oFile);

/*            int len1=0;
            while((len1=fis.read())!=-1){
                fos.write(len1);
            }*/
            byte[] bytes=new byte[1024];
            int len2=0;
            while((len2=fis.read(bytes))!=-1){
                fos.write(bytes,0,len2);
            }
            fos.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
