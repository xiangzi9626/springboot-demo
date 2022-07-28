package com.example.springbootrabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConfirmConsumer {
    @RabbitListener(queues = "confirm_queue")
    public void receive(Message message, Channel channel) {
        String msg = new String(message.getBody());
        System.out.println("发布确认模式消息为" + msg);
    }
}
