package com.github.tangyi.webcast.config;

import com.github.tangyi.common.core.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitmqConfig  {

    @Bean
    public Queue channelVideoQueue() {
        // 创建一个持久化的队列
        return new Queue(MqConstant.CHANNEL_VIDEO_QUEUE, true);
    }



}