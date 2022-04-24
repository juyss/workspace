package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.workorder.work.entity.WorkOrderAssistant;
import org.apache.ibatis.annotations.Mapper;

/**
 * 作业任务协助人 mapper
 * @author Administrator
 */
@Mapper
public interface WorkOrderAssistantMapper extends RepositoryMapper<WorkOrderAssistant,Long> {


}
