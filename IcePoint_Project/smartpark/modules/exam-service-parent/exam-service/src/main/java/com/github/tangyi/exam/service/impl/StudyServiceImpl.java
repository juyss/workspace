package com.github.tangyi.exam.service.impl;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.util.JsonUtils;
import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.exam.api.constants.StudyConstant;
import com.github.tangyi.exam.api.dto.req.StudyReq;
import com.github.tangyi.exam.api.dto.res.StudyDoingRes;
import com.github.tangyi.exam.api.dto.res.StudyOverRes;
import com.github.tangyi.exam.api.dto.res.StudyStartRes;
import com.github.tangyi.exam.api.enums.StudyTaskEnum;
import com.github.tangyi.exam.config.StudyConfig;
import com.github.tangyi.exam.model.bo.StudyBo;
import com.github.tangyi.exam.service.StudyService;
import com.github.tangyi.example.ExamStudyTaskCourseUserInfoExample;
import com.github.tangyi.example.ExamStudyTaskUserInfoExample;
import com.github.tangyi.model.ExamCourse;
import com.github.tangyi.model.ExamStudyTask;
import com.github.tangyi.model.ExamStudyTaskCourseUserInfo;
import com.github.tangyi.model.ExamStudyTaskUserInfo;
import com.github.tangyi.user.api.feign.UserServiceClient;
import lombok.AllArgsConstructor;
import my.xh.validate.CustomException;
import my.xh.validate.ValidateField;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class StudyServiceImpl implements StudyService {

    private CommonDaoService commonDaoService;
    private UserServiceClient userServiceClient;
    private RedisTemplate<String, String> redisTemplate;
    private StudyConfig studyConfig;


    @Override
    @CustomException(excptionClazz = CommonException.class)
    @ValidateField(index = 0, filedName = "courseId", notNull = true)
    @ValidateField(index = 0, filedName = "currentTime", notNull = true)
    public StudyStartRes start(StudyReq studyReq,String anonUserId) {

        //获取当前用户ID
        String user = SysUtil.getUser();

        Long userId = null;
        //如果user为null或者为"anonymousUser" ，表示用户未登录
        if(!StringUtils.equalsIgnoreCase("anonymousUser",user)){

            //登录用户逻辑
            ResponseBean<UserVo> userByIdentifier = userServiceClient.findUserByIdentifier(SysUtil.getUser(), SysUtil.getTenantCode());
            userId = userByIdentifier.getData().getUserId();
        }else{

            //匿名用户逻辑
            userId = Long.valueOf(anonUserId);
        }

        //检查课程是否存在
        ExamCourse examCourse = commonDaoService.get(ExamCourse.class, studyReq.getCourseId());
        if (examCourse == null || examCourse.getDelFlag() == 1) throw new CommonException("课程不存在");
        //检查任务
        ExamStudyTask examStudyTask = commonDaoService.get(ExamStudyTask.class, studyReq.getTaskId());
        if (examStudyTask == null || examStudyTask.getDelFlag() == 1) throw new CommonException("任务不存在");
        StudyBo studyBo = null;

        //查数据库，是否已有学习记录
        ExamStudyTaskCourseUserInfoExample example = new ExamStudyTaskCourseUserInfoExample();
        example.and().andValid().andUserIdEqualTo(userId).andExamCourseIdEqualTo(examCourse.getId()).andTaskIdEqualTo(examStudyTask.getId());
        ExamStudyTaskCourseUserInfo examStudyTaskCourseUserInfo = commonDaoService.selectOneByExample(example);
        if (examStudyTaskCourseUserInfo == null) {
            //没有记录
            examStudyTaskCourseUserInfo = new ExamStudyTaskCourseUserInfo();
            examStudyTaskCourseUserInfo.setExamCourseId(examCourse.getId());
            examStudyTaskCourseUserInfo.setTaskId(examStudyTask.getId());
            examStudyTaskCourseUserInfo.setUserId(userId);
            examStudyTaskCourseUserInfo.setStudyTime(0);
            examStudyTaskCourseUserInfo.setIsFinish(0);
            examStudyTaskCourseUserInfo.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
            commonDaoService.insert(examStudyTaskCourseUserInfo);
        } else {
            if (examStudyTaskCourseUserInfo.getIsFinish() == 1) {
                return new StudyStartRes(false, 0L, 0, 0);
            }
        }
        studyBo = new StudyBo(studyConfig.getDuration(), new Date().getTime(), studyReq.getCurrentTime()
                , examCourse.getStudyTime(), examCourse.getCourseTime(), examStudyTaskCourseUserInfo);
        redisTemplate.opsForValue().set(StudyConstant.getRedisStudyKey(userId), JsonUtils.toString(studyBo), 10 * 60 * 60, TimeUnit.SECONDS);
        return new StudyStartRes(true, studyConfig.getDuration(), studyBo.getCourseTime(), studyBo.getStudyTime());
    }

    @Override
    public StudyDoingRes doing(StudyReq studyReq, String anonUserId) {

        //获取当前用户ID
        String user = SysUtil.getUser();

        Long userId = null;
        //如果user为null或者为"anonymousUser" ，表示用户未登录
        if(!StringUtils.equalsIgnoreCase("anonymousUser",user)){

            //登录用户逻辑
            ResponseBean<UserVo> userByIdentifier = userServiceClient.findUserByIdentifier(SysUtil.getUser(), SysUtil.getTenantCode());
            userId = userByIdentifier.getData().getUserId();
        }else{

            //匿名用户逻辑
            userId = Long.valueOf(anonUserId);
        }

        //从redis中查，是否正在学习进行中
        String str = redisTemplate.opsForValue().get(StudyConstant.getRedisStudyKey(userId));
        StudyBo studyBo = null;
        if (str != null) {
            studyBo = JsonUtils.toObject(str, StudyBo.class);
            if (studyBo != null && studyBo.getTaskId().equals(studyReq.getTaskId())
                    && studyBo.getExamCourseId().equals(studyReq.getCourseId())) {
                //正在进行中 ok
            } else {
                throw new CommonException("课程学习没有开始");
            }
        }
        //检查课程是否存在
        ExamCourse examCourse = commonDaoService.get(ExamCourse.class, studyReq.getCourseId());
        if (examCourse == null || examCourse.getDelFlag() == 1) throw new CommonException("课程不存在");
        //检查任务
        ExamStudyTask examStudyTask = commonDaoService.get(ExamStudyTask.class, studyReq.getTaskId());
        if (examStudyTask == null || examStudyTask.getDelFlag() == 1) throw new CommonException("任务不存在");

        boolean rational = studyBo.isRational(studyReq.getCurrentTime());
        boolean over = studyBo.isOver();
        ExamStudyTaskCourseUserInfo entity = studyBo.getEntity();
        commonDaoService.update(entity);//同步到数据库
        redisTemplate.opsForValue().set(StudyConstant.getRedisStudyKey(userId), JsonUtils.toString(studyBo), 10 * 60 * 60, TimeUnit.SECONDS);
        return new StudyDoingRes(rational, over, studyBo.getCourseTime(), studyBo.getStudyTime());
    }

    @Override
    public StudyOverRes finish(StudyReq studyReq, String anonUserId) {

        //获取当前用户ID
        String user = SysUtil.getUser();

        Long userId = null;
        //如果user为null或者为"anonymousUser" ，表示用户未登录
        if(!StringUtils.equalsIgnoreCase("anonymousUser",user)){

            //登录用户逻辑
            ResponseBean<UserVo> userByIdentifier = userServiceClient.findUserByIdentifier(SysUtil.getUser(), SysUtil.getTenantCode());
            userId = userByIdentifier.getData().getUserId();
        }else{

            //匿名用户逻辑
            userId = Long.valueOf(anonUserId);
        }

        //从redis中查，是否正在学习进行中
        String str = redisTemplate.opsForValue().get(StudyConstant.getRedisStudyKey(userId));
        StudyBo studyBo = null;
        if (str != null) {
            studyBo = JsonUtils.toObject(str, StudyBo.class);
            if (studyBo != null && studyBo.getTaskId().equals(studyReq.getTaskId())
                    && studyBo.getExamCourseId().equals(studyReq.getCourseId())) {
                //正在进行中 ok
            } else {
                throw new CommonException("课程学习没有开始");
            }
        }
        boolean over = studyBo.isOver();
        if (over) {
            ExamStudyTaskCourseUserInfo entity = studyBo.getEntity();
            entity.setIsFinish(1);
            commonDaoService.update(entity);
            updateUserStudyTimeAndCourseStudyStatus(entity);
            return new StudyOverRes(over, "已完成学习");
        }
        return new StudyOverRes(over, "未达到学时");
    }

    private void updateUserStudyTimeAndCourseStudyStatus(ExamStudyTaskCourseUserInfo entity) {
        ExamStudyTaskUserInfoExample taskUserExample = new ExamStudyTaskUserInfoExample();
        taskUserExample.and().andValid().andUserIdEqualTo(entity.getUserId()).andTaskIdEqualTo(entity.getTaskId());

        ExamStudyTaskUserInfo examStudyTaskUserInfo = commonDaoService.selectOneByExample(taskUserExample);
        //公开课程，需要创建这个对象
        if(examStudyTaskUserInfo == null) {
            examStudyTaskUserInfo = new ExamStudyTaskUserInfo();
            examStudyTaskUserInfo.setUserId(entity.getUserId());
            examStudyTaskUserInfo.setTaskId(entity.getTaskId());
            examStudyTaskUserInfo.setCommonValue(entity.getCreator(),entity.getApplicationCode(),entity.getTenantCode());
            examStudyTaskUserInfo.setStudyStatus(StudyTaskEnum.StudyStatus.wait.getValue());
            commonDaoService.insert(examStudyTaskUserInfo);
        }
        examStudyTaskUserInfo.setStudyTime((examStudyTaskUserInfo.getStudyTime() == null ? 0 : examStudyTaskUserInfo.getStudyTime()) + entity.getStudyTime());

        ExamStudyTask examStudyTask = commonDaoService.get(ExamStudyTask.class, entity.getTaskId());
        ExamStudyTaskCourseUserInfoExample examStudyTaskCourseUserInfoExample = new ExamStudyTaskCourseUserInfoExample();
        examStudyTaskCourseUserInfoExample.and().andValid().andTaskIdEqualTo(entity.getTaskId()).andUserIdEqualTo(entity.getUserId())
                .andIsFinishEqualTo(1);
        int finishCourseNum = commonDaoService.selectCountByExample(examStudyTaskCourseUserInfoExample);
        if (finishCourseNum == examStudyTask.getCourseNum()) {
            examStudyTaskUserInfo.setStudyStatus(StudyTaskEnum.StudyStatus.finish.getValue());
        }
        commonDaoService.update(examStudyTaskUserInfo);
    }

}
