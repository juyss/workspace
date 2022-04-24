package com.github.tangyi.pub.controller;


import bean.ScheduleTableBean;
import bean.ScheduleTableListBean;
import bean.ScheduleWorkerBean;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.pub.service.IShiftsService;
import com.google.common.collect.Lists;
import entity.Schedule;
import entity.ScheduleToday;
import entity.Shifts;
import entity.UserSchedule;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import param.ScheduleParam;
import util.ExcelUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Api(description = "班次相关接口")
@RestController
@RequestMapping("/shifts")
public class ShiftsController {

    private final IShiftsService shiftsService;

    @ApiOperation(value = "分页查询班次")
    @GetMapping("/findShiftsPage")
    @Log("分页查询班次")
    public ResponseBean<PageInfo<Shifts>> findShiftsPage(Pageable pageable) {
        return ResponseBeanUtils.queryPageInfo(shiftsService.findShiftsPage(pageable).toPageInfo());
    }

    @ApiOperation(value = "分页查询班次")
    @GetMapping("/findShiftsById")
    @Log("分页查询班次")
    public ResponseBean<Shifts> findShiftsById(String id) {
        Assert.notNull(id, "id不能为空");
        return ResponseBeanUtils.queryOne(shiftsService.findShiftsById(Long.valueOf(id)));
    }

    @ApiOperation("删除班次")
    @DeleteMapping("/deleteShifts")
    @Log("删除班次")
    public ResponseBean<Boolean> deleteShifts(String ids) {
        Assert.notNull(ids, "ids不能为空");
        boolean b = shiftsService.deleteShifts(ids);
        Assert.state(b, "删除失败,班次已存在在值班表中");
        return ResponseBean.success(Boolean.TRUE);
    }

    @ApiOperation("新增班次")
    @PostMapping("/addShifts")
    @ResponseBody
    @Log("新增班次")
    public ResponseBean<Boolean> addShifts(@RequestBody Shifts shifts) {
        Assert.notNull(shifts, "参数为空");
        return ResponseBeanUtils.addNewData(shiftsService.addShifts(shifts));
    }

    @ApiOperation("编辑班次")
    @PostMapping("/updateShifts")
    @ResponseBody
    @Log("编辑班次")
    public ResponseBean<Boolean> updateShifts(@RequestBody Shifts shifts) {
        Assert.notNull(shifts.getId(), "参数为空");
        boolean b = shiftsService.updateShifts(shifts);
        Assert.state(b, "修改失败");
        return ResponseBean.success(Boolean.TRUE);
    }


    @ApiOperation(value = "分页人员排班")
    @PostMapping("/findSchedulePage")
    @Log("分页人员排班")
    public ResponseBean<PageInfo<UserSchedule>> findSchedulePage(@RequestBody ScheduleParam scheduleParam, Pageable pageable) {
        Assert.notNull(scheduleParam.getStartDate(), "排班开始时间不能为空");
        Assert.notNull(scheduleParam.getEndDate(), "排班结束时间不能为空");
        List<String> week = new ArrayList<>(7);

        Calendar satrtCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        satrtCal.setTime(scheduleParam.getStartDate());
        endCal.setTime(scheduleParam.getEndDate());
        while (satrtCal.before(endCal)) {
            week.add(new SimpleDateFormat("yyyy-MM-dd").format(satrtCal.getTime()));
            satrtCal.add(Calendar.DAY_OF_WEEK, 1);
            if (week.size() > 7) {
                return null;
            }
        }
        ;
        week.add(new SimpleDateFormat("yyyy-MM-dd").format(endCal.getTime()));
        scheduleParam.setWeek(week);
        return ResponseBeanUtils.queryPageInfo(shiftsService.findSchedulePage(scheduleParam, pageable).toPageInfo());
    }

    @ApiOperation("新增人员排班")
    @PostMapping("/addSchedule")
    @ResponseBody
    @Log("新增人员排班")
    public ResponseBean<Boolean> addSchedule(@RequestBody Schedule schedule) {
        Assert.notNull(schedule.getUserId(), "用户id为空");
        Assert.notNull(schedule.getWorkDay(), "日期为空");
        Assert.notNull(schedule.getShiftsIds(), "班次为空");
        return ResponseBeanUtils.addNewData(shiftsService.addSchedule(schedule));
    }

    @ApiOperation("删除人员排班")
    @PostMapping("/deleteSchedule")
    @Log("删除人员排班")
    public ResponseBean<Boolean> deleteSchedule(@RequestBody Schedule schedule) {
        Assert.notNull(schedule.getUserId(), "用户id为空");
        Assert.notNull(schedule.getWorkDay(), "日期为空");
        boolean a = shiftsService.deleteSchedule(schedule);
        Assert.state(a, "删除失败");
        return ResponseBean.success(Boolean.TRUE);
    }

