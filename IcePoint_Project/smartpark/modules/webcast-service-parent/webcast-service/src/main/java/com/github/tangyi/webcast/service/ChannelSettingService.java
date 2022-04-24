package com.github.tangyi.webcast.service;

import com.alibaba.fastjson.JSON;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.webcast.api.dto.req.MarqueeParameterRequest;
import com.github.tangyi.webcast.api.model.ChannelMarquee;
import com.github.tangyi.webcast.utils.FileUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.account.LiveAccountSwitchRequest;
import net.polyv.live.v1.entity.account.LiveAccountSwitchResponse;
import net.polyv.live.v1.entity.account.LiveUpdateAccountSwitchRequest;
import net.polyv.live.v1.entity.player.*;
import net.polyv.live.v1.entity.web.info.LiveChannelSplashRequest;
import net.polyv.live.v1.entity.web.info.LiveChannelSplashResponse;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelSplashRequest;
import net.polyv.live.v1.entity.web.menu.LiveListChannelMenuRequest;
import net.polyv.live.v1.entity.web.menu.LiveListChannelMenuResponse;
import net.polyv.live.v1.entity.web.menu.LiveUpdateChannelMenuRequest;
import net.polyv.live.v1.entity.web.setting.LiveChannelGlobalSwitchRequest;
import net.polyv.live.v1.service.account.impl.LiveAccountServiceImpl;
import net.polyv.live.v1.service.player.impl.LivePlayerServiceImpl;
import net.polyv.live.v1.service.web.impl.LiveWebInfoServiceImpl;
import net.polyv.live.v1.service.web.impl.LiveWebMenuServiceImpl;
import net.polyv.live.v1.service.web.impl.LiveWebSettingServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 设置管理Service
 *
 * @author Xiang Longfei
 * @date 2021/01/13
 */
@Slf4j
@AllArgsConstructor
@Service
public class ChannelSettingService {

    @Autowired
    private ChannelMarqueeService channelMarqueeService;

