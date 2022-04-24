package com.github.tangyi.exam.service.impl;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.ExcelUtils;
import com.github.tangyi.core.common.util.StringUtils;
import com.github.tangyi.core.common.web.PageInfo;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.page.PageUtils;
import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.exam.api.enums.StudyTaskEnum;
import com.github.tangyi.exam.mapper.ExamStudyTaskMapper;
import com.github.tangyi.exam.service.DeptService;
import com.github.tangyi.exam.service.ExamStudyTaskService;
import com.github.tangyi.example.ExamStudyTaskCourseRelationExample;
import com.github.tangyi.example.ExamStudyTaskCourseUserInfoExample;
import com.github.tangyi.example.ExamStudyTaskExample;
import com.github.tangyi.example.ExamStudyTaskUserInfoExample;
import com.github.tangyi.model.*;
import com.github.tangyi.user.api.feign.UserServiceClient;
import com.github.tangyi.user.api.module.Dept;
import lombok.AllArgsConstructor;
import my.xh.validate.CustomException;
import my.xh.validate.ValidateField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExamStudyTaskServiceImpl implements ExamStudyTaskService {
    private static Logger logger = LoggerFactory.getLogger(ExamStudyTaskServiceImpl.class);

    private CommonDaoService commonDaoService;
    private UserServiceClient userServiceClient;
    private ExamStudyTaskMapper examStudyTaskMapper;
    private DeptService deptService;

    @Override
    public ExamStudyTask get(Long id) {
        return commonDaoService.get(ExamStudyTask.class, id);
    }

    @Override
    public PageResult list(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq) {
        ExamStudyTaskExample examStudyTaskExample = new ExamStudyTaskExample();
        ExamStudyTaskExample.Criteria c = examStudyTaskExample.and().andValid();

        if (StringUtils.isNotBlank(baseReq.getKw())) c.andTaskNameLike(String.format("%%%s%%", baseReq.getKw()));
        if (baseReq.getStartTime() != null) c.andCreateDateGreaterThanOrEqualTo(baseReq.getStartTime());
        if (baseReq.getEndTime() != null) c.andCreateDateLessThanOrEqualTo(baseReq.getEndTime());

        examStudyTaskExample.setOrderByClause(String.format("%s %s", sort, order));
        PageResult query = PageUtils.query(pageNum, pageSize, 10, () -> commonDaoService.selectByExample(examStudyTaskExample));

        return query;
    }

    @CustomException(excptionClazz = CommonException.class)
    @ValidateField(index = 0, filedName = "taskName", minLen = 1, maxLen = 64, notNull = true)
    @ValidateField(index = 0, filedName = "status", intVals = {0, 1}, defalut = "0")
    @ValidateField(index = 0, filedName = "taskDescription", minLen = 1)
    @ValidateField(index = 0, filedName = "isPublic", intVals = {0, 1}, defalut = "0")
    @ValidateField(index = 0, filedName = "major", minLen = 1, defalut = "其它")
    @Override
    public int save(ExamStudyTask task) {
        task.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        int i = 0;
        if (task.isNewRecord()) {
            i = commonDaoService.insert(task);
        } else {
            ExamStudyTask examStudyTask = commonDaoService.get(ExamStudyTask.class, task.getId());
            if (examStudyTask == null) throw new CommonException("学习任务不存在");
            if (examStudyTask.getStatus() == 1) throw new CommonException("已发布的学习任务不得修改。如需修改，请先撤回发布。");
            task.setStatusA(0);//修改后，需重新审核
            i = commonDaoService.update(task);
        }
        return i;
    }

    @Override
    @Transactional
    public int del(Long id) {
        int delete = commonDaoService.delete(ExamStudyTask.class, id);
        //删程课程关联，和 用户关联
        delUserRelation(null, id);
        delCourseRelation(null, id);
        return delete;
    }

    @Override
    @ValidateField(index = 2, notNull = true, strVals = {"status", "statusA"})
    public int check(Long id, Integer status, String field) {
        ExamStudyTask examStudyTask = new ExamStudyTask();
        examStudyTask.setId(id);
        ResponseBean<UserVo> userVoResponseBean = userServiceClient.findUserByIdentifier(SysUtil.getUser(), SysUtil.getTenantCode());

        if (StringUtils.equals("status", field)) {
            ExamStudyTask examStudyTask1 = commonDaoService.get(ExamStudyTask.class, id);
            if (examStudyTask1 == null || examStudyTask1.getStatusA() != 1) throw new CommonException("先审核再发布");

            if (status == 1) {
                ExamStudyTaskCourseRelationExample example = new ExamStudyTaskCourseRelationExample();
                example.and().andValid().andTaskIdEqualTo(id);
                if (commonDaoService.selectCountByExample(example) == 0)
                    throw new CommonException("至少关联一个课程才能发布，请关联课程");
            }
            examStudyTask.setStatus(status);
            examStudyTask.setAuditUserName(userVoResponseBean.getData().getName());
            examStudyTask.setAuditUserId(userVoResponseBean.getData().getUserId());
        } else {
            ExamStudyTask examStudyTask1 = commonDaoService.get(ExamStudyTask.class, id);
            if (examStudyTask1 != null && examStudyTask1.getStatus() == 1)
                throw new CommonException("已发布的学习任务不得修改。如需修改，请先撤回发布。");
            examStudyTask.setStatusA(status);
            examStudyTask.setStatusAUserName(userVoResponseBean.getData().getName());
            examStudyTask.setStatusAUserId(userVoResponseBean.getData().getUserId());
        }

        return commonDaoService.update(examStudyTask);
    }

    @Override
    @Transactional
    @ValidateField(index = 2, minLen = 1, notNull = true, regStr = "(\\d+\\,?)+")
    public int joinCourse(Long id, String courseIds) {
        ExamStudyTask examStudyTask = commonDaoService.get(ExamStudyTask.class, id);
        if (examStudyTask == null) throw new CommonException("学习任务不存在");
        if (examStudyTask.getStatus() == 1) throw new CommonException("已发布的学习任务不得修改。如需修改，请先撤回发布。");
        int result = 0;
        String[] split = courseIds.split(",");
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (StringUtils.isBlank(split[i])) continue;
            ids.add(Long.valueOf(split[i].trim()));
        }
        //先查询是否已关联
        ExamStudyTaskCourseRelationExample example = new ExamStudyTaskCourseRelationExample();
        example.and().andValid().andTaskIdEqualTo(id).andCourseIdIn(ids);
        List<ExamStudyTaskCourseRelation> entitys = commonDaoService.selectByExample(example);
        List<Long> ids2 = entitys.stream().map(ExamStudyTaskCourseRelation::getCourseId).collect(Collectors.toList());

        String user = SysUtil.getUser();
        String sysCode = SysUtil.getSysCode();
        String tenantCode = SysUtil.getTenantCode();
        for (Long courseId : ids) {
            if (ids2.contains(courseId)) continue;
            ExamStudyTaskCourseRelation examStudyTaskCourseRelation = new ExamStudyTaskCourseRelation();
            examStudyTaskCourseRelation.setCourseId(courseId);
            examStudyTaskCourseRelation.setTaskId(id);
            examStudyTaskCourseRelation.setCommonValue(user, sysCode, tenantCode);
            commonDaoService.insert(examStudyTaskCourseRelation);
            result++;
        }
        updateCourseNumAndTotalStudyTimeAndStatus(examStudyTask);

        return result;
    }

    /**
     * 更新课程数 和 总课程时长
     *
     * @param examStudyTask
     */
    private void updateCourseNumAndTotalStudyTimeAndStatus(ExamStudyTask examStudyTask) {
        if (examStudyTask == null || examStudyTask.getDelFlag() == 1) return;
        ExamStudyTaskCourseRelationExample example = new ExamStudyTaskCourseRelationExample();
        example.and().andValid().andTaskIdEqualTo(examStudyTask.getId());
        examStudyTask.setCourseNum(commonDaoService.selectCountByExample(example));

        Integer totalStudyTime = examStudyTaskMapper.sumStudyTime(examStudyTask.getId());
        examStudyTask.setTotalStudyTime(totalStudyTime);

        commonDaoService.update(examStudyTask);
        updateStudyStatus(examStudyTask);
    }

    private void updateCourseNumAndTotalStudyTimeAndStatus(Long taskId) {
        updateCourseNumAndTotalStudyTimeAndStatus(commonDaoService.get(ExamStudyTask.class, taskId));
    }

    //更新用户学习完成状态
    private void updateStudyStatus(ExamStudyTask entity) {
        ExamStudyTaskUserInfoExample taskUserExample = new ExamStudyTaskUserInfoExample();
        taskUserExample.and().andValid().andTaskIdEqualTo(entity.getId());

        List<ExamStudyTaskUserInfo> examStudyTaskUserInfos = commonDaoService.selectByExample(taskUserExample);
        examStudyTaskUserInfos.forEach(examStudyTaskUserInfo -> {

            ExamStudyTaskCourseUserInfoExample examStudyTaskCourseUserInfoExample = new ExamStudyTaskCourseUserInfoExample();
            examStudyTaskCourseUserInfoExample.and().andValid().andTaskIdEqualTo(entity.getId()).andUserIdEqualTo(examStudyTaskUserInfo.getUserId())
                    .andIsFinishEqualTo(1);
            int finishCourseNum = commonDaoService.selectCountByExample(examStudyTaskCourseUserInfoExample);
            if (finishCourseNum == entity.getCourseNum() && entity.getCourseNum() > 0) {
                examStudyTaskUserInfo.setStudyStatus(StudyTaskEnum.StudyStatus.finish.getValue());
            } else {
                examStudyTaskUserInfo.setStudyStatus(StudyTaskEnum.StudyStatus.wait.getValue());
            }
            commonDaoService.update(examStudyTaskUserInfo);
        });
    }

    @Override
    @Transactional
    public int joinUser(Long id, String userIds) {
        ExamStudyTask examStudyTask = commonDaoService.get(ExamStudyTask.class, id);
        if (examStudyTask == null) throw new CommonException("学习任务不存在");
        if (examStudyTask.getStatus() == 1) throw new CommonException("已发布的学习任务不得修改。如需修改，请先撤回发布。");
        if (examStudyTask.getIsPublic() == 1) throw new CommonException("公开的学习任务，无需关联用户");
        int result = 0;
        String[] split = userIds.split(",");
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (StringUtils.isBlank(split[i])) continue;
            ids.add(Long.valueOf(split[i].trim()));
        }
        //先查询是否已关联
        ExamStudyTaskUserInfoExample example = new ExamStudyTaskUserInfoExample();
        example.and().andValid().andTaskIdEqualTo(id).andUserIdIn(ids);
        List<ExamStudyTaskUserInfo> entitys = commonDaoService.selectByExample(example);
        List<Long> ids2 = entitys.stream().map(ExamStudyTaskUserInfo::getUserId).collect(Collectors.toList());

        String user = SysUtil.getUser();
        String sysCode = SysUtil.getSysCode();
        String tenantCode = SysUtil.getTenantCode();
        for (Long userId : ids) {
            if (ids2.contains(userId)) continue;
            ExamStudyTaskUserInfo examStudyTaskUserInfo = new ExamStudyTaskUserInfo();
            examStudyTaskUserInfo.setUserId(userId);
            examStudyTaskUserInfo.setTaskId(id);
            examStudyTaskUserInfo.setCommonValue(user, sysCode, tenantCode);
            commonDaoService.insert(examStudyTaskUserInfo);
            result++;
        }

        return result;
    }

    /**
     * @return void
     * @Author xh
     * @Description 当删除课程时，删除学习任务与课程关联
     * @Date 9:14 2020/10/24
     * @Param [courseId, taskaId]
     **/
    @Override
    public void delCourseRelation(Long courseId, Long taskId) {
        if (CheckUtils.isAllEmpty(courseId, taskId)) throw new CommonException("courseId与taskId 不能同时为空");
        ExamStudyTaskCourseRelationExample example = new ExamStudyTaskCourseRelationExample();
        ExamStudyTaskCourseRelationExample.Criteria c = example.and().andValid();
        if (courseId != null) c.andCourseIdEqualTo(courseId);
        if (taskId != null) c.andTaskIdEqualTo(taskId);
        List<ExamStudyTaskCourseRelation> relations = commonDaoService.selectByExample(example);
        List<Long> taskIds = relations.stream().map(ExamStudyTaskCourseRelation::getTaskId).distinct().collect(Collectors.toList());
        ExamStudyTaskCourseRelation examStudyTaskCourseRelation = new ExamStudyTaskCourseRelation();
        examStudyTaskCourseRelation.setDelFlag(1);
        commonDaoService.updateByExample(examStudyTaskCourseRelation, example);

        //学时的处理
        if (courseId != null && relations.size() > 0) {
            ExamStudyTaskCourseUserInfoExample examStudyTaskCourseUserInfoExample = new ExamStudyTaskCourseUserInfoExample();
            examStudyTaskCourseUserInfoExample.and().andValid().andTaskIdIn(taskIds).andExamCourseIdEqualTo(courseId).andIsFinishEqualTo(1);
            List<ExamStudyTaskCourseUserInfo> examStudyTaskCourseUserInfos = commonDaoService.selectByExample(examStudyTaskCourseUserInfoExample);
            examStudyTaskCourseUserInfos.forEach(item -> {
                ExamStudyTaskUserInfoExample examStudyTaskUserInfoExample = new ExamStudyTaskUserInfoExample();
                examStudyTaskUserInfoExample.and().andValid().andTaskIdEqualTo(item.getTaskId()).andUserIdEqualTo(item.getUserId());
                ExamStudyTaskUserInfo examStudyTaskUserInfo = commonDaoService.selectOneByExample(examStudyTaskUserInfoExample);
                if (examStudyTaskUserInfo != null) {
                    examStudyTaskUserInfo.setStudyTime(examStudyTaskUserInfo.getStudyTime() - item.getStudyTime());
                    commonDaoService.update(examStudyTaskUserInfo);
                }
                commonDaoService.delete(item);
            });
        }

        for (Long id : taskIds) {
            updateCourseNumAndTotalStudyTimeAndStatus(id);
        }
    }

    /**
     * @return void
     * @Author xh
     * @Description 当删除用户时，删除学习任务与学员关联
     * @Date 9:15 2020/10/24
     * @Param [userId]
     **/
    @Override
    public void delUserRelation(Long userId, Long taskId) {
        if (CheckUtils.isAllEmpty(userId, taskId)) throw new CommonException("userId与taskId 不能同时为空");
        ExamStudyTaskUserInfoExample example = new ExamStudyTaskUserInfoExample();
        ExamStudyTaskUserInfoExample.Criteria c = example.and().andValid();
        if (userId != null) c.andUserIdEqualTo(userId);
        if (taskId != null) c.andTaskIdEqualTo(taskId);

        ExamStudyTaskUserInfo examStudyTaskUserInfo = new ExamStudyTaskUserInfo();
        examStudyTaskUserInfo.setDelFlag(1);
        commonDaoService.updateByExample(examStudyTaskUserInfo, example);

        ExamStudyTaskCourseUserInfoExample example2 = new ExamStudyTaskCourseUserInfoExample();
        ExamStudyTaskCourseUserInfoExample.Criteria c2 = example2.and().andValid();
        if (userId != null) c2.andUserIdEqualTo(userId);
        if (taskId != null) c2.andTaskIdEqualTo(taskId);

        ExamStudyTaskCourseUserInfo examStudyTaskCourseUserInfo = new ExamStudyTaskCourseUserInfo();
        examStudyTaskCourseUserInfo.setDelFlag(1);
        commonDaoService.updateByExample(examStudyTaskCourseUserInfo, example2);
    }

    @Override
    public int cancelJoinCourse(Long id, String courseIds) {
        ExamStudyTask examStudyTask = commonDaoService.get(ExamStudyTask.class, id);
        if (examStudyTask == null) throw new CommonException("学习任务不存在");
        if (examStudyTask.getStatus() == 1) throw new CommonException("已发布的学习任务不得修改。如需修改，请先撤回发布。");
        int result = 0;
        String[] split = courseIds.split(",");
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (StringUtils.isBlank(split[i])) continue;
            ids.add(Long.valueOf(split[i].trim()));
        }
        for (Long courseId : ids) {
            delCourseRelation(courseId, id);
            result++;
        }
        updateCourseNumAndTotalStudyTimeAndStatus(examStudyTask);
        return result;
    }

    @Override
    public int cancelJoinUser(Long id, String userIds) {
        ExamStudyTask examStudyTask = commonDaoService.get(ExamStudyTask.class, id);
        if (examStudyTask == null) throw new CommonException("学习任务不存在");
        if (examStudyTask.getStatus() == 1) throw new CommonException("已发布的学习任务不得修改。如需修改，请先撤回发布。");
        int result = 0;
        String[] split = userIds.split(",");
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (StringUtils.isBlank(split[i])) continue;
            ids.add(Long.valueOf(split[i].trim()));
        }
        for (Long userId : ids) {
            delUserRelation(userId, id);
            result++;
        }
        return result;
    }

    @Override
    public PageResult myTaskList(Integer pageNum, Integer pageSize, String sort, String order, String type) {

        ResponseBean<UserVo> userVoResponseBean = getCurrentUser();

        ExamStudyTaskUserInfoExample examStudyTaskUserInfoExample = new ExamStudyTaskUserInfoExample();
        ExamStudyTaskUserInfoExample.Criteria c = examStudyTaskUserInfoExample.and().andValid()
                .andUserIdEqualTo(userVoResponseBean.getData().getUserId());
        if (StringUtils.equals(type, StudyTaskEnum.MyTaskListQueryType.current.name())) {
            c.andStudyStatusNotEqualTo(StudyTaskEnum.StudyStatus.finish.getValue());
        }
        examStudyTaskUserInfoExample.setOrderByClause(String.format("%s %s", sort, order));
        return PageUtils.query(pageNum, pageSize, 10, () -> examStudyTaskMapper.getMyTasksByExample(examStudyTaskUserInfoExample));
    }

    private ResponseBean<UserVo> getCurrentUser() {
        String user = SysUtil.getUser();
        String tenantCode = SysUtil.getTenantCode();
        return userServiceClient.findUserByIdentifier(user, tenantCode);
    }

    @Override
    public PageResult myTaskCourses(Integer pageNum, Integer pageSize, String sort, String order, Long taskId) {

        String user = SysUtil.getUser();
        //如果user为null或者为“anonymousUser” ，表示为匿名用户
        if(!org.apache.commons.lang.StringUtils.equalsIgnoreCase("anonymousUser",user)) {

            //登录用户逻辑
            ResponseBean<UserVo> userVoResponseBean = getCurrentUser();

            PageResult query = PageUtils.query(pageNum, pageSize, 10, () -> examStudyTaskMapper.getMyTaskCourses(taskId,
                    userVoResponseBean.getData().getUserId(), sort, order));

            return query;
        }else {

            //匿名用户逻辑
            PageResult query = PageUtils.query(pageNum, pageSize, 10, () -> examStudyTaskMapper.getMyTaskCourses(taskId,null, sort, order));

            return query;
        }
    }

    @Override
    public Map<String, Object> myStatistics() {
        ResponseBean<UserVo> userVoResponseBean = getCurrentUser();
        return examStudyTaskMapper.myStatistics(userVoResponseBean.getData().getUserId());
    }

    @Override
    public void export(Long id, HttpServletResponse response) {
        Map<String, Object> data = new HashMap<String, Object>();
        BaseReq baseReq = new BaseReq();

        List<Map<String, Object>> dataList = examStudyTaskMapper.getUserList(baseReq, true, id, CommonConstant.PAGE_SORT_DEFAULT, CommonConstant.PAGE_ORDER_DEFAULT);

        //对于每一条数据
        for (Map<String, Object> map : dataList) {
            String deptName = (String) map.get("deptName");

            //如果deptName为空，证明此用户为统一认证用户，需要通过中间表进行查询部门信息
//            if (!StringUtils.isNotEmpty(deptName)) {
            Long userId = (Long) map.get("id"); //获取用户id
            List<Dept> list = deptService.getListByUserId(userId); //根据id获取部门集合

            //构建部门信息字符串，多个部门中间加空格
            StringBuilder builder;
            if (deptName!=null){
                builder= new StringBuilder(deptName);
            }else {
                builder= new StringBuilder();
            }
            for (Dept dept : list) {
                String deptDeptName = dept.getDeptName();
                builder.append("  "+deptDeptName);
            }
            String deptString = builder.toString().trim();

            //放入map集合
            map.put("deptName", deptString);
//            }
        }

        ExamStudyTask examStudyTask = commonDaoService.get(ExamStudyTask.class, id);
        data.put("dataList", dataList);
        data.put("examStudyTask", examStudyTask);

        String filename = "学习任务关联学员-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String template = "task_user_template";
        ExcelUtils.export(data, filename, template, response);
    }

    @Override
    public List<Map<String, Object>> getCourseList(Long id, Boolean join, BaseReq baseReq) {
        return examStudyTaskMapper.getCourseList(id, join, baseReq);
    }

    @Override
    public PageResult getUserList(Integer pageNum, Integer pageSize, String sort, String order, Long id, Boolean join, BaseReq baseReq) {
        return PageUtils.query(pageNum, pageSize, 10, () -> examStudyTaskMapper.getUserList(baseReq, join, id, sort, order));
    }

    @Override
    public Object myTaskWithCourseList(Integer pageNum, Integer pageSize, String sort, String order, String major) {
        ArrayList<String> majors = new ArrayList<>();

        //如果没有传入专业分类，就先查询我的任务的所有专业分类
        ResponseBean<UserVo> currentUser = getCurrentUser();
        Long userId = currentUser.getData().getUserId();
        if (StringUtils.isEmpty(major)) {
            List<String> majorList = examStudyTaskMapper.getMyTaskMajorList(userId);
            majors.addAll(majorList);
        } else {
            majors.add(major);
        }

        Map<String, PageResult> result = majors.stream().collect(Collectors.toMap(item -> item, item -> {
            PageResult taskPage = PageUtils.query(pageNum, pageSize, 1, () -> examStudyTaskMapper.getTaskList(sort, order, userId, major));

            List<Map<String, Object>> rows = (List<Map<String, Object>>) taskPage.getRows();
            rows.forEach(item2 -> {
                Long taskId = Long.valueOf(item2.get("id").toString());
                PageResult pageResult = myTaskCourses(1, 100000, sort, order, taskId);

                item2.put("courses", pageResult.getRows());
            });
            return taskPage;
        }));

        return result;
    }

    /**
     * 获取公开学习任务
     * @return
     */
    @Override
    public Object publicTaskList(Integer pageNum, Integer pageSize, String sort, String order, String major){
        ArrayList<String> majors = new ArrayList<>();

        //如果没有传入专业类型，先查询所有专业类型，放入集合中
        if (StringUtils.isEmpty(major)) {
            List<String> majorList = examStudyTaskMapper.getMajorList();
            majors.addAll(majorList);
        } else {
            majors.add(major);
        }

        //专业作为Key，按专业分页查询的结果作为Value
        HashMap<String, PageResult> result = new HashMap<>();
        for (int i = 0; i < majors.size(); i++) {

            //根据专业查询公开的学习任务
            int finalI = i;
            PageResult query = PageUtils.query(pageNum, pageSize, 9, () -> examStudyTaskMapper.getPublicTaskList(majors.get(finalI), sort, order));
            List<Map<String, Object>> taskList = (List<Map<String, Object>>)query.getRows();

            //根据学习任务和专业查询课程
            for (int j = 0; j < taskList.size(); j++) {
                Map<String, Object> item = taskList.get(j);
                Long taskId = Long.valueOf(item.get("id").toString());
                List<Map<String, Object>> courses = examStudyTaskMapper.getCourseWithMajorAndTaskId(majors.get(i), taskId);
                item.put("courses",courses);
            }
            result.put(majors.get(i),query);
        }
        return result;
    }

    @Override
    public PageResult studyRecordList(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq) {
        return PageUtils.query(pageNum, pageSize, 10, () -> examStudyTaskMapper.studyRecordList(baseReq, sort, order));
    }

    @Override
    public Map<String, Object> myCourse(Long taskId, Long courseId) {

        String user = SysUtil.getUser();
        //如果user为null或者为“anonymousUser” ，表示为匿名用户
        if(!org.apache.commons.lang.StringUtils.equalsIgnoreCase("anonymousUser",user)) {

            //已登录用户查询课程详情
            ResponseBean<UserVo> userVoResponseBean = userServiceClient.findUserByIdentifier(SysUtil.getUser(), SysUtil.getTenantCode());
            return examStudyTaskMapper.myCourse(taskId, courseId, userVoResponseBean.getData().getUserId());
        }{
            //匿名用户查询课程详情
            return examStudyTaskMapper.myCourse(taskId, courseId, null);
        }
        
    }

    @Override
    public PageResult statistics(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq) {
        return PageUtils.query(pageNum, pageSize, 10, () -> examStudyTaskMapper.statistics(baseReq, sort, order));
    }

    @Override
    public PageResult userLoginStatistics(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq) {
        return PageUtils.query(pageNum, pageSize, 10, () -> examStudyTaskMapper.userLoginStatistics(baseReq, sort, order));
    }

    @Override
    public PageResult accessTimeStatistics(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq) {
        return PageUtils.query(pageNum, pageSize, 10, () -> examStudyTaskMapper.accessTimeStatistics(baseReq, sort, order));
    }

    @Override
    public PageResult accessNumStatistics(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq) {
        return PageUtils.query(pageNum, pageSize, 10, () -> examStudyTaskMapper.accessNumStatistics(baseReq, sort, order));
    }
}
