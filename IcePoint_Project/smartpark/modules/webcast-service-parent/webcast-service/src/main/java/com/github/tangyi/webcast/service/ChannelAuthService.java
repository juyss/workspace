package com.github.tangyi.webcast.service;

import com.alibaba.fastjson.JSON;
import com.github.tangyi.common.core.exceptions.CommonException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.web.auth.*;
import net.polyv.live.v1.service.web.impl.LiveWebAuthServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.springframework.stereotype.Service;

/**
 * @author Xiang Longfei
 * @date 2021/01/21
 */
@Slf4j
@AllArgsConstructor
@Service
public class ChannelAuthService {

    public LiveChannelAuthResponse getChannelAuth(LiveChannelAuthRequest liveChannelAuthRequest) {
        LiveChannelAuthResponse liveChannelAuthResponse;
        try {
            liveChannelAuthRequest.setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthResponse = new LiveWebAuthServiceImpl().getChannelAuth(liveChannelAuthRequest);
            if (liveChannelAuthResponse != null) {
                //to do something ......
                log.debug("查询直播频道观看条件成功,{}", JSON.toJSONString(liveChannelAuthResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelAuthResponse;
    }

    /**
     * 设置观看条件
     * @param liveUpdateChannelAuthRequest
     * @return
     */
    public Boolean updateChannelAuth(LiveUpdateChannelAuthRequest liveUpdateChannelAuthRequest) {
        Boolean liveUpdateChannelAuthResponse;
        try {
            liveUpdateChannelAuthRequest.setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelAuthResponse = new LiveWebAuthServiceImpl().updateChannelAuth(
                    liveUpdateChannelAuthRequest);
            if (liveUpdateChannelAuthResponse) {
                log.debug("测试设置观看条件成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveUpdateChannelAuthResponse;
    }

    public Boolean createChannelWhiteList(LiveCreateChannelWhiteListRequest liveCreateChannelWhiteListRequest) {
        Boolean liveCreateChannelWhiteListResponse;
        try {
            liveCreateChannelWhiteListRequest.setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelWhiteListResponse = new LiveWebAuthServiceImpl().createChannelWhiteList(
                    liveCreateChannelWhiteListRequest);
            if (liveCreateChannelWhiteListResponse) {
                //to do something ......
                log.debug("添加单个白名单-全局白名单成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveCreateChannelWhiteListResponse;
    }

    public LiveChannelWhiteListResponse getChannelWhiteList(LiveChannelWhiteListRequest liveChannelWhiteListRequest) {
        LiveChannelWhiteListResponse liveChannelWhiteListResponse;
        try {
            liveChannelWhiteListRequest.setRequestId(LiveSignUtil.generateUUID());
            liveChannelWhiteListResponse = new LiveWebAuthServiceImpl().getChannelWhiteList(
                    liveChannelWhiteListRequest);
            if (liveChannelWhiteListResponse != null) {
                //to do something ......
                log.debug("查询频道观看白名单列表成功,{}", JSON.toJSONString(liveChannelWhiteListResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelWhiteListResponse;
    }

    public Boolean updateChannelWhiteList(LiveUpdateChannelWhiteListRequest liveUpdateChannelWhiteListRequest) {
        Boolean liveUpdateChannelWhiteListResponse;
        try {
            liveUpdateChannelWhiteListRequest.setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelWhiteListResponse = new LiveWebAuthServiceImpl().updateChannelWhiteList(
                    liveUpdateChannelWhiteListRequest);
            if (liveUpdateChannelWhiteListResponse) {
                //to do something ......
                log.debug("更新白名单成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveUpdateChannelWhiteListResponse;
    }

    public Boolean deleteChannelWhiteList(LiveDeleteChannelWhiteListRequest liveDeleteChannelWhiteListRequest) {
        Boolean liveDeleteChannelWhiteListResponse;
        try {
            liveDeleteChannelWhiteListRequest.setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelWhiteListResponse = new LiveWebAuthServiceImpl().deleteChannelWhiteList(
                    liveDeleteChannelWhiteListRequest);
            if (liveDeleteChannelWhiteListResponse) {
                //to do something ......
                log.debug("删除白名单成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveDeleteChannelWhiteListResponse;
    }
}


