package com.github.tangyi.webcast.controller;


import com.github.tangyi.webcast.api.model.ChannelVideo;
import com.github.tangyi.webcast.mapper.ChannelVideoMapper;
import com.github.tangyi.webcast.service.ChannelVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步合并完成后回调
 */
@RestController
@RequestMapping(value = "/v1/AsyncCallBack")
@Slf4j
public class AsyncCallBack {

    @Autowired
    private ChannelVideoService channelVideoService;

    @Autowired
    private ChannelVideoMapper channelVideoMapper;

    @RequestMapping(value = "callBack")
    public void callBack(@RequestParam("status") String status,
                         @RequestParam(value = "fileId") String fileId,
                         @RequestParam(value = "channelId") String channelId,
                         @RequestParam("fileUrl") String fileUrl,
                         @RequestParam("fileName") String fileName) {
        log.info("进入回调");
        //判断如果fileId 存在返回false
        //回调成功后插入本地 webcast_channel_video中
      Boolean b = channelVideoService.callBack(status,fileId,channelId,fileUrl,fileName);


    }





}