    /**
     * 接口用于获取开关设置，可获取全局开关设置或频道开关设置
     */
    public LiveAccountSwitchResponse getAccountSwitch(String id) {
        LiveAccountSwitchRequest liveAccountSwitchRequest = new LiveAccountSwitchRequest();
        LiveAccountSwitchResponse liveAccountSwitchResponse;
        try {
            if (id != null && id.length() == 0) {
                id = null;
            }
            liveAccountSwitchRequest.setChannelId(id).setRequestId(LiveSignUtil.generateUUID());
            liveAccountSwitchResponse = new LiveAccountServiceImpl().getAccountSwitch(liveAccountSwitchRequest);
            if (liveAccountSwitchResponse != null) {
                //to do something ......
                log.debug("查询功能开关状态接口成功,{}", JSON.toJSONString(liveAccountSwitchResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveAccountSwitchResponse;
    }

    public Boolean updateAccountSwitch(LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest) {
        Boolean liveUpdateAccountSwitchResponse;
        try {
            liveUpdateAccountSwitchRequest.setRequestId(LiveSignUtil.generateUUID());
            liveUpdateAccountSwitchResponse = new LiveAccountServiceImpl().updateAccountSwitch(
                    liveUpdateAccountSwitchRequest);
            if (liveUpdateAccountSwitchResponse) {
                //to do something ......
                log.debug("设置功能开关状态成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveUpdateAccountSwitchResponse;
    }

    public Boolean setPlayerLogo(String id, LiveSetPlayerLogoRequest liveSetPlayerLogoRequest) {
        Boolean result = null;
        try {
            liveSetPlayerLogoRequest.setChannelId(id)
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerLogo(liveSetPlayerLogoRequest);
            if (result != null) {
                //to do something ......
                log.debug("设置播放器Logo成功{}", JSON.toJSONString(result));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return result;
    }

    public Boolean setPlayerHeaderAdvert(LiveSetPlayerHeaderAdvertRequest liveSetPlayerHeaderAdvertRequest) {
        Boolean result = null;
        try {
            liveSetPlayerHeaderAdvertRequest.setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerHeaderAdvert(liveSetPlayerHeaderAdvertRequest);
            if (result != null) {
                //to do something ......
                log.debug("设置播放器片头广告成功{}", JSON.toJSONString(result));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return result;
    }

    public Boolean setPlayerPauseAdvert(LiveSetPlayerPauseAdvertRequest liveSetPlayerPauseAdvertRequest) {
        Boolean result = null;
        try {
            liveSetPlayerPauseAdvertRequest.setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerPauseAdvert(liveSetPlayerPauseAdvertRequest);
            if (result != null) {
                //to do something ......
                log.debug("设置播放器暂停广告成功{}", JSON.toJSONString(result));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return result;
    }

    public Boolean setPlayerUrlMarquee(LiveSetPlayerUrlMarqueeRequest liveSetPlayerUrlMarqueeRequest) {
        Boolean liveSetPlayerUrlMarqueeResponse;
        try {
            liveSetPlayerUrlMarqueeRequest.setRequestId(LiveSignUtil.generateUUID());
            liveSetPlayerUrlMarqueeResponse = new LivePlayerServiceImpl().setPlayerUrlMarquee(
                    liveSetPlayerUrlMarqueeRequest);
            if (liveSetPlayerUrlMarqueeResponse) {
                //to do something ......
                log.debug("设置播放器自定义url跑马灯成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveSetPlayerUrlMarqueeResponse;
    }

    public Boolean setChannelGlobalSwitch(LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest) {
        Boolean liveChannelGlobalSwitchResponse;
        try {
            liveChannelGlobalSwitchRequest.setRequestId(LiveSignUtil.generateUUID());
            liveChannelGlobalSwitchResponse = new LiveWebSettingServiceImpl().setChannelGlobalSwitch(
                    liveChannelGlobalSwitchRequest);
            if (liveChannelGlobalSwitchResponse) {
                //to do something ......
                log.debug("设置频道默认项开关成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelGlobalSwitchResponse;
    }

    public LiveChannelSplashResponse getChannelSplash(String id) {
        LiveChannelSplashRequest liveChannelSplashRequest = new LiveChannelSplashRequest();
        LiveChannelSplashResponse liveChannelSplashResponse;
        try {
            liveChannelSplashRequest.setChannelId(id).setRequestId(LiveSignUtil.generateUUID());
            liveChannelSplashResponse = new LiveWebInfoServiceImpl().getChannelSplash(liveChannelSplashRequest);
            if (liveChannelSplashResponse != null) {
                //to do something ......
                log.debug("查询直播引导图开关状态及URL成功,{}", JSON.toJSONString(liveChannelSplashResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelSplashResponse;
    }

    public String updateChannelSplash(String id, String splashEnabled, MultipartFile file) {
        LiveUpdateChannelSplashRequest liveUpdateChannelSplashRequest = new LiveUpdateChannelSplashRequest();
        String liveUpdateChannelSplashResponse;
        File imgfile = FileUtil.multipartFileToFile(file);
        try {
            liveUpdateChannelSplashRequest.setChannelId(id)
                    .setSplashEnabled(splashEnabled)
                    .setImgfile(imgfile)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelSplashResponse = new LiveWebInfoServiceImpl().updateChannelSplash(
                    liveUpdateChannelSplashRequest);
            if (liveUpdateChannelSplashResponse != null) {
                log.debug("设置引导开关以及引导图片成功,{}", liveUpdateChannelSplashResponse);
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        } finally {
            // 把暂存的文件删除
            imgfile.delete();
        }
        return liveUpdateChannelSplashResponse;
    }

    public Boolean updateWarmUpEnable(LiveSetWarmupEnableRequest liveSetWarmupEnableRequest) {
        Boolean result = null;
        try {
            liveSetWarmupEnableRequest.setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerWarmupEnable(liveSetWarmupEnableRequest);
            if (result != null) {
                //to do something ......
                log.debug("设置频道的暖场设置开关成功{}", JSON.toJSONString(result));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return result;
    }

    public Boolean setPlayerImg(LiveSetPlayerImgRequest liveSetPlayerImgRequest) {
        Boolean result = null;
        try {
            liveSetPlayerImgRequest.setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerImg(liveSetPlayerImgRequest);
            if (result != null) {
                log.debug("设置播放器暖场图片成功 ");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return result;
    }

    public Boolean setPlayerVideo(LiveSetWarmupVedioRequest liveSetWarmupVedioRequest) {
        Boolean result = null;
        try {
            liveSetWarmupVedioRequest.setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
            if (result != null) {
                //to do something ......
                log.debug("设置播放器暖场视频成功{}", JSON.toJSONString(result));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return result;
    }

    public Boolean setLiveIntro(LiveUpdateChannelMenuRequest liveUpdateChannelMenuRequest) {
        Boolean liveUpdateChannelMenuResponse;
        try {
            liveUpdateChannelMenuRequest.setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMenuResponse = new LiveWebMenuServiceImpl().updateChannelMenu(
                    liveUpdateChannelMenuRequest);
            if (liveUpdateChannelMenuResponse) {
                //to do something ......
                log.debug("设置自定义菜单直播介绍成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveUpdateChannelMenuResponse;
    }

    public LiveListChannelMenuResponse listChannelMenu(String id) {
        LiveListChannelMenuRequest liveListChannelMenuRequest = new LiveListChannelMenuRequest();
        LiveListChannelMenuResponse liveListChannelMenuResponse;
        try {
            liveListChannelMenuRequest.setChannelId(id).setRequestId(LiveSignUtil.generateUUID());
            liveListChannelMenuResponse = new LiveWebMenuServiceImpl().listChannelMenu(liveListChannelMenuRequest);
            if (liveListChannelMenuResponse != null) {
                //to do something ......
                log.debug("查询频道的菜单信息成功,{}", JSON.toJSONString(liveListChannelMenuResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveListChannelMenuResponse;
    }

    public Boolean setPlayerUrlMarqueeParameter(MarqueeParameterRequest marqueeParameterRequest) {
        ChannelMarquee entity = new ChannelMarquee();
        entity.setChannelId(marqueeParameterRequest.getChannelId());
        entity.setMarquee(marqueeParameterRequest.getMarquee());
        ChannelMarquee channelMarquee = channelMarqueeService.get(entity);
        if (channelMarquee == null) {
            entity.setNewRecord(true);
        } else {
            entity.setId(channelMarquee.getId());
        }
        return channelMarqueeService.save(entity) > 0 ? true : false;
    }
}
