package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.ExamCourse;
import org.apache.ibatis.annotations.Mapper;

/**
 * exam_course 课程表
 *
 * @author xh
 * @since 2020/12/12
 */
@Mapper
public interface ExamCourseBaseMapper extends CommonDaoMapper<ExamCourse> {
}
