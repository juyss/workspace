package com.juyss.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MsgConsumer
 * @Desc:
 * @package com.juyss.rabbitmq
 * @project RabbitMQ
 * @date 2020/11/23 15:33
 */
@Component
public class MsgConsumer {

    @RabbitListener(queuesToDeclare = @Queue(value = "hello"))
    public void receiver1(String msg){
        System.out.println("消费者1接收消息==============>"+msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "hello"))
    public void receiver2(String msg){
        System.out.println("消费者2接收消息==============>"+msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "hello"))
    public void receiver3(String msg){
        System.out.println("消费者3接收消息==============>"+msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "hello"))
    public void receiver4(String msg){
        System.out.println("消费者4接收消息==============>"+msg);
    }

}
