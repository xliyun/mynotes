package com.liyun.test.IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-03 9:51
 */
public class OutPutStream2 {
    public static void main(String[] args) {
        try{
            FileOutputStream fos=new FileOutputStream("G:\\test\\a.txt");
            fos.write(43);

            byte[] btes={65,66,67,68,69};
            fos.write(btes);

            fos.write(btes,1,2);
            fos.close();

            byte[] btes2="你好".getBytes();
            System.out.println(Arrays.toString(btes2));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
