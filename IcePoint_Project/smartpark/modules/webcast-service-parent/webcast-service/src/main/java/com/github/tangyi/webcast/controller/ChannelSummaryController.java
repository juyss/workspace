package com.github.tangyi.webcast.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.webcast.api.dto.req.DataReportRequest;
import com.github.tangyi.webcast.api.dto.resp.DataReportEcharts;
import com.github.tangyi.webcast.api.dto.resp.LiveListChannelSummaryWithAverageValueResponse;
import com.github.tangyi.webcast.api.model.DataReport;
import com.github.tangyi.webcast.service.ChannelSummaryService;
import com.github.tangyi.webcast.service.PolyvApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.entity.channel.viewdata.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


/**
 * 统计
 *
 * @author Xiang Longfei
 * @date 2021/01/13
 */
@Slf4j
@Api("统计")
@RestController
@RequestMapping(value = "/v1/webcast/summary")
public class ChannelSummaryController extends BaseController {

    @Autowired
    private ChannelSummaryService channelSummaryService;

    @Autowired
    private PolyvApiService polyvApiService;

    @ApiOperation(value = "查询多个频道汇总的统计数据", notes = "查询多个频道汇总的统计数据")
    @GetMapping("get")
    @Log("查询多个频道汇总的统计数据")
    public ResponseBean<LiveListChannelSummaryWithAverageValueResponse> get(
            @RequestParam(value = "channelIds") List<String> channelIds,
            @RequestParam(value = "startDate", required = false) Date startDate,
            @RequestParam(value = "endDate", required = false) Date endDate) {
        return new ResponseBean<>(channelSummaryService.get(channelIds, startDate, endDate));
    }

    @ApiOperation(value = "分页查询频道观看日志", notes = "分页查询频道观看日志")
    @GetMapping("viewLog")
    @Log("分页查询频道观看日志")
    public ResponseBean<LiveListChannelViewlogResponse> viewLog(
            @ApiParam(name = "liveListChannelViewlogRequest", value = "分页查询频道观看日志请求对象", required = true) LiveListChannelViewlogRequest liveListChannelViewlogRequest) {
        return new ResponseBean<>(channelSummaryService.viewLog(liveListChannelViewlogRequest));
    }

    @ApiOperation(value = "查询实时并发数", notes = "查询实时并发数")
    @GetMapping("viewerConcurrence")
    @Log("查询实时并发数")
    public ResponseBean<LiveListChannelViewerCountResponse> viewerCount(
            @ApiParam(name = "channelIds", value = "频道号", required = true) @RequestParam(value = "channelIds") List<String> channelIds) {
        return new ResponseBean<>(channelSummaryService.viewerCount(channelIds));
    }

    @ApiOperation(value = "查询历史在线人数", notes = "查询历史在线人数")
    @GetMapping("viewerCount")
    @Log("查询历史在线人数")
    public ResponseBean<LiveChannelViewerConcurrenceResponse> viewerConcurrence(
            @ApiParam(name = "liveChannelViewerConcurrenceRequest", value = "频道的历史在线人数请求对象", required = true) LiveChannelViewerConcurrenceRequest liveChannelViewerConcurrenceRequest) {
        return new ResponseBean<>(channelSummaryService.viewerConcurrence(liveChannelViewerConcurrenceRequest));
    }

    @ApiOperation(value = "数据统计-日期汇总", notes = "数据统计-日期汇总")
    @GetMapping("listDailySummary")
    @Log("数据统计-日期汇总")
    public ResponseBean<JSONObject> listDailySummary(
            @ApiParam(name = "channelId", value = "频道ID", required = true) @RequestParam(value = "channelId") String channelId,
            @ApiParam(name = "startDate", value = "yyyy-MM-dd的日期字符串，要查询的数据的开始日期", required = true) @RequestParam(value = "startDate") String startDate,
            @ApiParam(name = "endDate", value = "yyyy-MM-dd的日期字符串，要查询的数据的结束日期", required = true) @RequestParam(value = "endDate") String endDate) {
        return new ResponseBean<>(polyvApiService.listDailySummary(channelId, startDate, endDate));
    }

    @ApiOperation(value = "数据报表", notes = "数据报表")
    @GetMapping("dataReport")
    @Log("数据报表")
    public PageInfo<DataReport> dataReport(
            @ApiParam(name = "dataReportRequest", value = "数据报表查询对象", required = true) DataReportRequest dataReportRequest) {
        return channelSummaryService.dataReport(dataReportRequest);
    }

    @ApiOperation(value = "预览报表", notes = "预览报表")
    @GetMapping("dataReportDetail")
    @Log("预览报表")
    public ResponseBean<DataReportEcharts> dataReportDetail(
            @ApiParam(name = "dataReportRequest", value = "预览报表查询对象", required = true) DataReportRequest dataReportRequest) {
        return new ResponseBean<>(channelSummaryService.dataReportDetail(dataReportRequest));
    }

    @ApiOperation(value = "下载报表", notes = "下载报表")
    @GetMapping("dataReportExcel")
    @Log("下载报表")
    public void dataReportExcel(
            @ApiParam(name = "dataReportRequest", value = "下载报表提交对象", required = true) DataReportRequest dataReportRequest, HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        try {
            String filename = channelSummaryService.dataReportExcel(dataReportRequest);
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20") + ".xlsx");
            // saveToStream有问题，采用读文件到流下载
            // workbook.saveToStream(response.getOutputStream());
            FileInputStream fileInputStream = new FileInputStream(new File("/home/park/attach/tempDir/DataReport.xlsx"));
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(buffer)) > 0) {
                response.getOutputStream().write(buffer, 0, len);
            }
            fileInputStream.close();
        } catch (IOException e) {
            log.error("下载报表时，发生IO异常", e);
            throw new CommonException("下载报表时，发生IO异常。");
        }
    }

    @ApiOperation(value = "获取最新一次直播的频道ID", notes = "获取最新一次直播的频道ID")
    @GetMapping("latestChannelId")
    @Log("获取最新一次直播的频道ID")
    public ResponseBean<String> latestChannelId() {
        return new ResponseBean<>(channelSummaryService.latestChannelId());
    }

}
