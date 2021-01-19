package com.liyun.kafka.cosumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

/**
 * @description: 消费者不再分组，自动
 * @author: xiaoliyu
 * @date: 2021-01-16 20:06
 */
public class OneCousmer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        //key 序列化器
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        //value 反序列化器
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        /**
         * 非创建消费者分组订阅主题的方式
         * 1.定义主题分区数组，然后assgin
         *         List<TopicPartition> list = new ArrayList<>();
         *         list.add(new TopicPartition("test-topic",0));
         *         kafkaConsumer.assign(list);
         */
        //拉取test-topic主题下所有的分区
        List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor("test-topic");

        //定义独立消费者，没有群组，可以订阅多个分区，多个分区
        List<TopicPartition> list = new ArrayList<>();
        list.add(new TopicPartition("test-topic",0));
        for (PartitionInfo partitionInfo : partitionInfos) {
            //剔除2和8分区的监听
            if(partitionInfo.partition()!=2 && partitionInfo.partition()!=8){
                list.add(new TopicPartition(partitionInfo.topic(),partitionInfo.partition()));
            }
        }
        kafkaConsumer.assign(list);

            while(true){
                //每间隔500毫秒拉取一次
                ConsumerRecords<String, String> poll = kafkaConsumer.poll(500);
                for (ConsumerRecord<String, String> context : poll) {
                    System.out.println("消息所在分区:"+context.partition()+"\n消息偏移量:"+context.offset()+"\n" +
                            "消息key:"+context.key()+"\n消息value:"+context.value()+"\n============");

                    System.out.println("处理消费到的消息的逻辑");
                }
            }
    }
}
