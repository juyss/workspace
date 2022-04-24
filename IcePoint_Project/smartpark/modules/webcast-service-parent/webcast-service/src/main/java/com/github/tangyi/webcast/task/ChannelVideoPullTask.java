package com.github.tangyi.webcast.task;

import com.alibaba.fastjson.JSON;
import com.github.tangyi.common.core.constant.MqConstant;
import com.github.tangyi.webcast.api.dto.req.DataReportRequest;
import com.github.tangyi.webcast.api.model.ChannelVideo;
import com.github.tangyi.webcast.api.model.ChannelWhiteList;
import com.github.tangyi.webcast.api.model.DataReport;
import com.github.tangyi.webcast.mapper.ChannelWhiteListMapper;
import com.github.tangyi.webcast.service.ChannelAuthService;
import com.github.tangyi.webcast.service.ChannelPlaybackVideoService;
import com.github.tangyi.webcast.service.ChannelService;
import com.github.tangyi.webcast.service.DataReportService;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.account.LiveListAccountRequest;
import net.polyv.live.v1.entity.account.LiveListAccountResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.v1.entity.channel.playback.LiveChannelVideoListRequest;
import net.polyv.live.v1.entity.channel.playback.LiveChannelVideoListResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthResponse;
import net.polyv.live.v1.entity.web.auth.LiveChannelWhiteListRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelWhiteListResponse;
import net.polyv.live.v1.service.account.impl.LiveAccountServiceImpl;
import net.polyv.live.v1.service.channel.impl.LiveChannelPlaybackServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 拉取保利威暂存视频的数据
 *
 * @author Xiang Long fei
 */
