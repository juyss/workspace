package com.github.tangyi.pub.service.impl;


import bean.ScheduleTableBean;
import bean.ScheduleTableListBean;
import bean.ScheduleWorkerBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.pub.mapper.ShiftMapper;
import com.github.tangyi.pub.service.IShiftsService;
import com.google.common.collect.Sets;
import entity.Schedule;
import entity.Shifts;
import entity.UserSchedule;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import param.ScheduleParam;
import util.ObjectUtils;
import util.StringUtil;
import util.TimeUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftsServiceImpl implements IShiftsService {

    private final ShiftMapper shiftMapper;

    @Override
    public Page<Shifts> findShiftsPage(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        return shiftMapper.findShiftsPage();
    }

    @Override
    public Shifts findShiftsById(Long id) {
        return shiftMapper.findShiftsById(id);
    }

    @Override
    @Transactional
    public boolean deleteShifts(String ids) {
        List<String> list = new ArrayList<String>();
        String[] strArr = ids.split(",");
        for (int i = 0; i < strArr.length; i++) {
            list.add(strArr[i]);
        }
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ids", list);
        param.put("today", today);

        if (shiftMapper.getShiftsUser(param) != 0) {
            return false;
        }

        return shiftMapper.deleteShifts(list);
    }

    @Override
    public boolean addShifts(Shifts shifts) {
        String user = SysUtil.getUser();
        shifts.setCreator(user);
        shifts.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return shiftMapper.addShifts(shifts);
    }

    @Override
    public boolean updateShifts(Shifts shifts) {
        return shiftMapper.updateShifts(shifts);
    }

    @Override
    @Transactional
    public Page<UserSchedule> findSchedulePage(ScheduleParam scheduleParam, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Page<UserSchedule> page = shiftMapper.findUser(scheduleParam);
        if (!CollectionUtils.isEmpty(page)) {
            List<Schedule> schedulesList = shiftMapper.schedulesList(scheduleParam);
            page.forEach(userSchedule -> {
                List<Schedule> scheduleBeanList = new ArrayList();
                for (String weekDay : scheduleParam.getWeek()) {
                    Schedule schedule = null;
                    for (Schedule sch : schedulesList) {
                        String workDay = new SimpleDateFormat("yyyy-MM-dd").format(sch.getWorkDay());
                        if (userSchedule.getUserId().equals(sch.getUserId()) && weekDay.equalsIgnoreCase(workDay)) {
                            schedule = sch;

                        }
                    }
                    if (schedule == null) {
                        schedule = new Schedule();
                        Date date = null;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            date = sdf.parse(weekDay);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        schedule.setWorkDay(date);
                    }
                    scheduleBeanList.add(schedule);
                }
                userSchedule.setSchedules(scheduleBeanList);
            });

        }
        return page;
    }

    @Override
    public boolean addSchedule(Schedule schedule) {
        if (schedule == null) {
            return false;
        }

        if (!StringUtil.isInvalid(schedule.getShiftsIds())) {
            //新增排班（人员、班次、日期关系）
            String[] ids = schedule.getShiftsIds().split(",");
            Map<String, Object> sche = new HashMap<>();
            sche.put("userId", schedule.getUserId());
            sche.put("workDay", schedule.getWorkDay());
            sche.put("shiftsIds", ids);
            shiftMapper.addSchedule(sche);
        } else {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteSchedule(Schedule schedule) {
        return shiftMapper.deleteSchedule(schedule);
    }

    @Override
    @Transactional
    public boolean copySchedule(ScheduleParam scheduleParam) {
        List<UserSchedule> list = shiftMapper.findPeriodSchedule(scheduleParam);
        Date startTime = TimeUtil.weekDayMapByLocal(scheduleParam.getWorkDate(), scheduleParam.getStartDate());
        Date endTime = TimeUtil.weekDayMapByLocal(scheduleParam.getWorkDate(), scheduleParam.getEndDate());
        List<Long> userScheduleIdList = list.stream().map(userSchedule -> userSchedule.getUserId()).distinct().collect(Collectors.toList());
        shiftMapper.deleteWeekSchedule(startTime, endTime, userScheduleIdList);
        Map<String, Object> sche = null;
        if (list != null && list.size() > 0) {
            for (UserSchedule user : list) {
                sche = new HashMap<>();
                sche.put("userId", user.getUserId());
                List<Schedule> schedules = user.getSchedules();
                if (schedules != null && schedules.size() > 0) {
                    for (Schedule schedule : schedules) {
                        Date workDay = TimeUtil.weekDayMapByLocal(scheduleParam.getWorkDate(), schedule.getWorkDay());
                        sche.put("workDay", workDay);
						/*schedule.setUserId(user.getId());
						schedule.setWorkDay(workDay);
						scheduleWriteMapper.deleteSchedule(schedule);*/
                        List<Shifts> shiftses = schedule.getShifts();
                        if (shiftses != null && shiftses.size() > 0) {
                            List<String> ids = new ArrayList<>();
                            for (Shifts shifts : shiftses) {
                                ids.add(String.valueOf(shifts.getId()));
                            }
                            sche.put("shiftsIds", ids);
                            shiftMapper.addNewSchedule(sche);
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Page<ScheduleTableBean> findSchedulePageForCheck(ScheduleParam scheduleParam) {
        scheduleParam.setWeek(TimeUtil.converedPeriodToWeek(scheduleParam.getStartDate(), scheduleParam.getEndDate()));
        PageHelper.startPage(scheduleParam.getPage(), scheduleParam.getRows());
        Page<ScheduleTableBean> page = shiftMapper.findSchedulePageForCheck2(scheduleParam);

        if (CollectionUtils.isEmpty(page))
            return null;

        for (ScheduleTableBean bean : page) {
            // 初始化值班日map，key是工作日，value是工作日对应的多个值班人员
            // 使用TreeMap是为了以工作日从小到大排序
            Map<String, Collection<ScheduleWorkerBean>> daysMap = new TreeMap<>((d1, d2) -> {
                if (Objects.equals(d1, d2))
                    return 0;
                else if (d1 == null)
                    return -1;
                else if (d2 == null)
                    return 1;
                else
                    return d1.compareTo(d2);
            });
            scheduleParam.getWeek().forEach(day -> daysMap.put(day, Sets.newHashSet()));

            // 值班数据放在一个字符串里，这里要进行拆分组装对象
            String daysStr = bean.getDaysStr(); // {userId, workDay, username}|{...}...
            if (StringUtils.hasText(daysStr)) {
                String[] daysArray = daysStr.split("\\|");
                if (ArrayUtils.isNotEmpty(daysArray)) {

                    // 组装每一个值班人员bean
                    // {userId, workday, username}
                    for (String day : daysArray) {
                        if (!StringUtils.hasText(day))
                            continue;

                        String[] dayProperties = day.substring(1, day.length() - 1).split(",");
                        if (ArrayUtils.isEmpty(dayProperties) || dayProperties.length != 3)
                            continue;

                        ScheduleWorkerBean worker = new ScheduleWorkerBean();
                        worker.setId(ObjectUtils.nullSafeParseLong(dayProperties[0]));
                        worker.setUsername(dayProperties[2]);

                        // 如果有相同值班日的值班人员的话，则合并到集合中
                        String workday = dayProperties[1];
                        if (daysMap.containsKey(workday)) {
                            daysMap.get(workday).add(worker);
                        } else {
                            // 因为值班日的粒度是一天，而排班的粒度是班次，有可能出现一个人在同一天排有多个班次，所以采用set进行去重
                            daysMap.put(workday, Sets.newHashSet(worker));
                        }
                    }
                }
            }
            // 值班数据字符串对应前端来说是无用的数据，设置为null，减少网络传输
            bean.setDaysStr(null);
            bean.setDays(daysMap);
        }
        return page;
    }

    @Override
    public PageInfo<UserSchedule> findScheduleTablePage(ScheduleParam scheduleParam) {
        scheduleParam.setWeek(TimeUtil.converedPeriodToWeek(scheduleParam.getStartDate(), scheduleParam.getEndDate()));
        List<ScheduleTableListBean> scheduleTableListBeanList = shiftMapper.findScheduleTable(scheduleParam);
        Map<String, List<ScheduleTableListBean>> collect = scheduleTableListBeanList.stream().filter(item -> !StringUtil.isInvalid(item.getDeptName())).collect(Collectors.groupingBy(ScheduleTableListBean::getDeptName));
        List<UserSchedule> result = new LinkedList<>();
        collect.forEach((k, v) -> {
            UserSchedule userSchedule = new UserSchedule();
            userSchedule.setDeptName(k);
            List<Schedule> schedules = new LinkedList<>();
            for (ScheduleTableListBean scheduleTableListBean : v) {
                Schedule schedule = new Schedule();
                schedule.setWorkDay(scheduleTableListBean.getWorkDay());
                schedule.setShiftsName(scheduleTableListBean.getName());
                schedules.add(schedule);
            }
            List<Date> toFull = new LinkedList<>();
            for (int i = 0; i < 7; i++) {
                LocalDate startDateLocalDate = scheduleParam.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(i);
                AtomicBoolean existed = new AtomicBoolean(false);
                schedules.forEach(schedule -> {
                    LocalDate workDayLocalDate = schedule.getWorkDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (startDateLocalDate.isEqual(workDayLocalDate)) {
                        existed.set(true);
                    }
                });
                if (!existed.get()) {
                    toFull.add(Date.from(startDateLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
            }
            toFull.forEach(date -> {
                Schedule schedule = new Schedule();
                schedule.setWorkDay(date);
                schedules.add(schedule);
            });
            Collections.sort(schedules);
            userSchedule.setSchedules(schedules);
            result.add(userSchedule);
        });

        List<Schedule> schedulesForNull = new LinkedList<>();
        scheduleTableListBeanList.stream().filter(scheduleTableListBean -> scheduleTableListBean.getDeptId() == null).forEach(scheduleTableListBean -> {
            Schedule schedule = new Schedule();
            schedule.setWorkDay(scheduleTableListBean.getWorkDay());
            schedule.setShiftsName(scheduleTableListBean.getName());
            schedulesForNull.add(schedule);
        });
        if (!CollectionUtils.isEmpty(schedulesForNull)) {
            List<Date> toFull = new LinkedList<>();
            for (int i = 0; i < 7; i++) {
                LocalDate startDateLocalDate = scheduleParam.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(i);
                AtomicBoolean existed = new AtomicBoolean(false);
                schedulesForNull.forEach(schedule -> {
                    LocalDate workDayLocalDate = schedule.getWorkDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (startDateLocalDate.isEqual(workDayLocalDate)) {
                        existed.set(true);
                    }
                });
                if (!existed.get()) {
                    toFull.add(Date.from(startDateLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
            }
            toFull.forEach(date -> {
                Schedule schedule = new Schedule();
                schedule.setWorkDay(date);
                schedulesForNull.add(schedule);
            });
            Collections.sort(schedulesForNull);

        UserSchedule userScheduleForNull = new UserSchedule();
        userScheduleForNull.setSchedules(schedulesForNull);
        result.add(userScheduleForNull);
        }

        PageInfo<UserSchedule> pageInfo = new PageInfo<>();
        int fromIndex = (scheduleParam.getPage() - 1) * scheduleParam.getRows();
        int toIndex = scheduleParam.getPage() * scheduleParam.getRows();
        if (toIndex > result.size()) {
            toIndex = result.size();
        }
        pageInfo.setList(result.subList(fromIndex, toIndex));
        pageInfo.setTotal(result.size());
        pageInfo.setPageSize(scheduleParam.getRows());
        pageInfo.setPages(result.size() % scheduleParam.getRows() > 0 ? result.size() / scheduleParam.getRows() + 1 : result.size() / scheduleParam.getRows());
        pageInfo.setPageNum(pageInfo.getPages() < scheduleParam.getPage() ? pageInfo.getPages() : scheduleParam.getPage());
        pageInfo.setIsFirstPage(pageInfo.getPageNum() <= 1 ? true : false);
        pageInfo.setPrePage(pageInfo.getPageNum() <= 1 ? pageInfo.getPageNum() : pageInfo.getPageNum() - 1);
        pageInfo.setHasPreviousPage(pageInfo.getPageNum() > 1 ? true : false);
        pageInfo.setHasNextPage(pageInfo.getPageNum() < pageInfo.getPages() ? true : false);
        return pageInfo;
    }


}
