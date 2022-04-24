package com.github.tangyi.webcast.controller;


import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.webcast.api.dto.req.MarqueeParameterRequest;
import com.github.tangyi.webcast.service.ChannelSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.entity.account.LiveAccountSwitchResponse;
import net.polyv.live.v1.entity.account.LiveUpdateAccountSwitchRequest;
import net.polyv.live.v1.entity.player.*;
import net.polyv.live.v1.entity.web.info.LiveChannelSplashResponse;
import net.polyv.live.v1.entity.web.menu.LiveListChannelMenuResponse;
import net.polyv.live.v1.entity.web.menu.LiveUpdateChannelMenuRequest;
import net.polyv.live.v1.entity.web.setting.LiveChannelGlobalSwitchRequest;
import org.apache.ibatis.annotations.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 设置管理
 *
 * @author Xiang Longfei
 * @date 2021/01/13
 */
@Slf4j
@Api("设置管理")
@RestController
@RequestMapping(value = "/v1/webcast/setting")
public class ChannelSettingController extends BaseController {

    @Autowired
    private ChannelSettingService channelSettingService;

    @ApiOperation(value = "接口用于获取开关设置，可获取全局开关设置或频道开关设置", notes = "接口用于获取开关设置，可获取全局开关设置或频道开关设置")
    @GetMapping("accountSwitch")
    @Log("接口用于获取开关设置，可获取全局开关设置或频道开关设置")
    public ResponseBean<LiveAccountSwitchResponse> getAccountSwitch(@RequestParam(value = "id", required = false) String id) {
        return new ResponseBean<>(channelSettingService.getAccountSwitch(id));
    }

