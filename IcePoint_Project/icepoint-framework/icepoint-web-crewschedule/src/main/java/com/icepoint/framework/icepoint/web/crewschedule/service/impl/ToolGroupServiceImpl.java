package com.icepoint.framework.icepoint.web.crewschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolGroup;
import com.icepoint.framework.icepoint.web.crewschedule.mapper.ToolGroupMapper;
import com.icepoint.framework.icepoint.web.crewschedule.service.ToolGroupService;
import com.mysema.commons.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName ToolGroupServiceImpl
 * @description
 * @since 2021-07-28 10:55
 */
@Service
public class ToolGroupServiceImpl implements ToolGroupService {

    @Autowired
    private ToolGroupMapper mapper;

    /**
     * @param toolGroup 数据实体
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer insert(ToolGroup toolGroup) {
        return mapper.insert(toolGroup);
    }

    /**
     * @param toolGroup 数据实体
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer update(ToolGroup toolGroup) {
        Assert.isTrue(ObjectUtils.isEmpty(toolGroup.getId()),"id没有值");
        return mapper.update(toolGroup);
    }

    /**
     * @param ids id集合
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer delete(List<Long> ids) {
        Assert.isTrue(ObjectUtils.isEmpty(ids),"id集合为空");
        return mapper.deleteInBatchIds(ids);
    }

    /**
     * @param toolGroup 条件
     * @param pageable  分页数据
     * @return Page<ToolGroup>
     * @author Juyss
     */
    @Override
    public Page<ToolGroup> findAll(ToolGroup toolGroup, Pageable pageable) {
        Map<String, Object> lineMap = MapUtils.objectToLineMap(toolGroup);
        QueryWrapper<ToolGroup> wrapper = new QueryWrapper<>();

        wrapper.allEq(lineMap);

        return mapper.findAll(wrapper,pageable);
    }

    /**
     * @param id 主键
     * @return ToolGroup
     * @author Juyss
     */
    @Override
    public ToolGroup findOne(Long id) {
        return mapper.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("班组数据不存在，id：%s",id)));
    }
}
