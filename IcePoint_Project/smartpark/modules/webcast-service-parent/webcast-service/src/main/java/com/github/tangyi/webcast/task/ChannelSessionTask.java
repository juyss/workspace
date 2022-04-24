package com.github.tangyi.webcast.task;

import com.alibaba.fastjson.JSON;
import com.github.tangyi.webcast.api.model.ChannelSessionInfo;
import com.github.tangyi.webcast.service.ChannelSessionService;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;

import net.polyv.live.v1.entity.account.LiveListAccountRequest;
import net.polyv.live.v1.entity.account.LiveListAccountResponse;
import net.polyv.live.v1.entity.channel.playback.LiveListChannelSessionInfoRequest;
import net.polyv.live.v1.entity.channel.playback.LiveListChannelSessionInfoResponse;
import net.polyv.live.v1.service.account.impl.LiveAccountServiceImpl;
import net.polyv.live.v1.service.channel.impl.LiveChannelPlaybackServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 把保利威的直播场次 信息同步到数据库
 *
 *
 */
@Component
@Slf4j
@EnableScheduling
public class ChannelSessionTask {

    @Autowired
    private ChannelSessionService channelSessionService ;

    @Scheduled(cron = "0 */5 * * * ?")
    public void LocalVideoTask() throws IOException, NoSuchAlgorithmException {
        //查询全部的channelSession;
        List<ChannelSessionInfo> channelSessionInfos = channelSessionService.queryAll();
        List<Integer> channels = new ArrayList<>();
        LiveListAccountRequest liveListAccountRequest = new LiveListAccountRequest();
        LiveListAccountResponse liveListAccountResponse;
        try {
            liveListAccountRequest.setCategoryId(null).setKeyword(null).setRequestId(LiveSignUtil.generateUUID());
            liveListAccountResponse = new LiveAccountServiceImpl().listAccount(liveListAccountRequest);
            if (liveListAccountResponse != null) {
                //to do something ......
              channels = liveListAccountResponse.getChannels();
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
        try {
            for (Integer channel : channels) {
                LiveListChannelSessionInfoRequest liveListChannelSessionInfoRequest = new LiveListChannelSessionInfoRequest();
                LiveListChannelSessionInfoResponse liveListChannelSessionInfoResponse;
                liveListChannelSessionInfoRequest.setRequestId(LiveSignUtil.generateUUID());
                liveListChannelSessionInfoRequest.setChannelId(channel.toString());
                liveListChannelSessionInfoResponse = new LiveChannelPlaybackServiceImpl().listChannelSessionInfo(
                        liveListChannelSessionInfoRequest);
                if (liveListChannelSessionInfoResponse != null) {
                    List<LiveListChannelSessionInfoResponse.ChannelSessionInfo> contents = liveListChannelSessionInfoResponse.getContents();
                    //过滤掉还未结束的直播
                    contents = contents.stream().filter(a->!ObjectUtils.isEmpty(a.getEndTime())).collect(Collectors.toList());
                contents.forEach(content->{
                    if(ObjectUtils.isEmpty(channelSessionInfos)){
                        //列表为空  初始化全部数据
                        ChannelSessionInfo channelSessionInfo = new ChannelSessionInfo();
                        BeanUtils.copyProperties(content,channelSessionInfo);
                        channelSessionInfo.setUploadStatus("N");
                        channelSessionService.insert(channelSessionInfo);
                    }else{
                        //筛选过滤
                            List<ChannelSessionInfo> collect = channelSessionInfos
                                .stream()
                                .filter(a -> a.getSessionId().equals(content.getSessionId()))
                                  //为空  代表直播还未结束
                                .collect(Collectors.toList());
                        //判断collet是否存在
                        if(ObjectUtils.isEmpty(collect)){
                            //插入
                            ChannelSessionInfo channelSessionInfo = new ChannelSessionInfo();
                            BeanUtils.copyProperties(content,channelSessionInfo);
                            channelSessionInfo.setUploadStatus("N");
                            channelSessionService.insert(channelSessionInfo);
                        }
                    }
                });
                    log.debug("测试查询频道直播场次信息成功{}", JSON.toJSONString(liveListChannelSessionInfoResponse));
                }
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
}
