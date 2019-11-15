package com.thread.tb4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-12 10:22
 */
public class Demo {

    private int[] nums;

    Demo(int line){
        nums=new int[line];
    }

    public void calc(String line,int index,CountDownLatch latch){

        String [] nus=line.split(",");
        int total=0;
        for (String s : nus) {
            total+=Integer.valueOf(s);
        }
        nums[index]=total;//每一行的結果放到数组中
        System.out.println(Thread.currentThread().getName()+" 执行计算任务..."+line+"结果为："+total);
        latch.countDown();
    }

    public void sum(){
        int total=0;
        for(int i=0;i<nums.length;i++){
            total+=nums[i];
        }
        System.out.println("最终结果为："+total);
    }

    public static void main(String[] args) {
        List<String> contents=readFile();
        int lineCount=contents.size();

        CountDownLatch latch=new CountDownLatch(lineCount);
        Demo d=new Demo(lineCount);

        for(int i=0;i<lineCount;i++){
            final int j=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    d.calc(contents.get(j),j,latch);
                }
            }).start();
        }
/*        //就是空等待
        while(Thread.activeCount()>1){

        }*/

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        d.sum();
    }

    private static List<String> readFile() {
        List<String> contents=new ArrayList<>();
        String line=null;
        BufferedReader br=null;
        try {
             br=new BufferedReader(new FileReader("D:\\github\\sword_means_offer\\src\\com\\thread\\tb4\\nums.txt"));
            while((line=br.readLine())!=null){
                contents.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return contents;
    }
}
