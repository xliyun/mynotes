package com.liyun.zookeeper.node;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-22 11:22
 */
public class ZookeeperConnection {
    public static void main(String[] args) {
        try{
            //计数器对象
            CountDownLatch countDownLatch = new CountDownLatch(1);
            //connectString:服务器ip和端口
            //sessionTimeout:客户端与服务器之间的会话超时时间 以毫秒为单位
            //watcher:监视器对象
            ZooKeeper zooKeeper = new ZooKeeper("192.168.211.132:2181", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if(event.getState()==Event.KeeperState.SyncConnected){
                        System.out.println("连接创建成功");
                        countDownLatch.countDown();
                    }
                }
            });
            // 主线程阻塞等待连接对象的创建成功
            countDownLatch.await();
            //会话编号
            System.out.println(zooKeeper.getSessionId());
            zooKeeper.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
