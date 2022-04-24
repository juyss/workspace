package com.juyss.consumer.workqueue;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: WorkQueueConsumer
 * @package com.juyss.consumer.workqueue
 * @project RabbitMQ
 * @date 2021/9/19 15:42
 */
@Component
public class WorkQueueConsumer {

    @RabbitListener(queuesToDeclare = {@Queue("workqueue")})
    public void consume1(String msg){
        System.out.println("1接收消息" + msg);
    }

    @RabbitListener(queuesToDeclare = {@Queue("workqueue")})
    public void consume2(String msg){
        System.out.println("2接收消息" + msg);
    }

    @RabbitListener(queuesToDeclare = {@Queue("workqueue")})
    public void consume3(String msg){
        System.out.println("3接收消息" + msg);
    }

    @RabbitListener(queuesToDeclare = {@Queue("workqueue")})
    public void consume4(String msg){
        System.out.println("4接收消息" + msg);
    }
}
