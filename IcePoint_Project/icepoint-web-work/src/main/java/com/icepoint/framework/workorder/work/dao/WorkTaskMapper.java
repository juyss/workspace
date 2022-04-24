package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.workorder.work.entity.WorkTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ck
 * 作业任务 数据库操作层
 */
@Mapper
public interface WorkTaskMapper extends RepositoryMapper<WorkTask,Long> {
}
