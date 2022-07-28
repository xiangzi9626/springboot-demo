package com.example.springbootrabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterConsumer {
    @RabbitListener(queues = "dead_queue")
    public void receive(Message message, Channel channel) {
        String msg = new String(message.getBody());
        System.out.println("死信队列消息为" + msg);
    }
}
