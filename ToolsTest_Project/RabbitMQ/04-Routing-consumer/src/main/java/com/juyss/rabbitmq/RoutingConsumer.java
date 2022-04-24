package com.juyss.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: RoutingConsumer
 * @Desc:
 * @package com.juyss.rabbitmq
 * @project RabbitMQ
 * @date 2020/11/23 16:45
 */
@Component
public class RoutingConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                          exchange = @Exchange("routing"),
                          key = {"debug","info","warning","error"})
    })
    public void routingReceiver1(String msg){
        System.out.println("Routing消费者1接收消息=================>"+msg);
    }


    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                          exchange = @Exchange("routing"),
                          key = {"error"})
    })
    public void routingReceiver2(String msg){
        System.out.println("Routing消费者2接收消息=================>"+msg);
    }

}
