package com.example.springbootrabbitmq.controller;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProducerController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{message}")
    public void sendMsg(@PathVariable("message") String message) {
        rabbitTemplate.convertAndSend("normal_exchange", "normal_routing_key1", "消息来自于TTL为10秒的队列" + message);
        rabbitTemplate.convertAndSend("normal_exchange", "normal_routing_key2", "消息来自于TTL为40秒的队列" + message);
        System.out.println("发送消息" + message);
    }

    //发送带过期时间的消息
    @GetMapping("/sendExpiration/{message}/{ttlTime}")
    public void sendExpiration(@PathVariable("message") String message, @PathVariable("ttlTime") String ttlTime) {
        rabbitTemplate.convertAndSend("normal_exchange", "normal_routing_key3", message, msg -> {
            //这里设置消息过期时间
            msg.getMessageProperties().setExpiration(ttlTime);
            return msg;
        });
        System.out.println("发送消息:" + message + " 过期时间:" + ttlTime);
    }

    //使用插件实现延迟队列
    @GetMapping("/sendDelay/{message}/{delayTime}")
    public void sendDelay(@PathVariable("message") String message, @PathVariable("delayTime") Integer delayTime) {
        rabbitTemplate.convertAndSend("delay_exchange", "delay_routing_key", message, msg -> {
            //这里设置消息过期时间
            msg.getMessageProperties().setDelay(delayTime);
            return msg;
        });
        System.out.println("发送消息:" + message + " 过期时间:" + delayTime);
    }
    //测试发布确认
    @GetMapping("/sendConfirm/{message}")
    public void sendConfirm(@PathVariable String message){
        //消息ID
        CorrelationData correlationData = new CorrelationData("100");
        rabbitTemplate.convertAndSend("confirm_exchange", "confirm_routing_key",message,correlationData);
        System.out.println("发送消息" + message);
    }
}