    @ApiOperation("复制上周排班")
    @PostMapping("/copySchedule")
    @ResponseBody
    @Log("复制上周排班")
    public ResponseBean<Boolean> copySchedule(@RequestBody ScheduleParam scheduleParam) {
        Assert.notNull(scheduleParam.getStartDate(), "排班开始时间不能为空");
        Assert.notNull(scheduleParam.getEndDate(), "排班结束时间不能为空");
        boolean b = shiftsService.copySchedule(scheduleParam);
        Assert.state(b, "复制排班失败");
        return ResponseBean.success(Boolean.TRUE);
    }

    @ApiOperation("分页查询排班展示值班表")
    @PostMapping("/findSchedulePageForCheck")
    @ResponseBody
    @Log("分页查询排班展示值班表")
    public ResponseBean<PageInfo<ScheduleTableBean>> findSchedulePageForCheck(@RequestBody ScheduleParam scheduleParam, Pageable pageable) {
        Assert.notNull(scheduleParam.getStartDate(), "值班开始时间不能为空");
        Assert.notNull(scheduleParam.getEndDate(), "值班结束时间不能为空");
        return ResponseBeanUtils.queryPageInfo(shiftsService.findSchedulePageForCheck(scheduleParam).toPageInfo());
    }

    @ApiOperation("分页查询排班展示值班表")
    @PostMapping("/findScheduleTablePage")
    @ResponseBody
    @Log("分页查询排班展示值班表")
    public ResponseBean<PageInfo<UserSchedule>> findScheduleTablePage(@RequestBody ScheduleParam scheduleParam) {
        Assert.notNull(scheduleParam.getStartDate(), "值班开始时间不能为空");
        Assert.notNull(scheduleParam.getEndDate(), "值班结束时间不能为空");
        return ResponseBeanUtils.queryPageInfo(shiftsService.findScheduleTablePage(scheduleParam));
    }

    @GetMapping(value = "/exportExcel")
    @Log("导出排班表")
    public void exportExcel(HttpServletResponse response, HttpServletRequest request, ScheduleParam scheduleParam, @PageableDefault(page = 1, size = 9999) Pageable pageable) throws IOException {

        Assert.notNull(scheduleParam.getStartDate(), "排班开始时间不能为空");
        Assert.notNull(scheduleParam.getEndDate(), "排班结束时间不能为空");
        scheduleParam.setPage(1);
        scheduleParam.setRows(9999);
        List<ScheduleTableBean> list = shiftsService.findSchedulePageForCheck(scheduleParam);
        List<UserSchedule> userScheduleList = shiftsService.findScheduleTablePage(scheduleParam).getList();
        List<ScheduleToday> excelDataList = userScheduleList.stream().map(userSchedule -> {
            List<String> usernamesByDay = new ArrayList<>();
            userSchedule.getSchedules().forEach(schedule -> {
                usernamesByDay.add(schedule.getShiftsName());
            });
            return ScheduleToday.builder()
                    .deptName(userSchedule.getDeptName())
                    .monday(usernamesByDay.get(0))
                    .tuesday(usernamesByDay.get(1))
                    .wednesday(usernamesByDay.get(2))
                    .thursday(usernamesByDay.get(3))
                    .friday(usernamesByDay.get(4))
                    .saturday(usernamesByDay.get(5))
                    .sunday(usernamesByDay.get(6))
                    .build();
        }).collect(Collectors.toList());

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream()).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("值班表").build();
            List<List<String>> head = Lists.newArrayList();
            head.add(Lists.newArrayList("部门"));
            String[] dayList = new String[7];
            list.get(0).getDays().keySet().toArray(dayList);
            for (int i = 0; i < dayList.length; i++) {
                String weekName = null;
                switch (i) {
                    case 0:
                        weekName = "星期一";
                        break;
                    case 1:
                        weekName = "星期二";
                        break;
                    case 2:
                        weekName = "星期三";
                        break;
                    case 3:
                        weekName = "星期四";
                        break;
                    case 4:
                        weekName = "星期五";
                        break;
                    case 5:
                        weekName = "星期六";
                        break;
                    case 6:
                        weekName = "星期日";
                        break;
                }
                head.add(Lists.newArrayList(dayList[i] + "\r\n" + weekName));
            }

            WriteTable writeTable = EasyExcel.writerTable().head(head).build();


            excelWriter.write(excelDataList, writeSheet, writeTable);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + ExcelUtil.encodingFileName("值班表" + ".xlsx", request));

        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


}
