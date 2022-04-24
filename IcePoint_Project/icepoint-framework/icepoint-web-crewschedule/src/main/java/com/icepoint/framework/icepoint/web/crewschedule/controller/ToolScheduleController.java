package com.icepoint.framework.icepoint.web.crewschedule.controller;

import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolSchedule;
import com.icepoint.framework.icepoint.web.crewschedule.excel.ScheduleToday;
import com.icepoint.framework.icepoint.web.crewschedule.service.ToolScheduleService;
import com.icepoint.framework.icepoint.web.crewschedule.utils.ExcelUtil;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * 排班管理
 * @author Juyss
 * @version 1.0
 * @ClassName ToolScheduleController
 * @description
 * @since 2021-07-28 13:59
 */
@RestController
@RequestMapping("toolSchedule")
public class ToolScheduleController {
    
    @Autowired
    private ToolScheduleService service;

    /**
     * 增加班次
     * @author Juyss
     * @param toolSchedule 数据
     * @return Response<Integer>
     */
    @PostMapping
    public Response<Integer> insert(ToolSchedule toolSchedule){
        return ResponseUtils.any(service.insert(toolSchedule));
    }

    /**
     * 修改班次
     * @author Juyss
     * @param toolSchedule 数据
     * @return Response<Integer>
     */
    @PutMapping
    public Response<Integer> update(ToolSchedule toolSchedule){
        return ResponseUtils.any(service.update(toolSchedule));
    }

    /**
     * 删除班次
     * @author Juyss
     * @param ids 主键集合 多个id示例 1,2,3
     * @return Response<Integer>
     */
    @DeleteMapping
    public Response<Integer> delete(List<Long> ids){
        return ResponseUtils.any(service.delete(ids));
    }

    /**
     * 排班信息分页查询
     * @author Juyss
     * @param deptId 部门
     * @param roleId 角色
     * @param userName 用户名
     * @param pageable 分页数据
     * @param start 开始时间 1997-05-11
     * @param end 结束时间 1997-05-11
     * @return Response<Integer>
     */
    @GetMapping("page")
    public PageResponse<Map<String, Object>> findAll(Long deptId, Long roleId, String userName, Pageable pageable, Date start, Date end){
        return ResponseUtils.page(service.findAll(deptId,roleId, userName, pageable, start, end));
    }

    /**
     * 详情查询
     * @author Juyss
     * @param id 主键
     * @return Response<Integer>
     */
    @GetMapping("{id}")
    public Response<ToolSchedule> findOne(@PathVariable("id") Long id){
        return ResponseUtils.one(service.findOne(id));
    }

    /**
     * 导出排班表
     * @author Juyss
     * @param response
     * @param request
     * @param start 开始时间
     * @param end 结束时间
     * @return void
     */
    @GetMapping(value = "exportExcel")
    public void exportExcel(HttpServletResponse response, HttpServletRequest request, Date start, Date end){
        //获取数据
        List<ScheduleToday> excelDataList = service.getExportExcelDataList(start, end);

        //获取日期
        List<String> dayList = service.getBetweenDate(start.toString(), end.toString());

        //生成表头
        List<List<String>> excelHead = service.getExcelHead(dayList);

        ExcelUtil.exportBeanByEasyExcel(response,request,"人员排班表",excelDataList,ScheduleToday.class,excelHead,1,1);
    }
    
}
