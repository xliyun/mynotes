package com.liyun.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {

        //创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个selector对象
        Selector selector = Selector.open();

        //绑定一个端口，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //把serverSocketChanner注册到selector 关心事件为OP_ACCEPT
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while(true){
            //这里我们等待1秒，如果没有事件（连接事件）发生，返回
            if(selector.select(1000)==0){//没有事件发生或者用selector.selectNow()
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            //如果返回的>0，就获取到相关的selectionKey集合
            //1.如果返回大于0，表示已经获取到关注的事件
            //2.selector.selectedKeys()返回关注事件的集合
            //通过selectionKeys 反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历Set<SelectionKey>，使用迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while(keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                //根据key对应通道发生的事件做相应的处理
                if(selectionKey.isAcceptable()){//如果是OP_ACCEPT，有新的客户端连接我
                    //给该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功 生成了一个socketChannel "+socketChannel.hashCode());
                    //将socketChannel注册到selector，关注事件为OP_READ,
                    // 同时给该socketChannel关联一个Buffer
                    socketChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));

                }

                if(selectionKey.isReadable()){//发生OP_READ
                    //通过key反向获取到对象的channel
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer)selectionKey.attachment();
                    channel.read(byteBuffer);
                    System.out.println("客户端发送的的数据是："+new String(byteBuffer.array()));
                }
            }
            //手动从集合中移除当前的selectionKey，防止重复操作
            keyIterator.remove();
        }
    }
}
