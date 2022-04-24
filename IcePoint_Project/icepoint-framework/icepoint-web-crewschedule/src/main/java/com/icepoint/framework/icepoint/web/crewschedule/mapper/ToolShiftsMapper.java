package com.icepoint.framework.icepoint.web.crewschedule.mapper;

import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolShifts;
import com.icepoint.framework.data.mybatis.RepositoryMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 班次设置
 * @author Juyss
 * @version 1.0
 * @ClassName ToolShiftsService
 * @since 2021-07-27 15:18
 */
@Mapper
public interface ToolShiftsMapper extends RepositoryMapper<ToolShifts,Long> {
}
