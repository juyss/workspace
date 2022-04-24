package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.workorder.work.entity.WorkTask;
import com.icepoint.framework.workorder.work.entity.WorkTaskClock;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 
 */
@Mapper
public interface WorkTaskClockMapper extends RepositoryMapper<WorkTaskClock,Long> {
	/**
	 *查询所有的 打卡点
	 * @param taskIds 打卡点id
	 * @return 打开点
	 */
	List<WorkTaskClock> findAllByTaskIds(@Param("ids") List<Long> taskIds);
}
