package com.github.tangyi.webcast.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.webcast.api.dto.req.LiveChannelSettingWithMobileRequest;
import com.github.tangyi.webcast.api.dto.req.LiveChannelWithDeptRequest;
import com.github.tangyi.webcast.api.dto.resp.ChannelBasicInfo;
import com.github.tangyi.webcast.api.dto.resp.LiveChannelDetailWithPosterUrl;
import com.github.tangyi.webcast.service.ChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.account.*;
import net.polyv.live.v1.entity.channel.operate.*;
import net.polyv.live.v1.entity.web.interact.LiveGetChannelWxShareResponse;
import net.polyv.live.v1.entity.web.interact.LiveUpdateChannelWxShareRequest;
import net.polyv.live.v1.service.account.impl.LiveAccountServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 在线直播
 *
 * @author gaokx
 * @date 2020/1/5 16:54
 */
@Slf4j
@Api("频道管理")
@RestController
@RequestMapping(value = "/v1/webcast/channel")
public class ChannelController extends BaseController {

    @Autowired
    ChannelService channelService;

    @Log("频道创建")
    @ApiOperation(value = "频道创建", notes = "频道创建")
    @PostMapping("create")
    public ResponseBean<LiveChannelResponse> create(@RequestBody @ApiParam(name = "channelRequest", value = "频道创建请求对象", required = true) LiveChannelWithDeptRequest channelRequest) {

        if (channelRequest == null) {
            throw new CommonException("请输入频道配置信息");
        }
        //设置频道主题信息
        if (StringUtils.isEmpty(channelRequest.getName())) {
            throw new CommonException("频道名称不能为空");
        }
        if (StringUtils.isEmpty(channelRequest.getDeptId())) {
            throw new CommonException("部门不能为空");
        }
        LiveChannelResponse liveChannelResponse = channelService.createChannel(channelRequest);
        return new ResponseBean<>(liveChannelResponse);
    }


