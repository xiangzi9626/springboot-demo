package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发布确认高级
 * 配置类
 */
@Configuration
public class ConfirmConfig {
    private static final String CONFIRM_EXCHANGE = "confirm_exchange";
    private static final String CONFIRM_QUEUE = "confirm_queue";
    private static final String CONFIRM_ROUTING_KEY = "confirm_routing_key";

    //声明交换机
    @Bean
    public DirectExchange confirmExchange() {
        return new DirectExchange(CONFIRM_EXCHANGE);
    }

    //声明队列
    @Bean
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE).build();
    }

    //绑定
    @Bean
    public Binding confirmQueueBindConfirmExchange() {
        return BindingBuilder.bind(confirmQueue()).to(confirmExchange()).with(CONFIRM_ROUTING_KEY);
    }
}
