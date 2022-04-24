package com.github.tangyi.tools.mq;

import com.github.tangyi.common.core.constant.MqConstant;
import com.github.tangyi.common.core.model.req.ParkNewsReq;
import com.github.tangyi.tools.service.WxPushMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gaokx
 * @Description
 * @create 2021-03-25 13:38
 **/
@Slf4j
@Service
public class RabbitPushNewsWxReceiver {

  @Autowired
  WxPushMessageService wxPushMessageService;

  @RabbitListener(queues = {MqConstant.PUSH_NEWS_WX_QUEUE})
  public void submitExamination(ParkNewsReq req) {
    log.debug("req: {}", req);
    try {
      // 推送成功
      if (wxPushMessageService.pushNews(req)) {
        log.debug("wxPushMessageService.pushNews  success");
      } else {
        log.debug("wxPushMessageService.pushNews  failed");
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }
}
