package com.icepoint.framework.sample.module.sample.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.sample.module.sample.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jiawei Zhao
 */
@Mapper
public interface TeacherMapper extends RepositoryMapper<Teacher, Long> {
}
