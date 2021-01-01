package com.liyun.netty.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-31 10:32
 */
public class GroupChatServer {

    //定义属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    //构造器
    //初始化工作
    public GroupChatServer(){
        try{
            //得到选择器
            selector = Selector.open();
            //初始化serverSocketChannel
            listenChannel =  ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞模式
            listenChannel.configureBlocking(false);
            //将listenChannel注册到selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //监听
    public void listen(){
        try{
            //循环处理
            while(true){
                int count = selector.select();
                if(count>0){//有事件处理
                    //遍历得到的selectionKey集合
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while(iterator.hasNext()){
                        //取出selectionKey
                        SelectionKey key = iterator.next();
                        //监听到的是accept,处理连接
                        if(key.isAcceptable()){
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            //将该sc注册到selector
                            sc.register(selector,SelectionKey.OP_READ);
                            //提示
                            System.out.println(sc.getRemoteAddress().toString().substring(1)+" 上线");
                        }else if(key.isReadable()){//通道发送read事件，即通道是可读状态
                            //处理读
                            readData(key);
                        }

                        //当前的key删除，防止重复处理
                        iterator.remove();
                    }
                }else {
                    System.out.println("等待...");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    //读取客户端消息
    private void readData(SelectionKey key){
        //定义一个SocketChannel
        SocketChannel channel = null;
        try{
            //取到关联的channel
            channel = (SocketChannel) key.channel();
            //创建缓冲buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            //根据count的值做处理
            if(count>0){
                //把缓冲区的数据转成字符串
                String msg = new String(buffer.array());
                //输出该消息
                System.out.println("客户端消息："+msg);
                //向其他客户端转发消息(去除自己)
                sendInfoToOtherClients(msg,channel);
            }
        }catch (IOException e){
            try {
                System.out.println(channel.getRemoteAddress()+" 离线了");
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    //转发消息给其他客户(通道)
    private void sendInfoToOtherClients(String msg,SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中");
        //遍历所有注册到selector上的socketChannel并排除self
        for(SelectionKey key:selector.keys()){
            //通过key取出对应的socketChannel
            Channel targetChannel = key.channel();
            //排除自己
            if(targetChannel instanceof SocketChannel && targetChannel!=self){
                //转型
                SocketChannel dest = (SocketChannel)targetChannel;
                //将msg存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //将buffer数据写入到通道中
                dest.write(buffer);
            }
        }
    }
    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        chatServer.listen();
    }
}
