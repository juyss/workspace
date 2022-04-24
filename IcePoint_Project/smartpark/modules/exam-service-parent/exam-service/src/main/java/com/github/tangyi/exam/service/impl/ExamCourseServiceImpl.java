package com.github.tangyi.exam.service.impl;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.util.StringUtils;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.page.PageUtils;
import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.exam.mapper.ExamStudyTaskMapper;
import com.github.tangyi.exam.service.ExamCourseService;
import com.github.tangyi.exam.service.ExamStudyTaskService;
import com.github.tangyi.example.ExamCourseExample;
import com.github.tangyi.example.ExamStudyTaskCourseRelationExample;
import com.github.tangyi.example.ExamStudyTaskExample;
import com.github.tangyi.model.ExamCourse;
import com.github.tangyi.model.ExamStudyTask;
import com.github.tangyi.model.ExamStudyTaskCourseRelation;
import com.github.tangyi.user.api.feign.UserServiceClient;
import lombok.AllArgsConstructor;
import my.xh.validate.CustomException;
import my.xh.validate.ValidateField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExamCourseServiceImpl implements ExamCourseService {
    private static Logger logger = LoggerFactory.getLogger(ExamCourseServiceImpl.class);

    private CommonDaoService commonDaoService;
    private ExamStudyTaskMapper examStudyTaskMapper;
    private UserServiceClient userServiceClient;
    private ExamStudyTaskService examStudyTaskService;

    @Override
    public ExamCourse get(Long id) {
        ExamCourse examCourse = commonDaoService.get(ExamCourse.class, id);
        return examCourse;
    }

    @CustomException(excptionClazz = CommonException.class)
    @ValidateField(index = 0, filedName = "courseName", minLen = 1, maxLen = 64, notNull = true)
    @ValidateField(index = 0, filedName = "major", notNull = true, minLen = 1, maxLen = 64)
    @ValidateField(index = 0, filedName = "teacher", notNull = true, minLen = 1, maxLen = 64)
    @ValidateField(index = 0, filedName = "studyTime", notNull = true, minVal = 1)
    @ValidateField(index = 0, filedName = "courseTime", notNull = true, minVal = 1)
    @ValidateField(index = 0, filedName = "courseIntroduce", maxLen = 500)
    @ValidateField(index = 0, filedName = "courseType", notNull = true, strVals = {"图文课程", "视频课程"})
    @ValidateField(index = 0, filedName = "pic", minLen = 1)
    @Override
    public int save(ExamCourse course) {
        course.setStatus(0);
        course.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        if (course.isNewRecord()) {
            return commonDaoService.insert(course);
        } else {
            ExamCourse examcourse = commonDaoService.get(ExamCourse.class, course.getId());
            if (examcourse == null) throw new CommonException("课程不存在");
            if (examcourse.getStatus() == 1) throw new CommonException("已发布的课程不得修改。如需修改，请先撤回发布。");
            course.setStatusA(0);//修改后，需重新审核
            return commonDaoService.update(course);
        }
    }

    @Override
    @Transactional
    public int del(Long id) {
        ExamStudyTaskCourseRelationExample example = new ExamStudyTaskCourseRelationExample();
        example.and().andValid().andCourseIdEqualTo(id);
        List<ExamStudyTaskCourseRelation> rs = commonDaoService.selectByExample(example);
        if(rs.size()>0) {
            ExamStudyTaskExample examStudyTaskExample = new ExamStudyTaskExample();
            examStudyTaskExample.and().andValid().andIdIn(rs.stream().map(ExamStudyTaskCourseRelation::getTaskId).collect(Collectors.toList()));
            List<ExamStudyTask> examStudyTasks = commonDaoService.selectByExample(examStudyTaskExample);
            throw new CommonException("课程被关联了，不允许删除:" +
                    String.join(",",examStudyTasks.stream().map(ExamStudyTask::getTaskName).collect(Collectors.toList())));
        }
        ExamCourse examCourse = this.get(id);
        if(examCourse.getStatus() == 1) throw new CommonException("已发布的课程，不允许删除");
        examStudyTaskService.delCourseRelation(id,null);
        return commonDaoService.delete(ExamCourse.class, id);
    }

    @Override
    public PageResult list(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq) {
        ExamCourseExample examCourseExample = new ExamCourseExample();
        ExamCourseExample.Criteria c = examCourseExample.and().andValid();

        if (StringUtils.isNotBlank(baseReq.getKw())) c.andCourseNameLike(String.format("%%%s%%", baseReq.getKw()));
        if (baseReq.getStartTime() != null) c.andCreateDateGreaterThanOrEqualTo(baseReq.getStartTime());
        if (baseReq.getEndTime() != null) c.andCreateDateLessThanOrEqualTo(baseReq.getEndTime());

        examCourseExample.setOrderByClause(String.format("%s %s", sort, order));
        PageResult query = PageUtils.query(pageNum, pageSize, 10, () -> commonDaoService.selectByExample(examCourseExample));

        return query;
    }

    @Override
    @ValidateField(index = 2, notNull = true, strVals = {"status", "statusA"})
    public boolean check(Long id, Integer status, String field) {
        ExamCourse examCourse = new ExamCourse();
        examCourse.setId(id);
        ResponseBean<UserVo> userVoResponseBean = userServiceClient.findUserByIdentifier(SysUtil.getUser(), SysUtil.getTenantCode());
        if(StringUtils.equals("status",field)){
//            ExamCourse examCourse1 = commonDaoService.get(ExamCourse.class, id);
//            if(examCourse1 == null ||!examCourse1.getStatusA().equals(1))  throw new CommonException("先审核再发布");

            if(status == 0){
                ExamStudyTaskCourseRelationExample example = new ExamStudyTaskCourseRelationExample();
                example.and().andValid().andCourseIdEqualTo(id);
                if(commonDaoService.selectCountByExample(example)>0)  throw new CommonException("课程已被学习任务关联，不得撤回发布。如需撤回，请先取消该课程与学习任务的所有关联。");
            }

            examCourse.setStatus(status);
            examCourse.setAuditUserName(userVoResponseBean.getData().getName());
            examCourse.setAuditUserId(userVoResponseBean.getData().getUserId());
        }else {
            ExamCourse examStudyTask1 = commonDaoService.get(ExamCourse.class, id);
            if (examStudyTask1 != null && examStudyTask1.getStatus() == 1) throw new CommonException("已发布的课程不得修改。如需修改，请先撤回发布。");
            examCourse.setStatusA(status);
            examCourse.setStatusAUserName(userVoResponseBean.getData().getName());
            examCourse.setStatusAUserId(userVoResponseBean.getData().getUserId());
        }

        return commonDaoService.update(examCourse) > 0;
    }

    @Override
    public PageResult courseListForFe(Integer pageNum, Integer pageSize, String sort, String order, BaseReq baseReq) {
        PageResult query = PageUtils.query(pageNum, pageSize, 10, () -> examStudyTaskMapper.courseListForFe(sort,order));
        return query;
    }
}
