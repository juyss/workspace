package com.icepoint.framework.icepoint.web.crewschedule.service;

import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName ToolGroupService
 * @description
 * @since 2021-07-28 10:53
 */
public interface ToolGroupService {

    /**
     *
     * @author Juyss
     * @param toolGroup 数据实体
     * @return Integer
     */
    Integer insert(ToolGroup toolGroup);

    /**
     *
     * @author Juyss
     * @param toolGroup 数据实体
     * @return Integer
     */
    Integer update(ToolGroup toolGroup);

    /**
     *
     * @author Juyss
     * @param ids id集合
     * @return Integer
     */
    Integer delete(List<Long> ids);

    /**
     *
     * @author Juyss
     * @param toolGroup 条件
     * @param pageable 分页数据
     * @return Page<ToolGroup>
     */
    Page<ToolGroup> findAll(ToolGroup toolGroup, Pageable pageable);

    /**
     *
     * @author Juyss
     * @param id 主键
     * @return ToolGroup
     */
    ToolGroup findOne(Long id);
}
