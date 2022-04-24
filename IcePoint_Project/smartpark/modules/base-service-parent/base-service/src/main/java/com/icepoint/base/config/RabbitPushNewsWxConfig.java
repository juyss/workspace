package com.icepoint.base.config;

import com.github.tangyi.common.core.constant.MqConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitPushNewsWxConfig {

    @Bean
    public Queue pushNewsWxQueue() {
        // 创建一个持久化的队列
        return new Queue(MqConstant.PUSH_NEWS_WX_QUEUE, true);
    }

}