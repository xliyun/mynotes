package com.liyun.test.IO;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-03 9:54
 */
public class Filetest {
    public static void main(String[] args) {
        /*File f1=new File("G:\\test\\a.txt");
        System.out.println(f1);

        System.out.println(f1.getAbsolutePath());

        System.out.println(f1.getPath());

        System.out.println(f1.getName());

        System.out.println(f1.length());

        System.out.println(f1.exists());

        System.out.println(f1.isDirectory());

        System.out.println(f1.isFile());

        try {
            boolean b1=f1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f2=new File("G:\\test");
        System.out.println(new File("G:\\test\\dirs").mkdirs());
*/
        File file=new File("D:\\github\\mynotes\\codebase");
        String[] arrays=file.list();
/*        for (String array : arrays) {
            System.out.println(array);
        }

        File[] files=file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getPath());
        }*/

        getAllFile(file);
    }

    public static void getAllFile(File dir){
        System.out.println("======="+dir.getPath()+"=======");//打印被遍历的 目录
        File[] files=dir.listFiles();
        for (File file : files) {
            if(file.isDirectory()){
                getAllFile(file);
            }else {
                System.out.println(file.toPath());
            }
        }
    }
}
