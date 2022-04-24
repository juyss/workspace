package com.github.tangyi.pub.service;


import bean.ScheduleTableBean;
import com.github.pagehelper.Page;

import com.github.pagehelper.PageInfo;
import entity.Schedule;
import entity.Shifts;
import entity.UserSchedule;
import org.springframework.data.domain.Pageable;
import param.ScheduleParam;

public interface IShiftsService {

    Page<Shifts> findShiftsPage(Pageable pageable);

    Shifts  findShiftsById(Long id);

    boolean deleteShifts(String ids);

    boolean addShifts(Shifts shifts);

    boolean updateShifts(Shifts shifts);

    Page<UserSchedule> findSchedulePage(ScheduleParam scheduleParam, Pageable pageable);

    boolean addSchedule(Schedule schedule);

    boolean deleteSchedule(Schedule schedule);

    boolean copySchedule(ScheduleParam scheduleParam);

    Page<ScheduleTableBean> findSchedulePageForCheck(ScheduleParam scheduleParam);

    PageInfo<UserSchedule> findScheduleTablePage(ScheduleParam scheduleParam);
}
