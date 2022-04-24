package com.github.tangyi.file.config;

import com.github.tangyi.common.core.constant.MqConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig  {

    @Bean
    public Queue channelVideoQueue() {
        // 创建一个持久化的队列
        return new Queue(MqConstant.CHANNEL_VIDEO_ACK_QUEUE, true);
    }

}