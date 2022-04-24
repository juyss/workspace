package com.icepoint.framework.icepoint.web.crewschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolGroup;
import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolSchedule;
import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolShifts;
import com.icepoint.framework.icepoint.web.crewschedule.entity.UserWithSchedule;
import com.icepoint.framework.icepoint.web.crewschedule.excel.ScheduleToday;
import com.icepoint.framework.icepoint.web.crewschedule.mapper.ToolGroupMapper;
import com.icepoint.framework.icepoint.web.crewschedule.mapper.ToolScheduleMapper;
import com.icepoint.framework.icepoint.web.crewschedule.service.ToolScheduleService;
import com.icepoint.framework.icepoint.web.crewschedule.service.ToolShiftsService;
import com.icepoint.framework.web.security.dao.UserMapper;
import com.icepoint.framework.web.security.dao.UserRepository;
import com.icepoint.framework.web.security.entity.QUser;
import com.icepoint.framework.web.security.entity.Role;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.security.service.DepartmentService;
import com.mysema.commons.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName ToolScheduleServiceImpl
 * @description
 * @since 2021-07-28 13:53
 */
@Service
@Validated
public class ToolScheduleServiceImpl implements ToolScheduleService {

    @Autowired
    private ToolScheduleMapper mapper;

    @Autowired
    private ToolShiftsService toolShiftsService;

    @Autowired
    private ToolGroupMapper toolGroupMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentService departmentService;

    /**
     * @param toolSchedule 数据实体
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer insert(@Valid ToolSchedule toolSchedule) {
        return mapper.insert(toolSchedule);
    }

    /**
     * @param toolSchedule 数据实体
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer update(@Valid ToolSchedule toolSchedule) {
        Assert.isTrue(ObjectUtils.isEmpty(toolSchedule.getId()), "id没有值");
        return mapper.update(toolSchedule);
    }

    /**
     * @param ids id集合
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer delete(List<Long> ids) {
        Assert.isTrue(ObjectUtils.isEmpty(ids), "id集合为空");
        return mapper.deleteInBatchIds(ids);
    }

    /**
     * 查询排班记录
     *
     * @param deptId   部门
     * @param roleId   角色
     * @param userName 用户名
     * @param pageable 分页数据
     * @param start    开始时间 不能为空
     * @param end      结束时间 不能为空
     * @return Page<ToolSchedule>
     * @author Juyss
     */
    @Override
    public Page<Map<String, Object>> findAll(Long deptId, Long roleId, String userName, Pageable pageable, @NotNull Date start, @NotNull Date end) {

        //查询用户集合
        Page<User> userPage = userRepository.findAll(pageable);

        //获取总数
        long totalElements = userPage.getTotalElements();

        //获取分页结果
        List<User> userList = userPage.toList();

        //过滤用户名查询
        if (userName != null) {
            userList = userList.stream().filter(user -> user.getUsername().contains(userName)).collect(Collectors.toList());
        }

        //过滤指定部门的用户
        if (deptId != null) {
            userList = userList.stream().filter(user -> user.getDeptId().equals(deptId)).collect(Collectors.toList());
        }

        //过滤指定角色的用户
        if (roleId != null) {
            userList = userList.stream().filter(user -> {
                assert user.getRoles() != null;
                List<Role> collect = user.getRoles().stream().filter(role -> {
                    assert role.getId() != null;
                    return role.getId().equals(roleId);
                }).collect(Collectors.toList());
                return ObjectUtils.isEmpty(collect);
            }).collect(Collectors.toList());
        }

        //如果不存在符合条件的用户，直接返回
        if (ObjectUtils.isEmpty(userList)) {
            return null;
        }

        //创建结果集
        ArrayList<Map<String, Object>> result = new ArrayList<>();

        //为每一个用户设置排班信息
        for (User user : userList) {
            Long userId = user.getId();
            //获取部门名称
            String deptName = departmentService.findById(user.getDeptId()).getName();
            user.setDeptName(deptName);
            ToolSchedule toolSchedule = findByUserIdAndStartAndEnd(userId, start, end);
            Map<String, Object> userMap = BeanUtils.toMap(user, false);
            userMap.put("toolSchedule", toolSchedule);
            result.add(userMap);
        }

        //重新构建分页结果
        PageImpl<Map<String, Object>> page = new PageImpl<>(result, pageable, totalElements);

        return page;
    }

