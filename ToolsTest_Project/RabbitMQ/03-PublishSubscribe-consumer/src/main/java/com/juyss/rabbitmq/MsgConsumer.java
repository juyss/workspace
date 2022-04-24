package com.juyss.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MsgConsumer
 * @Desc:
 * @package com.juyss.rabbitmq
 * @project RabbitMQ
 * @date 2020/11/23 16:03
 */
@Component
public class MsgConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "logs",type = "fanout"))
    })
    public void receiver1(String msg){
        System.out.println("消费者1接收消息=====================>"+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "logs",type = "fanout"))
    })
    public void receiver2(String msg){
        System.out.println("消费者2接收消息=====================>"+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "logs",type = "fanout"))
    })
    public void receiver3(String msg){
        System.out.println("消费者3接收消息=====================>"+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "logs",type = "fanout"))
    })
    public void receiver4(String msg){
        System.out.println("消费者4接收消息=====================>"+msg);
    }

}