    @ApiOperation(value = "设置功能开关状态", notes = "设置功能开关状态")
    @PutMapping("accountSwitch")
    @Log("设置功能开关状态")
    public ResponseBean<Boolean> updateAccountSwitch(
            @ApiParam(name = "channelSwitches", value = "是否开启：Y代表开启，N代表关闭", required = true) @RequestBody LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.updateAccountSwitch(liveUpdateAccountSwitchRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "设置播放器Logo", notes = "设置播放器Logo")
    @PutMapping("setPlayerLogo/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    @Log("设置播放器Logo")
    public ResponseBean<Boolean> setPlayerLogo(
            @PathVariable(value = "id") String id,
            @ApiParam(name = "liveSetPlayerLogoRequest", value = "设置播放器Logo请求对象") @RequestBody LiveSetPlayerLogoRequest liveSetPlayerLogoRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.setPlayerLogo(id, liveSetPlayerLogoRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "设置某频道播放器的片头广告", notes = "设置某频道播放器的片头广告")
    @PutMapping("setPlayerHeaderAdvert")
    @Log("设置某频道播放器的片头广告")
    public ResponseBean<Boolean> setPlayerHeaderAdvert(
            @ApiParam(name = "headerAdvert", value = "播放器片头广告请求对象", required = true) @RequestBody LiveSetPlayerHeaderAdvertRequest liveSetPlayerHeaderAdvertRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.setPlayerHeaderAdvert(liveSetPlayerHeaderAdvertRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "设置某频道播放器的暂停广告", notes = "设置某频道播放器的暂停广告")
    @PutMapping("setPlayerPauseAdvert")
    @Log("设置某频道播放器的暂停广告")
    public ResponseBean<Boolean> setPlayerPauseAdvert(
            @ApiParam(name = "pauseAdvert", value = "播放器暂停广告请求对象", required = true) @RequestBody LiveSetPlayerPauseAdvertRequest liveSetPlayerPauseAdvertRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.setPlayerPauseAdvert(liveSetPlayerPauseAdvertRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "设置播放器自定义url跑马灯", notes = "可以设置播放器防录屏自定义url跑马灯开关，在开启时需提交url参数。")
    @PutMapping("setPlayerUrlMarquee")
    @Log("设置播放器自定义url跑马灯")
    public ResponseBean<Boolean> setPlayerUrlMarquee(
            @ApiParam(name = "urlMarquee", value = "播放器自定义url跑马灯请求对象", required = true) @RequestBody LiveSetPlayerUrlMarqueeRequest liveSetPlayerUrlMarqueeRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.setPlayerUrlMarquee(liveSetPlayerUrlMarqueeRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "设置频道默认项开关", notes = "设置频道默认项开关")
    @PutMapping("setChannelGlobalSwitch")
    @Log("设置频道默认项开关")
    public ResponseBean<Boolean> setChannelGlobalSwitch(
            @ApiParam(name = "globalSwitch", value = "设置频道默认项开关请求", required = true) @RequestBody LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.setChannelGlobalSwitch(liveChannelGlobalSwitchRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "查询直播引导图开关状态及URL", notes = "查询直播引导图开关状态及URL")
    @GetMapping("channelSplash/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    @Log("查询直播引导图开关状态及URL")
    public ResponseBean<LiveChannelSplashResponse> getChannelSplash(@PathVariable(value = "id") String id) {
        return new ResponseBean<>(channelSettingService.getChannelSplash(id));
    }

    @ApiOperation(value = "设置引导开关以及引导图片", notes = "设置引导开关以及引导图片")
    @PutMapping("channelSplash/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    @Log("设置引导开关以及引导图片")
    public ResponseBean<String> updateChannelSplash(
            @PathVariable(value = "id") String id,
            @ApiParam(name = "splashEnabled", value = "设置开启或关闭引导页Y或N", required = true) @RequestParam(value = "splashEnabled") String splashEnabled,
            @RequestParam MultipartFile file) {
        return new ResponseBean<>(channelSettingService.updateChannelSplash(id, splashEnabled, file));
    }

    @ApiOperation(value = "设置频道的暖场开关", notes = "设置频道的暖场开关")
    @PutMapping("warmUpEnabled/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    @Log("设置频道的暖场开关")
    public ResponseBean<Boolean> updateWarmUpEnable(
            @ApiParam(name = "liveSetWarmupEnableRequest", value = "频道的暖场设置开关请求对象", required = true) @RequestBody LiveSetWarmupEnableRequest liveSetWarmupEnableRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.updateWarmUpEnable(liveSetWarmupEnableRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "设置播放器暖场图片", notes = "设置播放器暖场图片")
    @PutMapping("setPlayerImg")
    @Log("设置播放器暖场图片")
    public ResponseBean<Boolean> setPlayerImg(
            @ApiParam(name = "liveSetPlayerImgRequest", value = "播放器暖场图片请求对象", required = true) @RequestBody LiveSetPlayerImgRequest liveSetPlayerImgRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.setPlayerImg(liveSetPlayerImgRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "设置播放器暖场视频", notes = "设置播放器暖场视频")
    @PutMapping("setPlayerVideo")
    @Log("设置播放器暖场视频")
    public ResponseBean<Boolean> setPlayerVideo(
            @ApiParam(name = "liveSetWarmupVedioRequest", value = "设置播放器暖场视频请求对象", required = true) @RequestBody LiveSetWarmupVedioRequest liveSetWarmupVedioRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.setPlayerVideo(liveSetWarmupVedioRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "设置直播介绍", notes = "设置直播介绍")
    @PutMapping("setLiveIntro")
    @Log("设置直播介绍")
    public ResponseBean<Boolean> setLiveIntro(
            @ApiParam(name = "liveUpdateChannelMenuRequest", value = "设置直播介绍请求对象", required = true) @RequestBody LiveUpdateChannelMenuRequest liveUpdateChannelMenuRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.setLiveIntro(liveUpdateChannelMenuRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "获取频道的菜单信息，可查直播介绍", notes = "获取频道的菜单信息，可查直播介绍")
    @GetMapping("listChannelMenu")
    @Log("获取频道的菜单信息，可查直播介绍")
    public ResponseBean<LiveListChannelMenuResponse> listChannelMenu(@ApiParam(name = "id", value = "频道号，不传为获取全局设置", required = false) @RequestParam(value = "id", required = false) String id) {
        return new ResponseBean<>(channelSettingService.listChannelMenu(id));
    }

    @ApiOperation(value = "设置播放器自定义url跑马灯参数", notes = "设置播放器自定义url跑马灯参数")
    @PutMapping("setPlayerUrlMarqueeParameter")
    @Log("设置播放器自定义url跑马灯参数")
    public ResponseBean<Boolean> setPlayerUrlMarqueeParameter(
            @ApiParam(name = "marqueeParameterRequest", value = "播放器自定义url跑马灯参数请求对象", required = true) @RequestBody MarqueeParameterRequest marqueeParameterRequest) {
        if (!Boolean.TRUE.equals(channelSettingService.setPlayerUrlMarqueeParameter(marqueeParameterRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

}
