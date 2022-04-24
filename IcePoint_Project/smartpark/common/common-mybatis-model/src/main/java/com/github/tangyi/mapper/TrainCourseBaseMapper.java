package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.TrainCourse;
import org.apache.ibatis.annotations.Mapper;

/**
 * train_course 培训表
 *
 * @author xh
 * @since 2020/10/20
 */
@Mapper
public interface TrainCourseBaseMapper extends CommonDaoMapper<TrainCourse> {
}
