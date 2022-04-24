package com.github.tangyi.webcast.service;

import com.alibaba.fastjson.JSON;
import com.github.tangyi.common.core.exceptions.CommonException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.web.menu.*;
import net.polyv.live.v1.service.web.impl.LiveWebMenuServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.springframework.stereotype.Service;

/**
 * 频道service
 *
 * @author Xiang Longfei
 * @date 2021/01/31
 */
@Slf4j
@AllArgsConstructor
@Service
public class ChannelMenuService {

    public LiveAddChannelMenuResponse addChannelMenu(LiveAddChannelMenuRequest liveAddChannelMenuRequest) {
        LiveAddChannelMenuResponse liveAddChannelMenuResponse;
        try {
            liveAddChannelMenuRequest.setRequestId(LiveSignUtil.generateUUID());
            liveAddChannelMenuResponse = new LiveWebMenuServiceImpl().addChannelMenu(liveAddChannelMenuRequest);
            if (liveAddChannelMenuResponse != null) {
                //to do something ......
                log.debug("添加频道菜单成功,{}", JSON.toJSONString(liveAddChannelMenuResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveAddChannelMenuResponse;
    }

    public Boolean updateChannelMenuSort(LiveUpdateChannelMenuSortRequest liveUpdateChannelMenuSortRequest) {
        Boolean liveUpdateChannelMenuSortResponse;
        try {
            liveUpdateChannelMenuSortRequest.setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMenuSortResponse = new LiveWebMenuServiceImpl().updateChannelMenuSort(
                    liveUpdateChannelMenuSortRequest);
            if (liveUpdateChannelMenuSortResponse != null) {
                //to do something ......
                log.debug("设置频道菜单排序成功,{}", JSON.toJSONString(liveUpdateChannelMenuSortResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveUpdateChannelMenuSortResponse;
    }

    public Boolean updateChannelMenuInfo(LiveUpdateChannelMenuInfoRequest liveUpdateChannelMenuInfoRequest) {
        Boolean liveUpdateChannelMenuInfoResponse;
        try {
            liveUpdateChannelMenuInfoRequest.setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMenuInfoResponse = new LiveWebMenuServiceImpl().updateChannelMenuInfo(
                    liveUpdateChannelMenuInfoRequest);
            if (liveUpdateChannelMenuInfoResponse != null) {
                //to do something ......
                log.debug("设置指定菜单id的频道菜单信息成功,{}", JSON.toJSONString(liveUpdateChannelMenuInfoResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveUpdateChannelMenuInfoResponse;
    }

    public Boolean deleteChannelMenu(String menuIds) {
        LiveDeleteChannelMenuRequest liveDeleteChannelMenuRequest = new LiveDeleteChannelMenuRequest();
        Boolean liveDeleteChannelMenuResponse;
        try {
            liveDeleteChannelMenuRequest.setMenuIds(menuIds).setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelMenuResponse = new LiveWebMenuServiceImpl().deleteChannelMenu(
                    liveDeleteChannelMenuRequest);
            if (liveDeleteChannelMenuResponse) {
                //to do something ......
                log.debug("删除频道菜单成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveDeleteChannelMenuResponse;
    }

    public Boolean setConsultingEnabled(LiveSetConsultingEnabledRequest liveSetConsultingEnabledRequest) {
        Boolean liveSetConsultingEnabledResponse;
        try {
            liveSetConsultingEnabledRequest.setRequestId(LiveSignUtil.generateUUID());
            liveSetConsultingEnabledResponse = new LiveWebMenuServiceImpl().setConsultingEnabled(
                    liveSetConsultingEnabledRequest);
            if (liveSetConsultingEnabledResponse) {
                //to do something ......
                log.debug("设置提问功能显示开关成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveSetConsultingEnabledResponse;
    }

    public LiveGetChannelImageTextResponse getChannelImageText(LiveGetChannelImageTextRequest liveGetChannelImageTextRequest) {
        LiveGetChannelImageTextResponse liveGetChannelImageTextResponse;
        try {
            liveGetChannelImageTextRequest.setRequestId(LiveSignUtil.generateUUID());
            liveGetChannelImageTextResponse = new LiveWebMenuServiceImpl().getChannelImageText(
                    liveGetChannelImageTextRequest);
            if (liveGetChannelImageTextResponse != null) {
                //to do something ......
                log.debug("测试查询频道图文内容列表成功，{}", JSON.toJSONString(liveGetChannelImageTextResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveGetChannelImageTextResponse;
    }
}
