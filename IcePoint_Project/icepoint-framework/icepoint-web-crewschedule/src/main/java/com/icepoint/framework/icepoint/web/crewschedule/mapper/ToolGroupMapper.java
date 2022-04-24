package com.icepoint.framework.icepoint.web.crewschedule.mapper;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName ToolGroupMapper
 * @description
 * @since 2021-07-28 10:52
 */
@Mapper
public interface ToolGroupMapper extends RepositoryMapper<ToolGroup,Long> {
}
