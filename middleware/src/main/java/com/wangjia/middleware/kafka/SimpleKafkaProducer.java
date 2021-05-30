package com.wangjia.middleware.kafka;

/**
 * @author : wangjia
 * @date : 2019-08-18 23:28
 */

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;
import java.util.regex.Pattern;

public class SimpleKafkaProducer {
    private static KafkaConsumer<String, String> consumer;
    private static final  Pattern compile = Pattern.compile("wj*");
    private final static String TOPIC = "wjtest";

    public SimpleKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        //每个消费者分配独立的组号
        props.put("group.id", "test2");
        //如果value合法，则自动提交偏移量
        props.put("enable.auto.commit", "true");
        //设置多久一次更新被消费消息的偏移量
        props.put("auto.commit.interval.ms", "1000");
        //设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
        props.put("session.timeout.ms", "30000");
        //自动重置offset
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(props);
    }

    public void consume() throws InterruptedException {
        consumer.subscribe(Arrays.asList(TOPIC));
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    String value = record.value();
                    System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), value);
                    System.out.println();
                }
                System.out.println(111);
                TopicPartition topicPartition = new TopicPartition(TOPIC, 0);
                List<TopicPartition> list = new ArrayList<>();
                list.add(topicPartition);
                Map<TopicPartition, Long> map = consumer.beginningOffsets(list);
                Map<TopicPartition, Long> end = consumer.endOffsets(list);
                System.out.println(map.get(topicPartition));
                System.out.println(end.get(topicPartition));

            }
        }).start();

//        Map<String, List<PartitionInfo>> map = consumer.listTopics();
//        Set<Map.Entry<String, List<PartitionInfo>>> entries = map.entrySet();
//        for (Map.Entry<String, List<PartitionInfo>> entry : entries) {
//            String key = entry.getKey();
//            System.out.println(key);
//            List<PartitionInfo> partitionInfos = entry.getValue();
//            partitionInfos.forEach(System.out::println);
//        }
//        Thread.sleep(1000000);
    }

    public static void main(String[] args) throws InterruptedException {
        new SimpleKafkaProducer().consume();
    }
}