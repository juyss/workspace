package com.juyss.service.impl;

import com.juyss.service.MessageSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MessageSenderImpl
 * @Desc:
 * @package com.juyss.service.impl
 * @project RabbitMQ
 * @date 2020/11/23 14:10
 */
@Service
public class MessageSenderImpl implements MessageSender {

    private final RabbitTemplate template;

    @Autowired
    public MessageSenderImpl(RabbitTemplate template) {
        this.template = template;
    }

    /**
     * 发送消息
     * Direct模式 : 一对一
     * Work queues模式 : 一对多
     * @param msg 请求传入的消息
     */
    @Override
    public void sendMessage(String msg) {
        template.convertAndSend("hello","消息提供者发送消息:"+msg);
    }

    /**
     * Publish/Subscribe示例
     * 交换机类型为fanout
     *
     * @param msg 消息
     */
    @Override
    public void fanout(String msg) {
        template.convertAndSend("logs", "", "消息提供者发送消息"+msg);
    }

    /**
     * Routing示例
     * 交换机类型为direct(默认)
     *
     * @param msg      消息
     * @param routeKey 路由key
     */
    @Override
    public void routing(String msg,String routeKey) {
        template.convertAndSend("routing", routeKey, "消息提供者发送消息"+msg);
    }

    /**
     * Topic示例
     * 按路由key匹配
     *
     * @param msg      消息
     * @param routeKey 路由key
     */
    @Override
    public void topic(String msg, String routeKey) {
        template.convertAndSend("topic",routeKey, "消息提供者发送消息"+msg);
    }
}
