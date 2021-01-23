package com.liyun.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-11-08 11:30
 */
public class BIOServer {
    public static void main(String[] args) throws Exception{
        //线程池机制

        //思路
        //1.创建一个线程池
        //2.如果有客户端连接，就创建一个线程，与之通讯（单独写一个方法）

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //创建ServierSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动");
        while(true){
            //监听，等待客户端连接
            System.out.println("等待连接....");//如果ser
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            //socket是阻塞的，如果没有客户端连接，就会阻塞在这里

            //创建一个线程，与之通讯,CachedThreadPool()一次只会有一个线程执行，
            if(socket.isConnected()) {
                newCachedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        //可以和客户端通讯 telnet ctrl+] send 信息
                        handler(socket);
                    }
                });
            }

        }
    }

    //编写一个handler方法，和客户端同学
    public static void handler(Socket socket){

        byte[] bytes = new byte[1024];
        //通过socker获取输入流
        try {
            System.out.println("线程信息 id="+Thread.currentThread().getId()+" 名字="+Thread.currentThread().getName());
            InputStream inputStream = socket.getInputStream();

            //循环读取客户端发送的数据
            int len = 0;
            System.out.println("等待读....");
            //如果客户端没有输入信息，就会一直阻塞在这里

            while((len = inputStream.read(bytes))!=-1){
                System.out.println("线程信息 id="+Thread.currentThread().getId()+" 名字="+Thread.currentThread().getName());
                System.out.println(new String(bytes,0,len));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
