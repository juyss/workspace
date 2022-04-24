package com.github.tangyi.webcast.controller;


import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.webcast.service.ChannelPptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.entity.channel.operate.LiveCreateChannelPPTRecordRequest;
import net.polyv.live.v1.entity.channel.operate.LiveListChannelPPTRecordRequest;
import net.polyv.live.v1.entity.channel.operate.LiveListChannelPPTRecordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 重制课件
 *
 * @author Xiang Longfei
 * @date 2021/01/22
 */
@Slf4j
@Api("重制课件")
@RestController
@RequestMapping(value = "/v1/webcast/ppt")
public class ChannelPptRecordController extends BaseController {

    @Autowired
    private ChannelPptService channelPptService;

    @ApiOperation(value = "创建重制课件任务", notes = "创建重制课件任务")
    @PostMapping("record")
    @Log("创建重制课件任务")
    public ResponseBean<Boolean> createChannelPPTRecord(
            @ApiParam(name = "liveCreateChannelPPTRecordRequest", value = "创建重制课件任务请求对象", required = true) @RequestBody LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest) {
        if (!Boolean.TRUE.equals(channelPptService.createChannelPPTRecord(liveCreateChannelPPTRecordRequest))) {
            throw new CommonException("创建失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "查询课件重制任务列表", notes = "查询课件重制任务列表")
    @PostMapping("recordList")
    @Log("查询课件重制任务列表")
    public ResponseBean<LiveListChannelPPTRecordResponse> listPPTRecord(
            @ApiParam(name = "liveListChannelPPTRecordRequest", value = "查询课件重制任务列表请求对象", required = true) @RequestBody LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest) {
        return new ResponseBean<>(channelPptService.listPPTRecord(liveListChannelPPTRecordRequest));
    }

}
