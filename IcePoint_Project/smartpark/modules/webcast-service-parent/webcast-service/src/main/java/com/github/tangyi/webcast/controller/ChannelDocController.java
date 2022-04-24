package com.github.tangyi.webcast.controller;


import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.webcast.service.ChannelDocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.entity.channel.doc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文档管理
 *
 * @author Xiang Longfei
 * @date 2021/01/20
 */
@Slf4j
@Api("文档管理")
@RestController
@RequestMapping(value = "/v1/webcast/doc")
public class ChannelDocController extends BaseController {

    @Autowired
    private ChannelDocService channelDocService;

    @Log("上传频道文档")
    @ApiOperation(value = "上传频道文档", notes = "上传频道文档")
    @PostMapping("channelDoc")
    public ResponseBean<LiveCreateChannelDocResponse> createChannelDoc(
            @RequestParam MultipartFile docFile,
            @ApiParam(name = "liveCreateChannelDocRequest", value = "上传频道文档请求对象", required = true) LiveCreateChannelDocRequest liveCreateChannelDocRequest) {
        return new ResponseBean<>(channelDocService.createChannelDoc(docFile, liveCreateChannelDocRequest));
    }

    @Log("查询频道文档转换状态")
    @ApiOperation(value = "查询频道文档转换状态", notes = "查询频道文档转换状态")
    @GetMapping("channelDocStatus")
    public ResponseBean<LiveChannelDocStatusResponse> getChannelDocStatus(
            @ApiParam(name = "liveChannelDocStatusRequest", value = "查询频道文档转换状态请求对象", required = true) @RequestBody LiveChannelDocStatusRequest liveChannelDocStatusRequest) {
        return new ResponseBean<>(channelDocService.getChannelDocStatus(liveChannelDocStatusRequest));
    }

    @Log("获取频道文档列表")
    @ApiOperation(value = "获取频道文档列表", notes = "获取频道文档列表")
    @PostMapping("channelDocList")
    public ResponseBean<LiveListChannelDocResponse> listChannelDoc(
            @ApiParam(name = "liveListChannelDocRequest", value = "获取频道文档列表请求对象", required = true) @RequestBody LiveListChannelDocRequest liveListChannelDocRequest) {
        return new ResponseBean<>(channelDocService.listChannelDoc(liveListChannelDocRequest));
    }

    @Log("删除频道文档")
    @ApiOperation(value = "删除频道文档", notes = "删除频道文档")
    @DeleteMapping("channelDoc")
    public ResponseBean<Boolean> deleteChannelDoc(
            @ApiParam(name = "liveDeleteChannelDocRequest", value = "删除频道文档请求对象", required = true) @RequestBody LiveDeleteChannelDocRequest liveDeleteChannelDocRequest) {
        if (!Boolean.TRUE.equals(channelDocService.deleteChannelDoc(liveDeleteChannelDocRequest))) {
            throw new CommonException("删除失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }


}
