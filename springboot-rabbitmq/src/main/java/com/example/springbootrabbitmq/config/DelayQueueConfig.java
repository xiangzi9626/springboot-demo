package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 需要安装rabbitmq插件 延迟队列配置类
 */
@Configuration
public class DelayQueueConfig {
    //交换机
    private static final String DELAY_EXCHANGE = "delay_exchange";
    //队列
    private static final String DELAY_QUEUE = "delay_queue";
    //routing-key
    private static final String DELAY_ROUTING_KEY = "delay_routing_key";

    //声明交换机
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false, arguments);
    }


    //声明队列
    @Bean
    public Queue delayQueue() {
        return QueueBuilder.durable(DELAY_QUEUE).build();
    }

    //绑定
    @Bean
    public Binding delayQueueBindDelayExchange() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY).noargs();
    }
}
