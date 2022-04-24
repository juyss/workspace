package com.github.tangyi.webcast.controller;


import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.webcast.service.ChannelAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.entity.web.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 观看条件
 *
 * @author Xiang Longfei
 * @date 2021/01/21
 */
@Slf4j
@Api("观看条件")
@RestController
@RequestMapping(value = "/v1/webcast/auth")
public class ChannelAuthController extends BaseController {

    @Autowired
    private ChannelAuthService channelAuthService;

    @Log("查询直播频道观看条件")
    @ApiOperation(value = "查询直播频道观看条件", notes = "查询直播频道观看条件")
    @GetMapping("channelAuth")
    public ResponseBean<LiveChannelAuthResponse> getChannelAuth(
            @ApiParam(name = "channelId", value = "频道ID", required = false) @RequestParam(required = false) String channelId) {
        LiveChannelAuthRequest liveChannelAuthRequest = new LiveChannelAuthRequest().setChannelId(channelId);
        return new ResponseBean<>(channelAuthService.getChannelAuth(liveChannelAuthRequest));
    }

    @Log("设置观看条件")
    @ApiOperation(value = "设置观看条件", notes = "设置观看条件")
    @PutMapping("channelAuth")
    public ResponseBean<Boolean> updateChannelAuth(
            @ApiParam(name = "liveUpdateChannelAuthRequest", value = "设置观看条件请求对象", required = true) @RequestBody LiveUpdateChannelAuthRequest liveUpdateChannelAuthRequest) {
        if (!Boolean.TRUE.equals(channelAuthService.updateChannelAuth(liveUpdateChannelAuthRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }


    @Log("添加单个白名单")
    @ApiOperation(value = "添加单个白名单", notes = "添加单个白名单")
    @PostMapping("channelWhite")
    public ResponseBean<Boolean> createChannelWhiteList(
            @ApiParam(name = "liveCreateChannelWhiteListRequest", value = "添加单个白名单请求对象", required = true) LiveCreateChannelWhiteListRequest liveCreateChannelWhiteListRequest) {
        if (!Boolean.TRUE.equals(channelAuthService.createChannelWhiteList(liveCreateChannelWhiteListRequest))) {
            throw new CommonException("添加失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @Log("查询频道观看白名单列表")
    @ApiOperation(value = "查询频道观看白名单列表", notes = "查询频道观看白名单列表")
    @PostMapping("channelWhiteList")
    public ResponseBean<LiveChannelWhiteListResponse> getChannelWhiteList(
            @ApiParam(name = "liveChannelWhiteListRequest", value = "查询频道观看白名单列表请求对象", required = true) @RequestBody LiveChannelWhiteListRequest liveChannelWhiteListRequest) {
        return new ResponseBean<>(channelAuthService.getChannelWhiteList(liveChannelWhiteListRequest));
    }

    @Log("更新白名单")
    @ApiOperation(value = "更新白名单", notes = "更新白名单")
    @PutMapping("channelWhiteList")
    public ResponseBean<Boolean> updateChannelWhiteList(
            @ApiParam(name = "liveUpdateChannelWhiteListRequest", value = "更新白名单请求对象", required = true) @RequestBody LiveUpdateChannelWhiteListRequest liveUpdateChannelWhiteListRequest) {
        if (!Boolean.TRUE.equals(channelAuthService.updateChannelWhiteList(liveUpdateChannelWhiteListRequest))) {
            throw new CommonException("更新失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @Log("用于删除指定观看白名单（支持一键清空）")
    @ApiOperation(value = "用于删除指定观看白名单（支持一键清空）", notes = "用于删除指定观看白名单（支持一键清空）")
    @DeleteMapping("channelWhiteList")
    public ResponseBean<Boolean> deleteChannelWhiteList(
            @ApiParam(name = "liveDeleteChannelWhiteListRequest", value = "删除白名单请求对象", required = true) @RequestBody LiveDeleteChannelWhiteListRequest liveDeleteChannelWhiteListRequest) {
        if (!Boolean.TRUE.equals(channelAuthService.deleteChannelWhiteList(liveDeleteChannelWhiteListRequest))) {
            throw new CommonException("删除失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }


}
