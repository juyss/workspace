package com.icepoint.framework.icepoint.web.crewschedule.mapper;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolSchedule;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName ToolScheduleMapper
 * @description
 * @since 2021-07-28 13:48
 */
@Mapper
public interface ToolScheduleMapper extends RepositoryMapper<ToolSchedule, Long> {
}
