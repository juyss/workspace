package com.github.tangyi.webcast.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.webcast.api.dto.req.DataReportRequest;
import com.github.tangyi.webcast.api.dto.resp.DataReportEcharts;
import com.github.tangyi.webcast.api.dto.resp.LiveListChannelSummaryWithAverageValueResponse;
import com.github.tangyi.webcast.api.model.DataReport;
import com.spire.xls.*;
import com.spire.xls.charts.ChartSerie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.channel.viewdata.*;
import net.polyv.live.v1.service.channel.impl.LiveChannelViewdataServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统计Service
 *
 * @author Xiang Longfei
 * @date 2021/01/13
 */
@Slf4j
@AllArgsConstructor
@Service
public class ChannelSummaryService {

    private DataReportService dataReportService;

    public LiveListChannelSummaryWithAverageValueResponse get(List<String> channelIds, Date startDate, Date endDate) {
        LiveListChannelSummaryRequest liveListChannelSummaryRequest = new LiveListChannelSummaryRequest();
        LiveListChannelSummaryResponse liveListChannelSummaryResponse;

        LiveListChannelSummaryWithAverageValueResponse liveListChannelSummaryWithAverageValueResponse = new LiveListChannelSummaryWithAverageValueResponse();
        List<LiveListChannelSummaryWithAverageValueResponse.ChannelSummaryWithAverageValue> channelSummaryWithAverageValueList = new LinkedList<>();
        liveListChannelSummaryWithAverageValueResponse.setChannelSummarys(channelSummaryWithAverageValueList);

        try {
            liveListChannelSummaryRequest.setStartDate(startDate)
                    .setEndDate(endDate)
                    .setChannelIds(String.join(",", channelIds))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelSummaryResponse = new LiveChannelViewdataServiceImpl().listChannelSummary(
                    liveListChannelSummaryRequest);
            if (liveListChannelSummaryResponse != null) {
                // 计算平均
                List<LiveListChannelSummaryResponse.ChannelSummary> channelSummarys = liveListChannelSummaryResponse.getChannelSummarys();
                if (!CollectionUtils.isEmpty(channelSummarys)) {
                    for (LiveListChannelSummaryResponse.ChannelSummary channelSummary : channelSummarys) {
                        LiveListChannelSummaryWithAverageValueResponse.ChannelSummaryWithAverageValue channelSummaryWithAverageValue = new LiveListChannelSummaryWithAverageValueResponse.ChannelSummaryWithAverageValue();
                        // 复制
                        BeanUtils.copyProperties(channelSummary, channelSummaryWithAverageValue);
                        // 计算

                        // 总人数
                        int totalViewer = channelSummaryWithAverageValue.getPcUniqueViewer() + channelSummaryWithAverageValue.getMobileUniqueViewer();
                        // 总时长
                        long totalPlayDuration = channelSummaryWithAverageValue.getPcPlayDuration() + channelSummaryWithAverageValue.getMobilePlayDuration();
                        // 计算 人均观看时长 = 总时长 / 总人数
                        String averagePlayDurationString;
                        if (totalViewer == 0) {
                            averagePlayDurationString = "0";
                        } else {
                            averagePlayDurationString = String.format("%.1f", (double) totalPlayDuration / totalViewer);
                        }
                        channelSummaryWithAverageValue.setAveragePlayDuration(new Double(averagePlayDurationString));

                        // 计算 pc端人均观看时长 = pc时长 / pc人数
                        String pcAveragePlayDurationString;
                        if (channelSummaryWithAverageValue.getPcUniqueViewer() == 0) {
                            pcAveragePlayDurationString = "0";
                        } else {
                            pcAveragePlayDurationString = String.format("%.1f", (double) channelSummaryWithAverageValue.getPcPlayDuration() / channelSummaryWithAverageValue.getPcUniqueViewer());
                        }
                        channelSummaryWithAverageValue.setPcAveragePlayDuration(new Double(pcAveragePlayDurationString));

                        // 计算 移动端端人均观看时长 = 移动时长 / 移动人数
                        String mobileAveragePlayDurationString;
                        if (channelSummaryWithAverageValue.getMobileUniqueViewer() == 0) {
                            mobileAveragePlayDurationString = "0";
                        } else {
                            mobileAveragePlayDurationString = String.format("%.1f", (double) channelSummaryWithAverageValue.getMobilePlayDuration() / channelSummaryWithAverageValue.getMobileUniqueViewer());
                        }
                        channelSummaryWithAverageValue.setMobileAveragePlayDuration(new Double(mobileAveragePlayDurationString));

                        // 总次数
                        long totalVideoView = channelSummaryWithAverageValue.getPcVideoView() + channelSummaryWithAverageValue.getMobileVideoView();
                        // 计算 人均观看次数 = 总次数 / 总人数
                        String averageVideoViewString;
                        if (totalViewer == 0) {
                            averageVideoViewString = "0";
                        } else {
                            averageVideoViewString = String.format("%.1f", (double) totalVideoView / totalViewer);
                        }
                        channelSummaryWithAverageValue.setAverageVideoView(new Double(averageVideoViewString));

                        // 计算 pc端人均观看次数 = pc次数 / pc人数
                        String pcAverageVideoViewString;
                        if (channelSummaryWithAverageValue.getPcUniqueViewer() == 0) {
                            pcAverageVideoViewString = "0";
                        } else {
                            pcAverageVideoViewString = String.format("%.1f", (double) channelSummaryWithAverageValue.getPcVideoView() / channelSummaryWithAverageValue.getPcUniqueViewer());
                        }
                        channelSummaryWithAverageValue.setPcAverageVideoView(new Double(pcAverageVideoViewString));

                        // 计算 移动端端人均观看次数 = 移动次数 / 移动人数
                        String mobileAverageVideoViewString;
                        if (channelSummaryWithAverageValue.getMobileUniqueViewer() == 0) {
                            mobileAverageVideoViewString = "0";
                        } else {
                            mobileAverageVideoViewString = String.format("%.1f", (double) channelSummaryWithAverageValue.getMobileVideoView() / channelSummaryWithAverageValue.getMobileUniqueViewer());
                        }
                        channelSummaryWithAverageValue.setMobileAverageVideoView(new Double(mobileAverageVideoViewString));


                        channelSummaryWithAverageValueList.add(channelSummaryWithAverageValue);
                    }
                }

                log.debug("查询多个频道汇总的统计数据成功，{}", JSON.toJSONString(liveListChannelSummaryResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveListChannelSummaryWithAverageValueResponse;
    }

    public LiveListChannelViewlogResponse viewLog(LiveListChannelViewlogRequest liveListChannelViewlogRequest) {
        LiveListChannelViewlogResponse liveListChannelViewlogResponse;
        try {
            liveListChannelViewlogRequest.setRequestId(LiveSignUtil.generateUUID());
            liveListChannelViewlogResponse = new LiveChannelViewdataServiceImpl().listChannelViewlog(
                    liveListChannelViewlogRequest);
            if (liveListChannelViewlogResponse != null) {
                //to do something ......
                log.debug("分页查询频道观看日志成功，{}", JSON.toJSONString(liveListChannelViewlogResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveListChannelViewlogResponse;
    }

    public LiveListChannelViewerCountResponse viewerCount(List<String> channelIds) {
        LiveListChannelViewerCountRequest liveListChannelViewerCountRequest = new LiveListChannelViewerCountRequest();
        LiveListChannelViewerCountResponse liveListChannelViewerCountResponse;
        try {
            liveListChannelViewerCountRequest.setChannelIds(String.join(",", channelIds))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelViewerCountResponse = new LiveChannelViewdataServiceImpl().listChannelViewerCount(
                    liveListChannelViewerCountRequest);
            if (liveListChannelViewerCountResponse != null) {
                //to do something ......
                log.debug("查询多个频道的实时在线人数成功，{}", JSON.toJSONString(liveListChannelViewerCountResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveListChannelViewerCountResponse;
    }

    public LiveChannelViewerConcurrenceResponse viewerConcurrence(LiveChannelViewerConcurrenceRequest liveChannelViewerConcurrenceRequest) {
        LiveChannelViewerConcurrenceResponse liveChannelViewerConcurrenceResponse;
        try {
            liveChannelViewerConcurrenceRequest.setRequestId(LiveSignUtil.generateUUID());
            liveChannelViewerConcurrenceResponse = new LiveChannelViewdataServiceImpl().getChannelViewerConcurrence(
                    liveChannelViewerConcurrenceRequest);
            if (liveChannelViewerConcurrenceResponse != null) {
                //to do something ......
                log.debug("查询频道的历史在线人数成功，{}", JSON.toJSONString(liveChannelViewerConcurrenceResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelViewerConcurrenceResponse;
    }

    public PageInfo<DataReport> dataReport(DataReportRequest dataReportRequest) {
        PageInfo<DataReport> page = new PageInfo<>();
        page.setPageNum(dataReportRequest.getPageNum());
        page.setPageSize(dataReportRequest.getPageSize());
        return dataReportService.findPage(page, dataReportRequest);
    }

    public DataReportEcharts dataReportDetail(DataReportRequest dataReportRequest) {
        List<DataReport> dataReportList = dataReportService.findAllList(dataReportRequest);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            int entTimeIndex = -1;
            Date endTime;
            Date startTime = simpleDateFormat.parse(dataReportRequest.getStartDate());
            for (int i = 0; i < dataReportList.size(); i++) {
                if (dataReportList.get(i).getStartTime().equals(startTime)) {
                    // 取倒序的下一个元素
                    entTimeIndex = i + 1;
                }
            }
            // 如果没有下一个元素，则取当前时间
            if (entTimeIndex < 0 || entTimeIndex >= dataReportList.size()) {
                endTime = new Date();
            } else {
                // 否则取下一个元素的开始时间
                endTime = dataReportList.get(entTimeIndex).getStartTime();
            }

            // 不支持跨月查询做的处理
            ZoneId zoneId = ZoneId.systemDefault();
            Instant startTimeInstant = startTime.toInstant();
            Instant endTimeInstant = endTime.toInstant();
            LocalDateTime startTimeLocalDateTime = startTimeInstant.atZone(zoneId).toLocalDateTime();
            LocalDateTime endTimeLocalDateTime = endTimeInstant.atZone(zoneId).toLocalDateTime();
            if (endTimeLocalDateTime.getMonthValue() != startTimeLocalDateTime.getMonthValue()) {
                endTimeLocalDateTime.withMonth(startTimeLocalDateTime.getMonthValue());
                endTime = Date.from(endTimeLocalDateTime.atZone(zoneId).toInstant());
            }
            // 查询数据
            LiveListChannelViewlogRequest liveListChannelViewlogRequest = new LiveListChannelViewlogRequest().setChannelId(dataReportRequest.getChannelId()).setStartTime(startTime).setEndTime(endTime);
            liveListChannelViewlogRequest.setPageSize(1000);
            List<LiveListChannelViewlogResponse.LiveChannelViewlog> liveListChannelViewlogResponse = viewLog(liveListChannelViewlogRequest).getContents();

            List<String> channelList = new ArrayList<>();
            channelList.add(dataReportRequest.getChannelId());
            LiveListChannelSummaryWithAverageValueResponse liveListChannelSummaryWithAverageValueResponse = get(channelList, startTime, endTime);
            // 数据处理
            return generateEcharts(liveListChannelViewlogResponse, liveListChannelSummaryWithAverageValueResponse);
        } catch (ParseException e) {
            log.error("预览报表（获取报表详情）时，日期转换异常", e);
            throw new CommonException("日期转换异常，请符合模式yyyy-MM-dd HH:mm:ss");
        }
    }

    private DataReportEcharts generateEcharts(List<LiveListChannelViewlogResponse.LiveChannelViewlog> liveListChannelViewlogResponse, LiveListChannelSummaryWithAverageValueResponse liveListChannelSummaryWithAverageValueResponse) {
        DataReportEcharts result = new DataReportEcharts();
        result.setLiveListChannelSummary(liveListChannelSummaryWithAverageValueResponse);
        List<String> provinceList = liveListChannelViewlogResponse.stream().map(liveChannelViewlog -> liveChannelViewlog.getProvince()).distinct().collect(Collectors.toList());
        result.setProvinceX(provinceList);

        List<Long> provinceSeries = new ArrayList<>();
        for (String province : provinceList) {
            long count = liveListChannelViewlogResponse.stream().filter(liveChannelViewlog -> (province.equals(liveChannelViewlog.getProvince()) && StringUtils.hasText(liveChannelViewlog.getUserId()))).count();
            provinceSeries.add(count);
        }

        return result;
    }

    public String dataReportExcel(DataReportRequest dataReportRequest) {
        DataReportRequest dataReportExample = new DataReportRequest().setChannelId(dataReportRequest.getChannelId());
        List<DataReport> dataReportList = dataReportService.findAllList(dataReportExample);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String filename = "数据报表";
            String title = "";
            String subtitle = "";
            int entTimeIndex = -1;
            Date endTime;
            Date startTime = simpleDateFormat.parse(dataReportRequest.getStartDate());
            for (int i = 0; i < dataReportList.size(); i++) {
                if (dataReportList.get(i).getStartTime().equals(startTime)) {
                    filename = dataReportList.get(i).getName() + "-直播数据报表";
                    title = dataReportList.get(i).getName() + "(" + dataReportRequest.getChannelId() + ")";
                    subtitle = dataReportRequest.getStartDate() + "直播数据报表";
                    // 取正序的下一个元素
                    entTimeIndex = i + 1;
                }
            }
            // 如果没有下一个元素，则取当前时间
            if (entTimeIndex < 0 || entTimeIndex >= dataReportList.size()) {
                endTime = new Date();
            } else {
                // 否则取下一个元素的开始时间
                endTime = dataReportList.get(entTimeIndex).getStartTime();
            }
            // 查询数据
            LiveListChannelViewlogRequest liveListChannelViewlogRequest = new LiveListChannelViewlogRequest().setChannelId(dataReportRequest.getChannelId()).setStartTime(startTime).setEndTime(endTime);
            liveListChannelViewlogRequest.setPageSize(1000);
            List<LiveListChannelViewlogResponse.LiveChannelViewlog> liveListChannelViewlogResponse = viewLog(liveListChannelViewlogRequest).getContents();

            List<String> channelList = new ArrayList<>();
            channelList.add(dataReportRequest.getChannelId());
            LiveListChannelSummaryWithAverageValueResponse.ChannelSummaryWithAverageValue channelSummaryWithAverageValue = get(channelList, startTime, endTime).getChannelSummarys().get(0);

            String liveListChannelSummary = String.format("本次直播观看分钟数：%s。唯一观众数：%s，其中PC端用户总数：%s，移动端用户总数：%s",
                    channelSummaryWithAverageValue.getPcPlayDuration() + channelSummaryWithAverageValue.getMobilePlayDuration(),
                    channelSummaryWithAverageValue.getPcUniqueViewer() + channelSummaryWithAverageValue.getMobileUniqueViewer(),
                    channelSummaryWithAverageValue.getPcUniqueViewer(),
                    channelSummaryWithAverageValue.getMobileUniqueViewer());

            List<String> provinceList = liveListChannelViewlogResponse.stream().map(liveChannelViewlog -> liveChannelViewlog.getProvince()).distinct().filter(province -> StringUtils.hasText(province)).collect(Collectors.toList());

            List<Integer> maxCountIndex = new ArrayList<>();
            List<String> maxProvinceList = new ArrayList<>();
            long maxCount = 0;
            List<Long> provinceSeries = new ArrayList<>();
            for (String province : provinceList) {
                long count = liveListChannelViewlogResponse.stream().filter(liveChannelViewlog -> (province.equals(liveChannelViewlog.getProvince()) && StringUtils.hasText(liveChannelViewlog.getUserId()))).count();
                provinceSeries.add(count);
                if (count > maxCount) {
                    maxCount = count;
                }
            }

            for (int i = 0; i < provinceSeries.size(); i++) {
                if (provinceSeries.get(i) == maxCount) {
                    maxCountIndex.add(i);
                }
            }
            for (Integer countIndex : maxCountIndex) {
                maxProvinceList.add(provinceList.get(countIndex));
            }
            String provinceSummary = String.format("本次直播观众分布在%s个省份，其中%s的观看次数最多", maxCountIndex.size(), String.join("，", maxProvinceList));

            //创建Excel文档
            Workbook workbook = new Workbook();
            //获取第一个工作表
            Worksheet sheet = workbook.getWorksheets().get(0);
            //设置工作表名称
            sheet.setName("数据报表");

            //将第1列的列宽设为140
            sheet.setColumnWidth(1, 140);
            //右对齐
            sheet.getCellRange("A1").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Right);
            //设置字体大小
            sheet.getCellRange("A1").getCellStyle().getExcelFont().setSize(12);
            //设置字体颜色
            sheet.getCellRange("A1").getCellStyle().getExcelFont().setColor(Color.lightGray);
            // 写入文本
            sheet.getCellRange("A1").setText("*报表仅显示直播情况，不包含回放数据");

            //将第2行的行高设为30
            sheet.setRowHeight(2, 30);
            //水平居中
            sheet.getCellRange("A2").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
            //设置字体大小
            sheet.getCellRange("A2").getCellStyle().getExcelFont().setSize(20);
            // 写入文本
            sheet.getCellRange("A2").setText(title);

            //将第3行的行高设为26
            sheet.setRowHeight(3, 26);
            //水平居中
            sheet.getCellRange("A3").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
            //设置字体大小
            sheet.getCellRange("A3").getCellStyle().getExcelFont().setSize(17);
            // 写入文本
            sheet.getCellRange("A3").setText(subtitle);

            //将第3列的列宽设为10
            sheet.setColumnWidth(3, 10);

            /*sheet.getRange().get("B4").setValue("PC端观众");
            sheet.getRange().get("C4").setValue("移动端观众");
            sheet.getRange().get("B5").setNumberValue(channelSummaryWithAverageValue.getPcUniqueViewer());
            sheet.getRange().get("C5").setNumberValue(channelSummaryWithAverageValue.getMobileUniqueViewer());
            //添加饼图
            Chart pieChart = sheet.getCharts().add(ExcelChartType.Pie);
            //设置图表数据区域
            pieChart.setDataRange(sheet.getCellRange("B5:C5"));
            pieChart.setSeriesDataFromRange(false);
            //设置图表位置大小
            pieChart.setLeftColumn(1);
            pieChart.setRightColumn(2);
            pieChart.setTopRow(4);
            pieChart.setBottomRow(20);

            //设置图表标题
            pieChart.setChartTitle("");
            pieChart.getChartTitleArea().isBold(true);
            pieChart.getChartTitleArea().setSize(12);
            //设置系列标签
            ChartSerie cs = pieChart.getSeries().get(0);
            cs.setCategoryLabels(sheet.getCellRange("B4:C5"));
            cs.setValues(sheet.getCellRange("B5:C5"));
            cs.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(false);
            pieChart.getPlotArea().getFill().setVisible(false);

            //水平居中
            sheet.getCellRange("A20").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
            //设置字体大小
            sheet.getCellRange("A20").getCellStyle().getExcelFont().setSize(15);
            // 写入文本
            sheet.getCellRange("A20").setText(liveListChannelSummary);

            //水平居中
            sheet.getCellRange("A21").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
            //设置字体大小
            sheet.getCellRange("A21").getCellStyle().getExcelFont().setSize(13);
            // 写入文本
            sheet.getCellRange("A21").setText("注：唯一观众数为用户ID去重后结果");*/

            //水平居中
            sheet.getCellRange("A4").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
            //设置字体大小
            sheet.getCellRange("A4").getCellStyle().getExcelFont().setSize(15);
            // 写入文本
            sheet.getCellRange("A4").setText(provinceSummary);

            sheet.getRange().get("B5").setValue("省份");
            sheet.getRange().get("C5").setValue("观看次数");
            int baseRow = 6;
            for (int i = 0; i < provinceList.size(); i++) {
                int row = baseRow + i;
                sheet.getRange().get(row, 2).setValue(provinceList.get(i));
            }
            for (int i = 0; i < provinceSeries.size(); i++) {
                int row = baseRow + i;
                sheet.getRange().get(row, 3).setNumberValue(provinceSeries.get(i));
            }

            //添加图表到工作表
            Chart chart = sheet.getCharts().add(ExcelChartType.ColumnClustered);
            chart.setDataRange(sheet.getRange().get(baseRow, 3, baseRow + provinceSeries.size(), 3));
            chart.setSeriesDataFromRange(false);
            chart.setTopRow(5);
            chart.setBottomRow(55);
            chart.setLeftColumn(1);
            chart.setRightColumn(2);
            chart.setChartTitle("");
            chart.getChartTitleArea().isBold(true);
            chart.getChartTitleArea().setSize(12);
            ChartSerie cs1 = chart.getSeries().get(0);
            cs1.setCategoryLabels(sheet.getRange().get(baseRow, 2, baseRow + provinceList.size(), 2));
            cs1.setName("观看次数");
            //添加数据表到图表
            chart.hasDataTable(true);

            String tempDir = "/home/park/attach/tempDir";
            File tempDirFile = new File(tempDir);
            if (!tempDirFile.exists()) {
                tempDirFile.mkdirs();
            }

            workbook.saveToFile(tempDir + "/DataReport.xlsx", ExcelVersion.Version2010);

            return filename;
        } catch (ParseException e) {
            log.error("下载报表时，日期转换异常", e);
            throw new CommonException("日期转换异常，请符合模式yyyy-MM-dd HH:mm:ss");
        }
    }

    public String latestChannelId() {
        return dataReportService.latestChannelId();
    }
}
