package com.liyun.kafka.cosumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-16 20:06
 */
public class Cousmer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        //key 序列化器
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        //value 反序列化器
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        //关闭自动提交偏移量
        properties.setProperty("enable.auto.commit","false");

        //properties.setProperty("auto.offset.reset","none");
        //定义消费者群组
        properties.setProperty("group.id","1111");
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(properties);



        //订阅主题
        kafkaConsumer.subscribe(Collections.singletonList("test-topic"), new ConsumerRebalanceListener() {
            //分区再均衡之前调用
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                System.out.println("分区再均衡之前");
                //可能是最后一次消费这个分区，所以需要用同步的方式
                kafkaConsumer.commitSync();
            }

            //分区再均衡之后调用
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                System.out.println("分区再均衡之后");
            }
        });

        Map<TopicPartition, OffsetAndMetadata> offsetAndMetadataMap = new HashMap<>();
        offsetAndMetadataMap.put(new TopicPartition("test-topic",0),new OffsetAndMetadata(0,"偏移量自动提交"));

        try{
            while(true){
                //每间隔500毫秒拉取一次
                ConsumerRecords<String, String> poll = kafkaConsumer.poll(500);
                for (ConsumerRecord<String, String> context : poll) {
                    System.out.println("消息所在分区:"+context.partition()+"\n消息偏移量:"+context.offset()+"\n" +
                            "消息key:"+context.key()+"\n消息value:"+context.value()+"\n============");

                    System.out.println("处理消费到的消息的逻辑");
                }
                //异步提交偏移量
                kafkaConsumer.commitAsync();
                //kafkaConsumer.commitSync(offsetAndMetadataMap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //如果消费者死机了，要提交一次偏移量，提交一次就关闭了，所以用同步提交
            try{
                //同步提交偏移量
                kafkaConsumer.commitSync();
            }catch (Exception e2){
                e2.printStackTrace();
            }finally {
                //关闭，优雅推出消费者
                kafkaConsumer.close();
            }

        }

    }
}
