package com.github.tangyi.webcast.service;

import com.alibaba.fastjson.JSON;
import com.github.tangyi.common.core.exceptions.CommonException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.channel.operate.LiveCreateChannelPPTRecordRequest;
import net.polyv.live.v1.entity.channel.operate.LiveListChannelPPTRecordRequest;
import net.polyv.live.v1.entity.channel.operate.LiveListChannelPPTRecordResponse;
import net.polyv.live.v1.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.springframework.stereotype.Service;

/**
 * 频道service
 *
 * @author Xiang Longfei
 * @date 2021/01/22
 */
@Slf4j
@AllArgsConstructor
@Service
public class ChannelPptService {

    public Boolean createChannelPPTRecord(LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest) {
        Boolean liveCreateChannelPPTRecordResponse;
        try {
            liveCreateChannelPPTRecordRequest.setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().createChannelPPTRecordTask(
                    liveCreateChannelPPTRecordRequest);
            if (liveCreateChannelPPTRecordResponse) {
                //to do something ......
                log.debug("创建重制课件任务成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveCreateChannelPPTRecordResponse;
    }

    public LiveListChannelPPTRecordResponse listPPTRecord(LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest) {
        LiveListChannelPPTRecordResponse liveListChannelPPTRecordResponse;
        try {
            liveListChannelPPTRecordRequest.setRequestId(LiveSignUtil.generateUUID());
            liveListChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().listPPTRecord(
                    liveListChannelPPTRecordRequest);
            if (liveListChannelPPTRecordResponse != null) {
                //to do something ......
                log.debug("查询课件重制任务列表信息成功{}", JSON.toJSONString(liveListChannelPPTRecordResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveListChannelPPTRecordResponse;
    }
}