    @Log("获取所有频道号列表")
    @ApiOperation(value = "获取所有频道号列表", notes = "频道列表")
    @PostMapping("findNumbers")
    public ResponseBean<LiveListAccountResponse> findChannels(
            @RequestBody @ApiParam(name = "liveListAccountRequest", value = "频道查询对象", required = true) LiveListAccountRequest liveListAccountRequest) {
        LiveListAccountResponse liveListAccountResponse;
        try {
            liveListAccountRequest.setRequestId(LiveSignUtil.generateUUID());
            liveListAccountResponse = new LiveAccountServiceImpl().listAccount(liveListAccountRequest);
            if (liveListAccountResponse != null) {
                //to do something ......
                log.debug("测试查询账号下的频道列表成功,{}", JSON.toJSONString(liveListAccountResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException("参数错误或供应商服务端错误");
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return new ResponseBean<>(liveListAccountResponse);
    }

    @Log("获取所有频道详情列表")
    @ApiOperation(value = "获取所有频道详情列表", notes = "频道详情列表")
    @GetMapping("pageList")
    public PageInfo<LiveChannelDetailWithPosterUrl> pageListAccountDetails(
            @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
            @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
            @RequestParam(value = "deptIdList") List<Long> deptIdList,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "watchStatus", required = false) String watchStatus,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "withoutEnd", required = false) String withoutEnd,
            @RequestParam(value = "withoutPlayback", required = false) String withoutPlayback) {
        LiveListAccountDetailRequest request = new LiveListAccountDetailRequest();
        request.setCategoryId(categoryId)
                .setKeyword(keyword)
                .setWatchStatus(watchStatus)
                .setPageSize(Integer.parseInt(pageSize))
                .setCurrentPage(Integer.parseInt(pageNum));
        return channelService.pageListAccountDetails(request, deptIdList, withoutEnd, withoutPlayback);
    }

    @Log("删除频道")
    @ApiOperation(value = "删除频道", notes = "删除频道")
    @DeleteMapping("{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    public ResponseBean<Boolean> delete(@PathVariable(value = "id") String id) {
        ChannelBasicInfo channelBasicInfo = channelService.getChannelBasicInfo(id);
        if (ObjectUtils.isEmpty(channelBasicInfo)) {
            throw new CommonException("频道不存在");
        }
        if (!Boolean.TRUE.equals(channelService.deleteChannel(id))) {
            throw new CommonException("频道删除失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @Log("查询频道信息")
    @ApiOperation(value = "查询频道信息", notes = "查询频道信息")
    @GetMapping("/channelInfo/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    public ResponseBean<LiveChannelInfoResponse> channelInfo(@PathVariable(value = "id") String id) {
        return new ResponseBean<>(channelService.getChannelInfo(id));
    }

    @Log("查询频道基本信息")
    @ApiOperation(value = "查询频道基本信息", notes = "查询频道基本信息")
    @GetMapping("/basicInfo/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    public ResponseBean<ChannelBasicInfo> basicInfo(@PathVariable(value = "id") String id) {
        return new ResponseBean<>(channelService.getChannelBasicInfo(id));
    }

    @Log("开启直播")
    @ApiOperation(value = "开启直播", notes = "开启直播")
    @PutMapping("/basicInfo/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    public ResponseBean<Boolean> start(@PathVariable(value = "id") String id, @RequestBody @ApiParam(name = "channelSetting", value = "频道相关设置对象", required = true) LiveChannelSettingWithMobileRequest liveChannelSettingRequestBasicSetting) {
        if (!Boolean.TRUE.equals(channelService.start(id, liveChannelSettingRequestBasicSetting))) {
            throw new CommonException("开启失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @Log("基本信息设置")
    @ApiOperation(value = "基本信息设置", notes = "基本信息设置")
    @PostMapping("/basicInfo/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    public ResponseBean<Boolean> updateChannelSetting(@PathVariable(value = "id") String id, @RequestBody @ApiParam(name = "channelSetting", value = "频道相关设置对象", required = true) LiveChannelSettingRequest.BasicSetting liveChannelSettingRequestBasicSetting) {
        if (!Boolean.TRUE.equals(channelService.updateChannelSetting(id, liveChannelSettingRequestBasicSetting))) {
            throw new CommonException("设置成功");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @Log("设置频道图标")
    @ApiOperation(value = "设置频道图标", notes = "设置频道图标")
    @PutMapping("updateChannelLogo/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    public ResponseBean<String> updateChannelLogo(@PathVariable(value = "id") String id, @RequestParam MultipartFile file) {
        return new ResponseBean<>(channelService.updateChannelLogo(id, file));
    }

    @Log("查询账户分钟数")
    @ApiOperation(value = "查询账户分钟数", notes = "查询账户分钟数")
    @GetMapping("userDurations")
    public ResponseBean<LiveAccountUserDurationsResponse> getUserDurations() {
        return new ResponseBean<>(channelService.getUserDurations());
    }

    @Log("设置频道微信分享信息")
    @ApiOperation(value = "设置频道微信分享信息", notes = "设置频道微信分享信息")
    @PutMapping("wxShare")
    public ResponseBean<Boolean> updateChannelWxShare(@ApiParam(name = "liveUpdateChannelWxShareRequest", value = "设置频道微信分享信息请求实体", required = true) @RequestBody LiveUpdateChannelWxShareRequest liveUpdateChannelWxShareRequest) {
        if (!Boolean.TRUE.equals(channelService.updateChannelWxShare(liveUpdateChannelWxShareRequest))) {
            throw new CommonException("设置成功");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @Log("获取频道的微信分享设置信息")
    @ApiOperation(value = "获取频道的微信分享设置信息", notes = "获取频道的微信分享设置信息")
    @GetMapping("wxShare/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    public ResponseBean<LiveGetChannelWxShareResponse> getChannelWxShare(@PathVariable String id) {
        return new ResponseBean<>(channelService.getChannelWxShare(id));
    }

    @Log("查询频道号下所有子频道信息")
    @ApiOperation(value = "查询频道号下所有子频道信息", notes = "查询频道号下所有子频道信息")
    @GetMapping("subChannel/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    public ResponseBean<LiveSonChannelInfoListResponse> getSonChannelInfoList(@PathVariable String id) {
        return new ResponseBean<>(channelService.getSonChannelInfoList(id));
    }

    @Log("设置子频道信息")
    @ApiOperation(value = "设置子频道信息", notes = "设置子频道信息")
    @PutMapping("subChannel")
    public ResponseBean<Boolean> updateSubChannelInfo(@ApiParam(name = "liveUpdateSonChannelInfoRequest", value = "设置子频道信息请求体", required = true) @RequestBody LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest) {
        if (!Boolean.TRUE.equals(channelService.updateSubChannelInfo(liveUpdateSonChannelInfoRequest))) {
            throw new CommonException("设置成功");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @Log("播放器调用的自定义跑马灯参数接口")
    @ApiOperation(value = "播放器调用的自定义跑马灯参数接口", notes = "播放器调用的自定义跑马灯参数接口，用户播放器查询跑马灯参数")
    @GetMapping("urlMarquee")
    public JSON getUrlMarquee(String vid, String uid, String code, String t) {
        return channelService.getUrlMarquee(vid, uid, code, t);
    }

    @Log("获取子频道单点登陆token")
    @ApiOperation(value = "获取子频道单点登陆token", notes = "获取子频道单点登陆token")
    @GetMapping("subChannelToken")
    public String getSubChannelToken(@ApiParam(name = "account", value = "子频道号", required = true) String account) {
        return channelService.getSubChannelToken(account);
    }

    @Log("获取所有正在直播和未开始直播频道")
    @ApiOperation(value = "获取所有正在直播和未开始直播", notes = "获取所有正在直播和未开始直播")
    @GetMapping("channelAsLive")
    public ResponseBean<List<LiveListAccountDetailResponse.LiveChannelDetail>> channelASLive() {
        return new ResponseBean<>(channelService.channelASLive());
    }


}
