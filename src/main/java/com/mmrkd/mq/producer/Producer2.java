package com.mmrkd.mq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import javax.security.auth.callback.Callback;
import java.util.concurrent.CountDownLatch;

public class Producer2 {

    public static void main(String[] args) throws Exception{

        DefaultMQProducer producer = new DefaultMQProducer("wolfcode-producer");
        //2 设置名字服务的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //3 启动生产者
        producer.start();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        //4 创建一个消息
        Message message = new Message("02-send_type", "hello,RocketMQ".getBytes("utf-8"));
        //5 发送消息
        for (int i = 0; i < 10; i++) {
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("消息发送成功");
                    countDownLatch.countDown();
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("消息发送失败");
                    countDownLatch.countDown();
                }
            });
        }
//        Thread.sleep(10000);
        countDownLatch.await();
        //6 关闭连接
        producer.shutdown();

    }
}
