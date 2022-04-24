package com.juyss.service;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MessageSender
 * @Desc:
 * @package com.juyss.service
 * @project RabbitMQ
 * @date 2020/11/23 14:08
 */
public interface MessageSender {

    /**
     * 发送消息
     * Direct模式 : 一对一
     * Work queues模式 : 一对多
     * @param msg 请求传入的消息
     */
    void sendMessage(String msg);

    /**
     * Publish/Subscribe示例
     * 交换机类型为fanout
     * @param msg 消息
     */
    void fanout(String msg);

    /**
     * Routing示例
     * 交换机类型为direct(默认)
     * @param msg 消息
     * @param routeKey 路由key
     */
    void routing(String msg,String routeKey);

    /**
     * Topic示例
     * 按路由key匹配
     * @param msg 消息
     * @param routeKey 路由key
     */
    void topic(String msg,String routeKey);
}
