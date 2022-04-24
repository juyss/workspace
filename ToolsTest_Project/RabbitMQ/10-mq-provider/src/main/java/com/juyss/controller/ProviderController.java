package com.juyss.controller;

import com.alibaba.fastjson.JSON;
import com.juyss.pojo.People;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author juyss
 * @version 1.0
 * @ClassName: ProviderController
 * @package com.juyss.controller
 * @project RabbitMQ
 * @date 2021/9/19 14:01
 */
@RestController
@RequestMapping("/mq")
public class ProviderController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProviderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 基本消息模型 一对一，直接通过队列发送消息（底层使用默认交换机，绑定了所有队列）
     * work queue
     * @param people
     * @return
     */
    @PostMapping("/basic")
    public String basicMqProvider(@RequestBody People people){
        String jsonString = JSON.toJSONString(people);
        rabbitTemplate.convertAndSend("workqueue",jsonString);
        return "OK";
    }

    @PostMapping("/funout")
    public String funoutProvider(@RequestBody People people){
        String jsonString = JSON.toJSONString(people);
        rabbitTemplate.convertAndSend("funoutTest","",jsonString);
        return "OK";
    }
}
