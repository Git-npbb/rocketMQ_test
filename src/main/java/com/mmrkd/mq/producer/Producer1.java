package com.mmrkd.mq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class Producer1 {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("wolfcode-producer");
        //2 设置名字服务的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //3 启动生产者
        producer.start();
        //4 创建一个消息
        Message message = new Message("01-hello", "hello,rocketmq".getBytes("utf-8"));
        //5 发送消息
        producer.send(message);
        //6 关闭连接
        producer.shutdown();

    }

}
