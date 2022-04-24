package com.github.tangyi.pub.mapper;

import bean.ScheduleTableBean;
import bean.ScheduleTableListBean;
import com.github.pagehelper.Page;
import entity.Schedule;
import entity.Shifts;
import entity.UserSchedule;
import org.apache.ibatis.annotations.Param;
import param.ScheduleParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ShiftMapper {
    Page<Shifts> findShiftsPage();

    Shifts findShiftsById(Long id);

    boolean deleteShifts(List<String> list);

    Integer getShiftsUser(Map<String, Object> param);

    boolean addShifts(Shifts shifts);

    boolean updateShifts(Shifts shifts);

    Page<UserSchedule> findUser(ScheduleParam scheduleParam);

    List<Schedule> schedulesList(ScheduleParam scheduleParam);

    void addSchedule(Map<String, Object> schedule);

    boolean deleteSchedule(Schedule schedule);

    List<UserSchedule> findPeriodSchedule(ScheduleParam scheduleParam);

    void addNewSchedule(Map<String, Object> sche);

    Page<ScheduleTableBean> findSchedulePageForCheck2(@Param("param") ScheduleParam scheduleParam);

    List<ScheduleTableListBean> findScheduleTable(@Param("param") ScheduleParam scheduleParam);

    void deleteWeekSchedule(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("list") List<Long> userScheduleIdList);
}
