package com.liyun.kafka.product;

import com.liyun.kafka.partitioner.MyPartitioner;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-16 18:39
 */
public class ProducerAnsy {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        //key 序列化器,key是用来确定消息放到哪个分区的
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        //value序列化器
        properties.setProperty("value.serializer",StringSerializer.class.getName());

        //自定义分区管理器
        properties.setProperty("partitioner.class", MyPartitioner.class.getName());

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String,String>(properties);
        ProducerRecord<String,String> record = new ProducerRecord<String,String>("test-topic","test-key","hello");

        //获取发送数据的状态--异步的方式
        Future<RecordMetadata> send = kafkaProducer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(null!=e){
                    e.printStackTrace();
                }

                if(null!=recordMetadata){
                    System.out.println(recordMetadata.topic());
                }
            }
        });

        RecordMetadata recordMetadata = send.get();
        System.out.println("topic:"+recordMetadata.topic()+" 分区:"+recordMetadata.partition()+"  偏移量:"+recordMetadata.offset());
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
