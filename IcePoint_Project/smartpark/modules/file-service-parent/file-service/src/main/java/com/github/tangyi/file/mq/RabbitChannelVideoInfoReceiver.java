package com.github.tangyi.file.mq;

import com.alibaba.fastjson.JSON;
import com.github.tangyi.common.basic.properties.SysProperties;
import com.github.tangyi.common.core.constant.MqConstant;
import com.github.tangyi.common.core.utils.FileUtil;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.file.api.model.Attachment;
import com.github.tangyi.file.mapper.ChannelVideoMapper;
import com.github.tangyi.file.uploader.UploadInvoker;
import com.github.tangyi.file.util.DownloadMain;
import com.github.tangyi.webcast.api.model.ChannelVideo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


import java.io.*;
import java.util.List;

/**
 * @author ck
 */
@Slf4j
@Component
public class RabbitChannelVideoInfoReceiver {

    @Autowired
    private RabbitTemplate amqpTemplate;

    @Autowired
    private ChannelVideoMapper channelVideoMapper;

    @RabbitListener(queues = MqConstant.CHANNEL_VIDEO_QUEUE)
    public void receiver(String channelVideoJson) {
        log.info("接收到消息 开始上传{}", channelVideoJson);
        ChannelVideo channelVideo = JSON.parseObject(channelVideoJson, ChannelVideo.class);
        System.out.println(channelVideo.getChannelSessionId());
        if (ObjectUtils.isEmpty(channelVideo)) {
            return;
        }
        // 保持数据一致 回放状态y 表示已经下载完成
        List<ChannelVideo> getstatus = channelVideoMapper.getstatus(channelVideo);
        if (!ObjectUtils.isEmpty(getstatus)) {
            return;
        }
        // 如果是m3u8格式，不用上传，直接发送消息
        if ("m3u8".equals(channelVideo.getFileName().substring(channelVideo.getFileName().lastIndexOf('.') + 1))) {
            amqpTemplate.convertAndSend(MqConstant.CHANNEL_VIDEO_ACK_QUEUE, channelVideo);
            return;
        }
        String filepathString = SpringContextHolder.getApplicationContext().getBean(SysProperties.class).getAttachPath() + File.separator + "channel-video-temp";
        File filepath = new File(filepathString);
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        String filename = filepathString + File.separator + channelVideo.getUrl().substring(channelVideo.getUrl().lastIndexOf("/") + 1);
        String fileTemp = null;
        try {
            //DownloadMain 开启六个线程池 分段下载 返回下载好的文件
            fileTemp = DownloadMain.opload(channelVideo.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("下载失败");
        }
        log.debug("下载完毕，开始上传 channelVideo: {}", channelVideo);
        try {
            Attachment attachment = new Attachment();
            //代表上传华为云
            attachment.setUploadType(4);
            attachment.setCommonValue("mq", "mq", "mq");
            attachment.setAttachType(FileUtil.getFileNameEx(filename));
            attachment.setAttachSize(channelVideo.getFileSize());
            attachment.setAttachName(channelVideo.getUrl().substring(channelVideo.getUrl().lastIndexOf("/") + 1));
            File uploadFile = new File(fileTemp);
            attachment = UploadInvoker.getInstance().uploadFile(attachment, uploadFile);
            //上传完成后  删除临时文件
            uploadFile.delete();
            // 发送确认消息
            channelVideo.setObsUrl(attachment.getPreviewUrl());
            channelVideoJson = JSON.toJSONString(channelVideo);
            amqpTemplate.convertAndSend(MqConstant.CHANNEL_VIDEO_ACK_QUEUE, channelVideoJson);
        } catch (Exception e) {
            log.error("upload attachment error: {}", e.getMessage(), e);
        }
        log.debug("上传完毕，删除文件 channelVideo: {}", channelVideo);
        // file.delete();
    }


}
