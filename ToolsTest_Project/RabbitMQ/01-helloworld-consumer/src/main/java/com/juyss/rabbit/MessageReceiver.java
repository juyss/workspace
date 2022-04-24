package com.juyss.rabbit;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MessageReceiver
 * @Desc: 消费者接收消息
 * @package com.juyss.rabbit
 * @project RabbitMQ
 * @date 2020/11/23 15:03
 */
@Component
public class MessageReceiver {

    @RabbitListener(queuesToDeclare = @Queue(value = "hello"))
    public void receive(String msg) {
        System.out.println("消费者接收消息==============>"+msg);
    }

}