@Component
@EnableScheduling
public class ChannelVideoPullTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChannelPlaybackVideoService channelPlaybackVideoService;

    @Autowired
    private DataReportService dataReportService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelAuthService channelAuthService;

    @Autowired
    private RabbitTemplate amqpTemplate;

    @Autowired
    private ChannelWhiteListMapper channelWhiteListMapper;

    //在一个小时执行一次
   // @Scheduled(cron = "0 0 */1 * * ?")
   //@Scheduled(cron = "0 */1 * * * ?")
    public void pull() {
        // 增量数据
        List<ChannelVideo> channelVideosToUpdate = new LinkedList<>();
        // 当前全量数据
        List<ChannelVideo> channelVideos = channelPlaybackVideoService.findAllList(new ChannelVideo());

        // 查开启直播的记录，用于后面匹配直播名称
        DataReportRequest example = new DataReportRequest();
        List<DataReport> dataReportList = dataReportService.findAllList(example);
        //频道权限
        LiveListAccountRequest liveListAccountRequest = new LiveListAccountRequest();
        LiveListAccountResponse liveListAccountResponse;
        try {
            // 读频道ID列表
            liveListAccountRequest.setCategoryId(null).setKeyword(null).setRequestId(LiveSignUtil.generateUUID());
            liveListAccountResponse = new LiveAccountServiceImpl().listAccount(liveListAccountRequest);
            if (liveListAccountResponse != null) {
                logger.debug("拉取暂存视频信息时，查询账号下的频道列表成功,{}", JSON.toJSONString(liveListAccountResponse));
                List<Integer> channelIds = liveListAccountResponse.getChannels();
                if (!CollectionUtils.isEmpty(channelIds)) {
                    // 循环拉取暂存视频信息
                    for (Integer channelId : channelIds) {

                        LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
                        liveChannelVideoListRequest.setChannelId(String.valueOf(channelId))
                                .setRequestId(LiveSignUtil.generateUUID());
                        LiveChannelVideoListResponse liveChannelVideoListResponse = new LiveChannelPlaybackServiceImpl().listChannelVideo(
                                liveChannelVideoListRequest);
                        if (liveChannelVideoListResponse != null) {
                            logger.debug("拉取暂存视频信息时，查询视频库-直播暂存成功{}", JSON.toJSONString(liveChannelVideoListResponse));
                            // 循环，把增量数据放到channelVideosToUpdate中，并生产消息
                            liveChannelVideoListResponse.getChannelVedioInfos().forEach(channelVideoInfo -> {

                                if (CollectionUtils.isEmpty(channelVideos)) {
                                    ChannelVideo channelVideo = new ChannelVideo();
                                    BeanUtils.copyProperties(channelVideoInfo, channelVideo);
                                    channelVideo.setChannelName(getChannelName(dataReportList, channelVideo.getStartTime(), String.valueOf(channelId)));
                                    channelVideo.setFileSize(String.valueOf(channelVideoInfo.getFileSize()));
                                    ChannelVideo channelVideoAuth = setAuthType(channelId.toString(), channelVideo.getFileId());
                                    if (!ObjectUtils.isEmpty(channelVideoAuth)) {
                                        channelVideo.setType(channelVideoAuth.getType());
                                        if (!ObjectUtils.isEmpty(channelVideoAuth.getVerificationCode())) {
                                            channelVideo.setVerificationCode(channelVideoAuth.getVerificationCode());
                                        }
                                    }
                                    channelVideosToUpdate.add(channelVideo);
                                } else {
                                    if (channelVideos.stream().noneMatch(channelVideo -> channelVideo.getFileId().equals(channelVideoInfo.getFileId()))) {
                                        ChannelVideo channelVideo = new ChannelVideo();
                                        BeanUtils.copyProperties(channelVideoInfo, channelVideo);
                                        channelVideo.setChannelName(getChannelName(dataReportList, channelVideo.getStartTime(), String.valueOf(channelId)));
                                        channelVideo.setFileSize(String.valueOf(channelVideoInfo.getFileSize()));
                                        ChannelVideo channelVideoAuth = setAuthType(channelId.toString(), channelVideo.getFileId());
                                        if (!ObjectUtils.isEmpty(channelVideoAuth)) {
                                            channelVideo.setType(channelVideoAuth.getType());
                                            if (!ObjectUtils.isEmpty(channelVideoAuth.getVerificationCode())) {
                                                channelVideo.setVerificationCode(channelVideoAuth.getVerificationCode());
                                            }
                                        }
                                        channelVideosToUpdate.add(channelVideo);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        } catch (PloyvSdkException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error("拉取暂存视频信息时，SDK调用异常", e);
        }
        // 发送消息
        channelVideos.stream().filter(channelVideo -> "N".equals(channelVideo.getObsUploaded())).forEach(channelVideo -> amqpTemplate.convertAndSend(MqConstant.CHANNEL_VIDEO_QUEUE, channelVideo));
        // 存数据
        if (!CollectionUtils.isEmpty(channelVideosToUpdate)) {
            channelPlaybackVideoService.saveAll(channelVideosToUpdate);
            // 发送消息
            channelVideosToUpdate.forEach(channelVideo -> amqpTemplate.convertAndSend(MqConstant.CHANNEL_VIDEO_QUEUE, channelVideo));
        }

    }

    private String getChannelName(List<DataReport> dataReportList, Date startTime, String channelId) {
        String result = dataReportList.stream().filter(dataReport -> dataReport.getStartTime().compareTo(startTime) <= 0 && channelId.equals(dataReport.getChannelId())).max(Comparator.comparing(DataReport::getStartTime)).map(DataReport::getName).orElse("");
        // 空的话 去调保利威服务
        if (StringUtils.isEmpty(result)) {
            return channelService.getChannelBasicInfo(channelId).getName();
        }
        return result;
    }

    /**
     * 获取频道验证码
     * return List  list.size=2 第一个元素是主要观看条件 第二个是次要观看条件
     */
    public List<String> getCode(String channelId) {
        LiveChannelAuthRequest liveChannelAuthRequest = new LiveChannelAuthRequest();
        liveChannelAuthRequest.setRequestId(LiveSignUtil.generateUUID());
        liveChannelAuthRequest.setChannelId(channelId);
        LiveChannelAuthResponse channelAuth = channelAuthService.getChannelAuth(liveChannelAuthRequest);
        List<LiveChannelSettingRequest.AuthSetting> authSettings = channelAuth.getAuthSettings();
        List<String> list = new ArrayList<>();
        for (LiveChannelSettingRequest.AuthSetting authSetting : authSettings) {
            String authCode = authSetting.getAuthCode();
            if (!ObjectUtils.isEmpty(authCode)) {
                list.add(authCode);
            }

        }


        return list;
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
