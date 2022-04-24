package com.github.tangyi.webcast.task;

import com.alibaba.fastjson.JSON;
import com.github.tangyi.common.core.constant.MqConstant;
import com.github.tangyi.webcast.api.model.ChannelSessionInfo;
import com.github.tangyi.webcast.api.model.ChannelVideo;
import com.github.tangyi.webcast.api.model.ChannelWhiteList;
import com.github.tangyi.webcast.mapper.ChannelWhiteListMapper;
import com.github.tangyi.webcast.service.ChannelAuthService;
import com.github.tangyi.webcast.service.ChannelSessionService;
import com.github.tangyi.webcast.service.ChannelVideoService;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInfoRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInfoResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.v1.entity.channel.playback.LiveChannelVideoListRequest;
import net.polyv.live.v1.entity.channel.playback.LiveChannelVideoListResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelWhiteListResponse;
import net.polyv.live.v1.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.v1.service.channel.impl.LiveChannelPlaybackServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 定时查询直播状态
 *
 * @author ck
 */
@Component
@EnableScheduling
@Slf4j
public class ChannelLocalTask {

    @Autowired
    private ChannelSessionService channelSessionService;

    @Autowired
    private ChannelWhiteListMapper channelWhiteListMapper;

    @Autowired
    private ChannelAuthService channelAuthService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ChannelVideoService channelVideoService;


    @Scheduled(cron = "0 */5 * * * ?")
    public void localVideoTask() {
        //查询前5分钟的更新的数据
        List<ChannelSessionInfo> channelSessionInfos = channelSessionService.queryByTime();
        //过滤掉已上传的 或者上传中的
        channelSessionInfos = channelSessionInfos.stream()
                .filter(a -> a.getUploadStatus().equals("N"))
                .collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(channelSessionInfos)) {
            //遍历过滤
            for (ChannelSessionInfo channelSessionInfo : channelSessionInfos) {
                //查询视频库列表
                LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
                LiveChannelVideoListResponse liveChannelVideoListResponse;
                try {
                    liveChannelVideoListRequest.setChannelId(channelSessionInfo.getChannelId())
                            .setSessionId(channelSessionInfo.getSessionId())
                            .setRequestId(LiveSignUtil.generateUUID());
                    liveChannelVideoListResponse = new LiveChannelPlaybackServiceImpl().listChannelVideo(
                            liveChannelVideoListRequest);
                    if (liveChannelVideoListResponse != null) {
                        List<LiveChannelVideoListResponse.ChannelVedioInfo> channelVedioInfos
                                = liveChannelVideoListResponse.getChannelVedioInfos();
                        if (!ObjectUtils.isEmpty(channelVedioInfos)) {

                            channelVedioInfos.forEach(channelVedioInfo -> {
                                //封装数据
                                ChannelVideo channelVideo = new ChannelVideo();
                                BeanUtils.copyProperties(channelVedioInfo, channelVideo);
                                channelVideo.setFileSize(String.valueOf(channelVedioInfo.getFileSize()));
                                ChannelVideo channelVideoAuth = setAuthType(channelVedioInfo.getChannelId(), channelVideo.getFileId());
                                if (!ObjectUtils.isEmpty(channelVideoAuth)) {
                                    channelVideo.setType(channelVideoAuth.getType());
                                    if (!ObjectUtils.isEmpty(channelVideoAuth.getVerificationCode())) {
                                        channelVideo.setVerificationCode(channelVideoAuth.getVerificationCode());
                                    }
                                }
                                channelVideo.setObsUploaded("N");
                                //查询频道
                                LiveChannelInfoRequest liveChannelInfoRequest = new LiveChannelInfoRequest();
                                LiveChannelInfoResponse liveChannelInfoResponse;
                                try {
                                    liveChannelInfoRequest.setChannelId(channelVedioInfo.getChannelId()).setRequestId(LiveSignUtil.generateUUID());
                                    liveChannelInfoResponse = new LiveChannelOperateServiceImpl().getChannelInfo(liveChannelInfoRequest);
                                    if (liveChannelInfoResponse != null) {
                                        //设置频道名称
                                        channelVideo.setChannelName(liveChannelInfoResponse.getName());
                                    }
                                    //修改channelSessionInfo状态为上传中
                                    String channelVideoJson = JSON.toJSONString(channelVideo);
                                    log.debug("发送消息{}", channelVideo);
                                    channelSessionInfo.setUploadStatus("Y");
                                    channelSessionService.updateStatus(channelSessionInfo);
                                    rabbitTemplate.convertAndSend(MqConstant.CHANNEL_VIDEO_QUEUE,channelVideoJson);
                                    //插入到webcast_channel_video中
                                    channelVideoService.insert(channelVideo);

                                    //发送消息
                                } catch (PloyvSdkException e) {
                                    //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
                                    log.error(e.getMessage(), e);
                                    // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
                                    throw e;
                                } catch (Exception e) {
                                    log.error("SDK调用异常", e);
                                }


                            });
                        }


                        log.debug("查询频道录制视频信息成功{}", JSON.toJSONString(liveChannelVideoListResponse));
                    }
                } catch (PloyvSdkException e) {
                    //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
                    log.error(e.getMessage(), e);
                    // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
                    throw e;
                } catch (Exception e) {
                    log.error("SDK调用异常", e);
                }
            }
        }
    }

    /**
     * 根据channelId获取权限类型
     *
     * @return channelAuth
     */
    public List<LiveChannelSettingRequest.AuthSetting> getType(String channelId) {
        LiveChannelAuthRequest liveChannelAuthRequest = new LiveChannelAuthRequest();
        liveChannelAuthRequest.setRequestId(LiveSignUtil.generateUUID());
        liveChannelAuthRequest.setChannelId(channelId);
        LiveChannelAuthResponse channelAuth = channelAuthService.getChannelAuth(liveChannelAuthRequest);
        return channelAuth.getAuthSettings();
    }

    /**
     * 设置观看条件
     */
    public ChannelVideo setAuthType(String channelId, String fileId) {
        ChannelVideo channelVideo = new ChannelVideo();
        List<LiveChannelSettingRequest.AuthSetting> type = getType(channelId);
        //只获取主要观看条件
        String authType = type.get(0).getAuthType();
        //通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom，外部授权-external,直接授权-direct
        if (!ObjectUtils.isEmpty(authType)) {
            if ("phone".equals(authType)) {//白名单观看
                channelVideo.setType("phone");
                LiveChannelWhiteListRequest liveChannelWhiteListRequest = new LiveChannelWhiteListRequest();
                liveChannelWhiteListRequest.setChannelId(channelId);
                liveChannelWhiteListRequest.setRank(1);
                liveChannelWhiteListRequest.setRequestId("-1");
                LiveChannelWhiteListResponse channelWhiteList = channelAuthService.getChannelWhiteList(liveChannelWhiteListRequest);
                List<LiveChannelWhiteListResponse.ChannelWhiteList> contents = channelWhiteList.getContents();
                if (!ObjectUtils.isEmpty(contents)) {
                    for (LiveChannelWhiteListResponse.ChannelWhiteList content : contents) {
                        ChannelWhiteList channel = new ChannelWhiteList();
                        channel.setName(content.getName());
                        channel.setPhone(content.getPhone());
                        channel.setFileId(fileId);
                        channelWhiteListMapper.insertWhiteList(channel);
                    }
                }

                return channelVideo;
            }
            if ("code".equals(authType)) {//验证码观看
                channelVideo.setType("code");
                channelVideo.setVerificationCode(type.get(0).getAuthCode());
            }
        }
        return channelVideo;
    }

}
