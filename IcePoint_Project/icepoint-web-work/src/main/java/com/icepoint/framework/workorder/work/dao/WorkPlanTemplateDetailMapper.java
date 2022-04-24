package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.workorder.work.entity.WorkPlanTemplateDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jiawei Zhao
 */
@Mapper
public interface WorkPlanTemplateDetailMapper extends RepositoryMapper<WorkPlanTemplateDetail, Long> {
}
