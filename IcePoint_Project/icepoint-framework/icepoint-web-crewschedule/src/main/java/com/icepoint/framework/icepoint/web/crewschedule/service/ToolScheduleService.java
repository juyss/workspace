package com.icepoint.framework.icepoint.web.crewschedule.service;

import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolGroup;
import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolSchedule;
import com.icepoint.framework.icepoint.web.crewschedule.excel.ScheduleToday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName ToolScheduleService
 * @description
 * @since 2021-07-28 13:50
 */
public interface ToolScheduleService {

    /**
     *
     * @author Juyss
     * @param toolSchedule 数据实体
     * @return Integer
     */
    Integer insert(ToolSchedule toolSchedule);

    /**
     *
     * @author Juyss
     * @param toolSchedule 数据实体
     * @return Integer
     */
    Integer update(ToolSchedule toolSchedule);

    /**
     *
     * @author Juyss
     * @param ids id集合
     * @return Integer
     */
    Integer delete(List<Long> ids);

    /**
     * 查询排班信息
     *
     * @author Juyss
     * @param deptId 部门
     * @param roleId 角色
     * @param userName 用户名
     * @param pageable 分页数据
     * @param start 开始时间
     * @param end 结束时间
     * @return Page<ToolSchedule>
     */
    Page<Map<String, Object>> findAll(Long deptId, Long roleId, String userName, Pageable pageable, Date start, Date end);

    /**
     *
     * @author Juyss
     * @param id 主键
     * @return ToolSchedule
     */
    ToolSchedule findOne(Long id);

    /**
     * 查询分组
     * @author Juyss
     * @param deptId 部门id
     * @param roleId 角色id
     * @param userName 用户名
     * @return List<ToolGroup>
     */
    List<ToolGroup> findGroup(Long deptId, Long roleId, String userName);

    /**
     * 根据起止时间查询排班表
     * @author Juyss
     * @param start 开始时间
     * @param end 结束时间
     * @return List<Map<String,Object>>
     */
    List<Map<String, Object>> findCrewSchedule(Date start, Date end);

    /**
     * 根据起止时间查询导出Excel所需数据
     * @author Juyss
     * @param start 开始时间
     * @param end 结束时间
     * @return List<ToolSchedule>
     */
    List<ScheduleToday> getExportExcelDataList(Date start, Date end);

    /**
     * 获取指定时间段内的每天的日期
     * @author Juyss
     * @param start
     * @param end
     * @return List<String>
     */
    List<String> getBetweenDate(String start, String end);

    /**
     * 获取Excel动态表头数据
     * @author Juyss
     * @param dayList
     * @return List<List<String>>
     */
    List<List<String>> getExcelHead(List<String> dayList);
}
