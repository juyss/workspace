package com.github.tangyi.webcast.mq;

import com.alibaba.fastjson.JSON;
import com.github.tangyi.common.core.constant.MqConstant;
import com.github.tangyi.webcast.api.model.ChannelSessionInfo;
import com.github.tangyi.webcast.api.model.ChannelVideo;
import com.github.tangyi.webcast.service.ChannelPlaybackVideoService;
import com.github.tangyi.webcast.service.ChannelSessionService;
import com.github.tangyi.webcast.task.ChannelVideoPullTask;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
@Slf4j
@AllArgsConstructor
@Component
public class RabbitChannelVideoAckReceiver {

    @Autowired
    private ChannelPlaybackVideoService channelPlaybackVideoService;

    @Autowired
    private ChannelSessionService channelSessionService;

    @Autowired
    ChannelVideoPullTask channelVideoPullTask;

    @RabbitListener(queues = MqConstant.CHANNEL_VIDEO_ACK_QUEUE)
    public void receiver(String channelVideoJson) {
        ChannelVideo channelVideo = JSON.parseObject(channelVideoJson, ChannelVideo.class);
        // 更新数据
        try {

            if (!StringUtils.isEmpty(channelVideo.getObsUrl())) {
                channelVideo.setObsUploaded("Y");
                channelPlaybackVideoService.update(channelVideo);
                log.info("ack channelVider{}",channelVideo);
                //当直播合并时   并没有sessionId  所以直接结束
                if(ObjectUtils.isEmpty(channelVideo.getChannelSessionId())){
                    return;
                }
                ChannelSessionInfo channelSessionInfo = new ChannelSessionInfo();
                channelSessionInfo.setSessionId(channelVideo.getChannelSessionId());
                channelSessionInfo.setUploadStatus("Y");
                channelSessionService.updateStatus(channelSessionInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