    /**
     * @param id 主键
     * @return ToolSchedule
     * @author Juyss
     */
    @Override
    public ToolSchedule findOne(Long id) {
        return mapper.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("班次数据不存在，id：%s", id)));
    }

    /**
     * 查询分组
     *
     * @param deptId   部门id
     * @param roleId   角色id
     * @param userName 用户名
     * @return List<ToolGroup>
     * @author Juyss
     */
    @Override
    public List<ToolGroup> findGroup(Long deptId, Long roleId, String userName) {

        List<ToolGroup> resultList;

        //传入了用户名优先根据用户名查询
        if (!ObjectUtils.isEmpty(userName)) {
            QUser qUser = QUser.user;

            //查询 username like
            List<User> userList = userRepository.findAll(qUser.username.likeIgnoreCase(userName));

            //如果没有符合条件的用户，直接返回
            if (ObjectUtils.isEmpty(userList)) {
                return null;
            }
            // 1.如果部门也作为查询条件
            if (deptId != null) {

                //过滤符合条件的用户，此时所有用户的deptId都一样
                userList = userList.stream().filter(a -> deptId.equals(a.getDeptId())).collect(Collectors.toList());

                //如果没有符合条件的用户，直接返回
                if (ObjectUtils.isEmpty(userList)) {
                    return null;
                }
            }

            // 2.如果角色也作为查询条件
            //创建角色id集合
            HashSet<Long> tempRoleIdSet = new HashSet<>();
            if (roleId != null) {

                //新建一个临时集合
                ArrayList<User> tempUserList = new ArrayList<>();

                //遍历用户
                for (User user : userList) {

                    //获取一个用户的角色id集合
                    Set<Long> collect = user.getRoles().stream().map(Role::getId).collect(Collectors.toSet());

                    //如果这个集合包含有作为条件的roleId，说明这个用户符合条件
                    if (collect.contains(roleId)) {
                        //添加符合条件的用户
                        tempUserList.add(user);
                        //添加符合条件的角色id集合
                        tempRoleIdSet.addAll(collect);
                    }
                }

                //赋值符合条件的用户数据
                userList = tempUserList;

                //如果没有符合条件的用户，直接返回
                if (ObjectUtils.isEmpty(userList)) {
                    return null;
                }
            }

            LambdaQueryWrapper<ToolGroup> wrapper = Wrappers.lambdaQuery(ToolGroup.class);

            //获取最终符合条件的用户的部门id集合
            Set<Long> deptIdSet = userList.stream().map(User::getDeptId).collect(Collectors.toSet());

            //作为查询条件
            if (!ObjectUtils.isEmpty(deptIdSet)) {
                wrapper.in(ToolGroup::getDeptId, deptIdSet);
            }

            //获取最终符合条件的用户的角色id集合
            Set<Long> roleIdSet = tempRoleIdSet;

            //作为查询条件
            if (!ObjectUtils.isEmpty(roleIdSet)) {
                wrapper.in(ToolGroup::getRoleId, roleIdSet);
            }

            //查询并返回
            resultList = toolGroupMapper.findAll(wrapper);
            return resultList;
        }

        //如果没传入用户名，根据部门或角色id查询
        LambdaQueryWrapper<ToolGroup> wrapper = Wrappers.lambdaQuery(ToolGroup.class);

        //作为查询条件
        if (!ObjectUtils.isEmpty(deptId)) {
            wrapper.eq(ToolGroup::getDeptId, deptId);
        }

        //作为查询条件
        if (!ObjectUtils.isEmpty(roleId)) {
            wrapper.eq(ToolGroup::getRoleId, roleId);
        }

        //查询并返回
        resultList = toolGroupMapper.findAll(wrapper);
        return resultList;
    }

    /**
     * 根据起止时间查询排班表
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return List<Map < String, Object>>
     * @author Juyss
     */
    @Override
    public List<Map<String, Object>> findCrewSchedule(Date start, Date end) {

        LambdaQueryWrapper<ToolSchedule> wrapper = Wrappers.lambdaQuery(ToolSchedule.class);
        wrapper.between(ToolSchedule::getWorkDay, start, end);

        //查询排班数据
        List<ToolSchedule> scheduleList = mapper.findAll(wrapper);

        if (ObjectUtils.isEmpty(scheduleList)) {
            return null;
        }

        ArrayList<Map<String, Object>> resultList = new ArrayList<>();

        for (ToolSchedule toolSchedule : scheduleList) {
            //查询排班的用户信息
            User user = userRepository.findById(toolSchedule.getUserId()).orElse(null);
            if (!ObjectUtils.isEmpty(user)) {
                //获取部门名称
                String deptName = departmentService.findById(user.getDeptId()).getName();
                user.setDeptName(deptName);
                //user对象转map
                Map<String, Object> userMap = BeanUtils.toMap(user, true);
                //设置排班信息
                userMap.put("toolSchedule", toolSchedule);
                //添加到结果集
                resultList.add(userMap);
            }
        }

        return resultList;
    }

    /**
     * 根据起止时间查询导出Excel所需数据
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return List<ToolSchedule>
     * @author Juyss
     */
    @Override
    public List<ScheduleToday> getExportExcelDataList(Date start, Date end) {
        LambdaQueryWrapper<ToolSchedule> wrapper = Wrappers.lambdaQuery(ToolSchedule.class);
        wrapper.between(ToolSchedule::getWorkDay, start, end);

        //查询排班数据
        List<ToolSchedule> scheduleList = mapper.findAll(wrapper);

        if (ObjectUtils.isEmpty(scheduleList)) {
            return null;
        }

        List<UserWithSchedule> resultList = new ArrayList<>();

        for (ToolSchedule toolSchedule : scheduleList) {
            //查询排班的用户信息
            User user = userRepository.findById(toolSchedule.getUserId()).orElse(null);
            UserWithSchedule userWithSchedule = new UserWithSchedule();

            Assert.isTrue(!ObjectUtils.isEmpty(user),String.format("对应id的用户不存在,ID: %s",toolSchedule.getUserId()));

            BeanUtils.copyProperties(user, userWithSchedule, true);

            if (!ObjectUtils.isEmpty(userWithSchedule)) {
                //获取部门名称
                String deptName = departmentService.findById(userWithSchedule.getDeptId()).getName();
                userWithSchedule.setDeptName(deptName);
                //设置排班信息
                userWithSchedule.setToolSchedule(toolSchedule);
                //添加到结果集
                resultList.add(userWithSchedule);
            }
        }
        //构建Excel数据集合
        ArrayList<ScheduleToday> scheduleTodayList = new ArrayList<>();

        for (UserWithSchedule user : resultList) {
            ScheduleToday scheduleToday = new ScheduleToday();

            //设置部门名称
            scheduleToday.setDeptName(user.getDeptName());
            Date workDay = user.getToolSchedule().getWorkDay();
            //获取排班日期是这周第几天
            LocalDate localDate = workDay.toLocalDate();
            int dayOfWeek = localDate.getDayOfWeek().getValue();
            //在对应周把用户名设置进去
            switch (dayOfWeek) {
                case 1:
                    scheduleToday.setMonday(user.getUsername());
                    break;
                case 2:
                    scheduleToday.setTuesday(user.getUsername());
                    break;
                case 3:
                    scheduleToday.setWednesday(user.getUsername());
                    break;
                case 4:
                    scheduleToday.setThursday(user.getUsername());
                    break;
                case 5:
                    scheduleToday.setFriday(user.getUsername());
                    break;
                case 6:
                    scheduleToday.setSaturday(user.getUsername());
                    break;
                case 7:
                    scheduleToday.setSunday(user.getUsername());
                    break;
            }
            scheduleTodayList.add(scheduleToday);
        }


        return scheduleTodayList;
    }

    /**
     * 获取指定时间段内的每天的日期
     *
     * @param start 开始时间 1997-05-11
     * @param end 结束时间 1997-05-11
     * @return List<String>
     * @author Juyss
     */
    @Override
    public List<String> getBetweenDate(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> list.add(f.toString()));
        return list;
    }

    /**
     * 获取Excel动态表头数据
     * @author Juyss
     * @param dayList 日期字符串
     * @return List<List<String>>
     */
    @Override
    public List<List<String>> getExcelHead(List<String> dayList){
        List<List<String>> head = Lists.newArrayList();
        head.add(Lists.newArrayList("部门"));

        for (int i = 0; i < dayList.size(); i++) {
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
            head.add(Lists.newArrayList(dayList.get(i) + "\r\n" + weekName));
        }

        return head;

    }

    /**
     * 根据用户ID和起止时间，查询排班记录
     *
     * @param userId 用户id
     * @param start  开始时间
     * @param end    结束时间
     * @return ToolSchedule
     * @author Juyss
     */
    public ToolSchedule findByUserIdAndStartAndEnd(@NotNull Long userId, @NotNull Date start, @NotNull Date end) {
        LambdaQueryWrapper<ToolSchedule> wrapper = Wrappers.lambdaQuery(ToolSchedule.class);
        // user_id
        wrapper.eq(ToolSchedule::getUserId, userId);
        // 条件查询 between and
        wrapper.between(ToolSchedule::getWorkDay, start, end);

        Optional<ToolSchedule> optional = mapper.findOne(wrapper);

        //如果有数据
        if (optional.isPresent()) {
            ToolSchedule toolSchedule = optional.get();
            Long toolShiftsId = toolSchedule.getToolShiftsId();
            //如果班次id不为空
            if (toolShiftsId != null) {
                ToolShifts toolShifts = toolShiftsService.findOne(toolShiftsId);
                //设置班次信息
                toolSchedule.setToolShifts(toolShifts);
                return toolSchedule;
            }
        }

        return null;
    }

}
