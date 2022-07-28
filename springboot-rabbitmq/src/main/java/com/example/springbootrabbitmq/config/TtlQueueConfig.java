package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟队列配置类
 */
@Configuration
public class TtlQueueConfig {
    //普通交换机
    private static final String NORMAL_EXCHANGE = "normal_exchange";
    //死信交换机
    private static final String DEAD_EXCHANGE = "dead_exchange";
    //普通队列
    private static final String NORMAL_QUEUE1 = "normal_queue1";
    private static final String NORMAL_QUEUE2 = "normal_queue2";
    private static final String NORMAL_QUEUE3 = "normal_queue3";
    //死信队列
    private static final String DEAD_QUEUE = "dead_queue";

    //声明交换机
    @Bean("normalExchange")
    public DirectExchange normalExchange() {
        return new DirectExchange(NORMAL_EXCHANGE);
    }

    @Bean("deadExchange")
    public DirectExchange deadExchange() {
        return new DirectExchange(DEAD_EXCHANGE);
    }

    //声明普通队列
    @Bean("normalQueue1")
    public Queue normalQueue1() {
        Map<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //设置死信routingkey
        arguments.put("x-dead-letter-routing-key", "dead_routing_key");
        //设置消息过期时间 毫秒
        arguments.put("x-message-ttl", 10000);
        return QueueBuilder.durable(NORMAL_QUEUE1).withArguments(arguments).build();
    }

    @Bean("normalQueue2")
    public Queue normalQueue2() {
        Map<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //设置死信routingkey
        arguments.put("x-dead-letter-routing-key", "dead_routing_key");
        //设置消息过期时间 毫秒
        arguments.put("x-message-ttl", 40000);
        return QueueBuilder.durable(NORMAL_QUEUE2).withArguments(arguments).build();
    }

    @Bean("normalQueue3")
    public Queue normalQueue3() {
        Map<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //设置死信routingkey
        arguments.put("x-dead-letter-routing-key", "dead_routing_key");
        return QueueBuilder.durable(NORMAL_QUEUE3).withArguments(arguments).build();
    }

    //声明死信队列
    @Bean("deadQueue")
    public Queue deadQueue() {
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }

    //绑定
    @Bean
    public Binding normalQueue1BindNormalExchange() {
        return BindingBuilder.bind(normalQueue1()).to(normalExchange()).with("normal_routing_key1");
    }

    @Bean
    public Binding normalQueue2BindNormalExchange() {
        return BindingBuilder.bind(normalQueue2()).to(normalExchange()).with("normal_routing_key2");
    }

    @Bean
    public Binding normalQueue3BindNormalExchange() {
        return BindingBuilder.bind(normalQueue3()).to(normalExchange()).with("normal_routing_key3");
    }

    @Bean
    public Binding deadQueueBindDeadExchange() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead_routing_key");
    }
}
