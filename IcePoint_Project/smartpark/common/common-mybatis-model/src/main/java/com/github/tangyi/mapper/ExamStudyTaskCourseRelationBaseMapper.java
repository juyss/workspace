package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.ExamStudyTaskCourseRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * exam_study_task_course_relation 学习任务课程关系表
 *
 * @author xh
 * @since 2020/10/25
 */
@Mapper
public interface ExamStudyTaskCourseRelationBaseMapper extends CommonDaoMapper<ExamStudyTaskCourseRelation> {
}
