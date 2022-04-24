package com.juyss.controller;

import com.juyss.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MsgController
 * @Desc:
 * @package com.juyss.controller
 * @project RabbitMQ
 * @date 2020/11/23 14:12
 */
@RestController
public class MsgController {

    @Autowired
    private MessageSender sender;

    @RequestMapping("/")
    public String Hello(){
        return "Hello,RabbitMQ";
    }

    /**
     * 提供者向队列发送消息,接受者从队列接收消息
     * 1. 一对一发送消息 (Hello World示例)
     * 2. 一对多发送消息,一个消息只能给一个接受者,公平分配 (Work Queues示例)
     * @param msg 消息
     * @return String
     */
    @RequestMapping("/send/{msg}")
    public String sendMsg(@PathVariable("msg")String msg){
        sender.sendMessage(msg);
        return "消息发送成功"+msg;
    }

    /**
     * 提供者向交换机(exchange)发送消息,接收者从指定交换机找到消息队列获取消息
     * 一对多同时发送,一个消息同时给多个接受者 (Publish/Subscribe示例)
     * @param msg 消息
     * @return String
     */
    @RequestMapping("/fanout/{msg}")
    public String sendFanoutMsg(@PathVariable("msg")String msg){
        sender.fanout(msg);
        return "消息发送成功"+msg;
    }

    /**
     * 提供者向交换机(exchange)发送消息,同时指定路由key,然后接受者寻找指定交换机,判断路由key是否匹配,然后接受对应消息
     * 一对多同时发送,一个消息可以给多个接受者,但是需要路由key匹配 (Routing示例)
     * @param key 路由key
     * @param msg 消息
     * @return String
     */
    @RequestMapping("/route/{key}/{msg}")
    public String sendRouting(@PathVariable("key")String key,
                              @PathVariable("msg")String msg){
        sender.routing(msg,key);
        return "消息发送成功"+msg;
    }

    /**
     * 提供者向交换机发送消息,指定路由key,一般由两个以上字段,然后接受者找到交换机,根据自己的路由key匹配规则,获取对应消息.
     * @param key 路由key
     * @param msg 消息
     * @return String
     */
    @RequestMapping("/topic/{key}/{msg}")
    public String sendTopic(@PathVariable("key")String key,
                            @PathVariable("msg")String msg){
        sender.topic(msg,key);
        return "消息发送成功"+msg;
    }

}
