package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.ExamStudyTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * exam_study_task 学习任务表
 *
 * @author xh
 * @since 2020/12/12
 */
@Mapper
public interface ExamStudyTaskBaseMapper extends CommonDaoMapper<ExamStudyTask> {
}
