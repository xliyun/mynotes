package com.liyun.kafka.product;

import com.liyun.kafka.partitioner.MyPartitioner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
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
public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        //key 序列化器,key是用来确定消息放到哪个分区的
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //value序列化器
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //自定义分区管理器
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getName());

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String,String>(properties);
        //生产两条消息，一条去分区0，一条去分区1
        ProducerRecord<String,String> record = new ProducerRecord<String,String>("test-topic",null,"hello");
        ProducerRecord<String,String> record2 = new ProducerRecord<String,String>("test-topic","test-key","hello");
        //获取发送数据的状态--同步的方式
        Future<RecordMetadata> send = kafkaProducer.send(record);
        Future<RecordMetadata> send2 = kafkaProducer.send(record2);
        RecordMetadata recordMetadata = send.get();
        System.out.println("topic:"+recordMetadata.topic()+" 分区:"+recordMetadata.partition()+"  偏移量:"+recordMetadata.offset());
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
