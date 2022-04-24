package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.workorder.work.entity.WorkOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName WorkOrderMapper
 * @description
 * @since 2021-07-19 9:32
 */
@Mapper
public interface WorkOrderMapper extends RepositoryMapper<WorkOrder, Long> {
}
