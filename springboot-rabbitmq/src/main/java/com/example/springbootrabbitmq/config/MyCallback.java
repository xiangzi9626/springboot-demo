package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class MyCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
    //交换机路由失败触发 消息退回给生产者

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        System.out.println("路由失败回退消息" + returnedMessage.getMessage());
        System.out.println("交换机" + returnedMessage.getExchange());
        System.out.println("路由key" + returnedMessage.getRoutingKey());
        System.out.println("回退有因" + returnedMessage.getReplyText());
    }

    @Resource
    private RabbitTemplate rabbitTemplate;

    //注入
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * 交换机确认回调
     *
     * @param correlationData 消息的ID
     * @param b               是否成功接收
     * @param s               成功返回null 失败返回失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        String msgId = correlationData != null ? correlationData.getId() : "";
        if (b) {
            System.out.println("交换机消息接收成功ACK确认消息id:" + msgId);
        } else {
            System.out.println("交换机未收到消息 id:" + msgId + " 原因为:" + s);
        }
    }
}
