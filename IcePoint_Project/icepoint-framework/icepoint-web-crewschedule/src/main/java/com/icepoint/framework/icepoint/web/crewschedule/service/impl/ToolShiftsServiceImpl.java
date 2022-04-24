package com.icepoint.framework.icepoint.web.crewschedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolShifts;
import com.icepoint.framework.icepoint.web.crewschedule.mapper.ToolShiftsMapper;
import com.icepoint.framework.icepoint.web.crewschedule.service.ToolShiftsService;
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
 * @ClassName ToolShiftsServiceImpl
 * @since 2021-07-27 15:43
 */
@Service
public class ToolShiftsServiceImpl implements ToolShiftsService {

    @Autowired
    private ToolShiftsMapper mapper;

    /**
     * @param toolShifts 数据实体
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer insert(ToolShifts toolShifts) {
        return mapper.insert(toolShifts);
    }

    /**
     * @param toolShifts 数据实体
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer update(ToolShifts toolShifts) {
        Assert.isTrue(ObjectUtils.isEmpty(toolShifts.getId()),"id没有值");
        return mapper.update(toolShifts);
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
     * @param toolShifts 条件
     * @param pageable   分页数据
     * @return Page<ToolShifts>
     * @author Juyss
     */
    @Override
    public Page<ToolShifts> findAll(ToolShifts toolShifts, Pageable pageable) {
        Map<String, Object> lineMap = MapUtils.objectToLineMap(toolShifts);
        QueryWrapper<ToolShifts> wrapper = new QueryWrapper<>();

        wrapper.allEq(lineMap);
        return mapper.findAll(wrapper, pageable);
    }

    /**
     * @param id 主键
     * @return ToolShifts
     * @author Juyss
     */
    @Override
    public ToolShifts findOne(Long id) {
        return mapper.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("班次数据不存在，id：%s",id)));
    }
}
