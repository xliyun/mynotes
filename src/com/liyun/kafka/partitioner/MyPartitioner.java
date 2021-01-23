package com.liyun.kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-17 0:02
 */
public class MyPartitioner implements Partitioner {
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        System.out.println("使用自己的分区管理器");
        if(bytes==null){
            return 0;
        }
        return 1;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
