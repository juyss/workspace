package com.juyss.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: TopicConsumer
 * @Desc:
 * @package com.juyss.rabbitmq
 * @project RabbitMQ
 * @date 2020/11/23 17:09
 */
@Component
public class TopicConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(value = "topic",type = "topic"),
                    key = {"user.*","admin.#"})
    })
    public void topicReceiver1(String msg){
        System.out.println("Topic消费者1接收消息====================>"+msg);
    }



    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(value = "topic",type = "topic"),
                    key = {"user.#","admin.*"})
    })
    public void topicReceiver2(String msg){
        System.out.println("Topic消费者2接收消息====================>"+msg);
    }
}
