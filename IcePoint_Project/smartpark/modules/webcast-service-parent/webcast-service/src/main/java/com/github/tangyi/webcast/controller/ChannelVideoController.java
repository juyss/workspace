package com.github.tangyi.webcast.controller;


import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.webcast.api.dto.req.LiveClipChannelVideoAsyncRequest;
import com.github.tangyi.webcast.api.dto.resp.LiveListChannelVideoLibraryWithPlaybackUrlResponse;
import com.github.tangyi.webcast.api.model.ChannelVideo;
import com.github.tangyi.webcast.service.ChannelVideoService;
import com.github.tangyi.webcast.service.PolyvApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.entity.channel.playback.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回放管理
 *
 * @author Xiang Longfei
 * @date 2021/01/12
 */
@Slf4j
@Api("回放管理")
@RestController
@RequestMapping(value = "/v1/webcast/channelVideo")
public class ChannelVideoController extends BaseController {

    @Autowired
    private ChannelVideoService channelVideoService;

    @Autowired
    private PolyvApiService polyvApiService;

    @ApiOperation(value = "查询频道的回放开关状态", notes = "查询频道的回放开关状态")
    @GetMapping("playBackEnabled/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    @Log("查询频道的回放开关状态")
    public ResponseBean<String> getChannelPlayBack(@PathVariable(value = "id") String id) {
        return new ResponseBean<>(channelVideoService.getChannelPlayBack(id));
    }

    @ApiOperation(value = "回放设置", notes = "回放设置")
    @PutMapping("playBackEnabled")
    @Log("回放设置")
    public ResponseBean<Boolean> updateChannelPlayBack(
            @ApiParam(name = "liveChannelPlaybackEnabledRequest", value = "回放开关请求对象", required = true) @RequestBody LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest) {
        if (!Boolean.TRUE.equals(channelVideoService.updateChannelPlayBack(liveChannelPlaybackEnabledRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "设置频道回放设置", notes = "设置频道回放设置")
    @PutMapping("channelPlaybackSetting")
    @Log("设置频道回放设置")
    public ResponseBean<Boolean> updateChannelPlaybackSetting(
            @ApiParam(name = "liveChannelPlaybackSettingRequest", value = "设置频道回放设置请求对象", required = true) @RequestBody LiveChannelPlaybackSettingRequest liveChannelPlaybackSettingRequest) {
        if (!Boolean.TRUE.equals(channelVideoService.updateChannelPlaybackSetting(liveChannelPlaybackSettingRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "查询视频库-回放列表", notes = "查询视频库-回放列表")
    @GetMapping("library/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    @Log("查询视频库-回放列表")
    public ResponseBean<LiveListChannelVideoLibraryWithPlaybackUrlResponse> listChannelVideoLibrary(
            @PathVariable(value = "id") String id,
            @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
            @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
            @ApiParam(name = "listType", value = "playback-回放列表,vod-点播列表;默认普通直播场景为vod，三分屏为playback", required = false) @RequestParam(value = "listType", required = false) String listType) {
        return new ResponseBean<>(channelVideoService.listChannelVideoLibrary(id, pageNum, pageSize, listType));
    }

    @ApiOperation(value = "设置视频库-回放列表排序", notes = "设置视频库-回放列表排序")
    @PutMapping("channelVideoSort")
    @Log("设置视频库-回放列表排序")
    public ResponseBean<Boolean> channelVideoSort(
            @ApiParam(name = "liveChannelVideoSortRequest", value = "设置视频库-回放列表排序请求实体对象", required = true) @RequestBody LiveChannelVideoSortRequest liveChannelVideoSortRequest) {
        if (!Boolean.TRUE.equals(channelVideoService.channelVideoSort(liveChannelVideoSortRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "删除视频库-回放列表中的视频", notes = "删除视频库-回放列表中的视频")
    @DeleteMapping("channelPlaybackVideo")
    @Log("删除视频库-回放列表中的视频")
    public ResponseBean<Boolean> deleteChannelPlaybackVideo(
            @ApiParam(name = "channelId", value = "频道ID", required = true) @RequestParam String channelId,
            @ApiParam(name = "videoId", value = "直播系统生成的id，可在回放列表接口的返回数据获取", required = true) @RequestParam String videoId,
            @ApiParam(name = "listType", value = "playback-回放列表，vod-点播列表", required = false) @RequestParam(required = false) String listType) {
        LiveDeleteChannelPlaybackVideoRequest liveDeleteChannelPlaybackVideoRequest = new LiveDeleteChannelPlaybackVideoRequest().setChannelId(channelId).setVideoId(videoId).setListType(listType);
        if (!Boolean.TRUE.equals(channelVideoService.deleteChannelPlaybackVideo(liveDeleteChannelPlaybackVideoRequest))) {
            throw new CommonException("删除失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "查询视频库-直播暂存", notes = "查询视频库-直播暂存")
    @GetMapping("channelVideo/{id}")
    @ApiImplicitParam(name = "id", value = "频道ID", required = true, paramType = "path")
    @Log("查询视频库-直播暂存")
    public PageInfo<LiveChannelVideoListResponse.ChannelVedioInfo> listChannelVideo(
            @PathVariable(value = "id") String id,
            @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
            @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize) {
        return channelVideoService.listChannelVideo(id, pageNum, pageSize);
    }

    @ApiOperation(value = "将点播中的视频添加到视频库", notes = "将点播中的视频添加到视频库")
    @PostMapping("library")
    @Log("将点播中的视频添加到视频库")
    public ResponseBean<LiveCreateChannelVideoPlaybackResponse> createChannelVideoPlayback(
            @ApiParam(name = "liveCreateChannelVideoPlaybackRequest", value = "将点播中的视频添加到视频库请求对象", required = true) @RequestBody LiveCreateChannelVideoPlaybackRequest liveCreateChannelVideoPlaybackRequest) {
        return new ResponseBean<>(channelVideoService.createChannelVideoPlayback(liveCreateChannelVideoPlaybackRequest));
    }

    @ApiOperation(value = "异步转存直播暂存到点播列表", notes = "异步转存直播暂存到点播列表")
    @PostMapping("convert")
    @Log("异步转存直播暂存到点播列表")
    public ResponseBean<Boolean> convertChannelVideoListAsync(
            @ApiParam(name = "liveConvertChannelVideoListAsyncRequest", value = "异步转存直播暂存到点播列表请求实体", required = true) @RequestBody LiveConvertChannelVideoListAsyncRequest liveConvertChannelVideoListAsyncRequest) {
        return new ResponseBean<>(channelVideoService.convertChannelVideoListAsync(liveConvertChannelVideoListAsyncRequest));
    }

    @ApiOperation(value = "异步合并直播暂存", notes = "异步合并直播暂存")
    @PostMapping("merge")
    @Log("异步合并直播暂存")
    public ResponseBean<Boolean> mergeChannelVideoAsync(
            @ApiParam(name = "liveConvertChannelVideoListAsyncRequest", value = "异步合并直播暂存请求实体", required = true) @RequestBody LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest) {
        if (!Boolean.TRUE.equals(polyvApiService.mergeChannelVideoAsync(liveMergeChannelVideoAsyncRequest))) {
            throw new CommonException("合并失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "异步裁剪直播录制文件", notes = "异步裁剪直播录制文件")
    @PostMapping("clip")
    @Log("异步裁剪直播录制文件")
    public ResponseBean<Boolean> clipChannelVideoAsync(
            @ApiParam(name = "liveClipChannelVideoAsyncRequest", value = "异步裁剪直播录制文件请求实体", required = true) @RequestBody LiveClipChannelVideoAsyncRequest liveClipChannelVideoAsyncRequest) {
        if (!Boolean.TRUE.equals(polyvApiService.clipChannelVideoAsync(liveClipChannelVideoAsyncRequest))) {
            throw new CommonException("裁剪失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "修改回放视频名称", notes = "修改回放视频名称")
    @PostMapping("playbackTitle")
    @Log("查询频道的回放开关状态")
    public ResponseBean<Boolean> updatePlaybackTitle(
            @ApiParam(name = "liveUpdatePlaybackTitleRequest", value = "修改回放视频名称请求实体", required = true) @RequestBody LiveUpdatePlaybackTitleRequest liveUpdatePlaybackTitleRequest) {
        if (!Boolean.TRUE.equals(channelVideoService.updatePlaybackTitle(liveUpdatePlaybackTitleRequest))) {
            throw new CommonException("修改失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "删除视频库-直播暂存中的视频", notes = "删除视频库-直播暂存中的视频")
    @DeleteMapping("channelVideo")
    @Log("删除视频库-直播暂存中的视频")
    public ResponseBean<Boolean> deleteChannelVideo(
            @ApiParam(name = "channelId", value = "频道ID", required = true) @RequestParam String channelId,
            @ApiParam(name = "sessionId", value = "录制视频的场次ID", required = false) @RequestParam(required = false) String sessionId,
            @ApiParam(name = "startTime", value = "录制视频的开始录制时间", required = false) @RequestParam(required = false) String startTime) {
        if (!Boolean.TRUE.equals(channelVideoService.deleteChannelVideo(channelId, sessionId, startTime))) {
            throw new CommonException("删除失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "查询本地视频库-回放列表", notes = "查询本地视频库-回放列表")
    @GetMapping("localVideo")
    @Log("查询本地视频库-回放列表")
    public PageInfo<ChannelVideo> localVideo(
            @ApiParam(name = "channelId", value = "频道ID", required = false) @RequestParam(value = "channelId") String channelId,
            @ApiParam(name = "pageNum", value = "页码", required = false) @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
            @ApiParam(name = "pageSize", value = "页大小", required = false) @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize) {
        return channelVideoService.localVideo(channelId, pageNum, pageSize);
    }

    @ApiOperation(value = "查询本地视频库-回放列表", notes = "查询本地视频库-回放列表")
    @GetMapping("getObsUrl")
    @Log("查询本地视频库-回放列表")
    public ResponseBean<String> getLocalVidoByCode(
            @ApiParam(name = "type", value = "验证类型", required = false) @RequestParam(value = "type") String type,
            @ApiParam(name = "code", value = "验证码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(name = "id", value = "本地视频id", required = false) @RequestParam(value = "id", required = false) Long id) {
        return channelVideoService.getObsUrl(type, code, id);
    }

    @ApiOperation(value = "删除本地视频库-回放列表中的视频", notes = "删除本地视频库-回放列表中的视频")
    @DeleteMapping("localVideo")
    @Log("删除本地视频库-回放列表中的视频")
    public ResponseBean<Boolean> deleteLocalVideo(@ApiParam(name = "ids", value = "ID集合", required = true) Long[] ids) {
        if (!Boolean.TRUE.equals(channelVideoService.deleteLocalVideo(ids))) {
            throw new CommonException("删除失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }


    @ApiOperation(value = "查询本地视频库-同场次-回放列表", notes = "查询本地视频库-同场次-回放列表")
    @GetMapping("localVideoSession")
    @Log("查询本地视频库-同场次-回放列表")
    public List<ChannelVideo> localVideoSession(
            @ApiParam(name = "channelId", value = "频道ID", required = true) @RequestParam(value = "channelId") String channelId,
            @ApiParam(name = "channelSessionId", value = "场次ID", required = true) @RequestParam(value = "channelSessionId") String channelSessionId
    ) {
        return channelVideoService.localVideoSession(channelId, channelSessionId);
    }

}
