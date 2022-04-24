package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.ExamStudyTaskUserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * exam_study_task_user_info 学习任务用户关联表
 *
 * @author xh
 * @since 2020/10/25
 */
@Mapper
public interface ExamStudyTaskUserInfoBaseMapper extends CommonDaoMapper<ExamStudyTaskUserInfo> {
}
