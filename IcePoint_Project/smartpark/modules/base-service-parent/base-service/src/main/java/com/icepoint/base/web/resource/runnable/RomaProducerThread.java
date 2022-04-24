package com.icepoint.base.web.resource.runnable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.it.eip.ump.client.producer.Producer;
import com.huawei.it.eip.ump.client.producer.SendResult;
import com.huawei.it.eip.ump.common.exception.UmpException;
import com.huawei.it.eip.ump.common.message.Message;
import com.icepoint.base.api.domain.MapBasedEntity;
import com.icepoint.base.mqs.MqsProducerProperties;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class RomaProducerThread implements Runnable {

    private MapBasedEntity result;
    private Map<String, Object> entity;
    private MqsProducerProperties mqsProducerProperties;
    private Producer producer;

    public RomaProducerThread(MapBasedEntity result, Map<String, Object> entity, MqsProducerProperties mqsProducerProperties, Producer producer) {
        this.result = result;
        this.entity = entity;
        this.mqsProducerProperties = mqsProducerProperties;
        this.producer = producer;
    }

    @Override
    public void run() {
        try {
            // 睡眠2分钟
            Thread.sleep(120000);

            String key = result.getProperty("id").getValue().toString();
            ObjectMapper mapper = new ObjectMapper();
            entity.remove("appId");
            entity.remove("ownerId");
            entity.remove("createUser");
            entity.remove("updateUser");
            entity.remove("deleted");
            entity.put("id", key);
            String data = mapper.writeValueAsString(entity);
            Message message = new Message();
            message.setBusinessId(mqsProducerProperties.getBusinessId()); // 设置消息业务标示，便于追踪消息轨迹
            message.setTags(mqsProducerProperties.getTag()); // 设置消息标签
            message.setBody(data.getBytes("UTF-8"));
            SendResult sendResult = producer.send(message);
            if (sendResult.isSuccess()) {
                // 发送成功的逻辑处理
                System.out.println("message++++++" + sendResult.getMessageId());
            } else {
                // 发送失败的逻辑处理
                System.out.println(sendResult.toString());
            }
        } catch (UmpException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
