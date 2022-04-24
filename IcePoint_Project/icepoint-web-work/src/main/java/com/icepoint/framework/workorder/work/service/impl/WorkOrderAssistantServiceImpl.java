package com.icepoint.framework.workorder.work.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.workorder.work.dao.WorkOrderAssistantMapper;
import com.icepoint.framework.workorder.work.entity.WorkOrderAssistant;
import com.icepoint.framework.workorder.work.service.WorkOrderAssistantService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ck
 */
@Service
@RequiredArgsConstructor
public class WorkOrderAssistantServiceImpl implements WorkOrderAssistantService {

    private final WorkOrderAssistantMapper mapper;

    @Override
    public Boolean addAssistant(WorkOrderAssistant workOrderAssistant) {
        return mapper.insert(workOrderAssistant) > 0;
    }

    @Override
    public Optional<WorkOrderAssistant> query(WorkOrderAssistant workOrderAssistant) {
        Map<String, Object> map = MapUtils.objectToLineMap(workOrderAssistant);
        QueryWrapper<WorkOrderAssistant> wrapper = Wrappers.query(new WorkOrderAssistant());
        wrapper.allEq(map);
        return mapper.findOne(wrapper);
    }

    @Override
    public List<WorkOrderAssistant> queryAll(Long tableId, Long objId) {
        LambdaQueryWrapper<WorkOrderAssistant> wrapper = Wrappers.lambdaQuery(WorkOrderAssistant.class);
        wrapper.eq(WorkOrderAssistant::getTableId, tableId);
        wrapper.eq(WorkOrderAssistant::getObjId, objId);
        return mapper.findAll(wrapper);
    }

    @Override
    public Boolean updateAssistant(WorkOrderAssistant workOrderAssistant) {
        return mapper.update(workOrderAssistant) > 0;
    }

    @Override
    public Boolean deleteAssistant( List<WorkOrderAssistant> assistants) {
        try {
            List<Long> ids = assistants.stream().map(item -> item.getId()).collect(Collectors.toList());
            mapper.deleteInBatchIds(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean addAssistantByList(@Nullable List<WorkOrderAssistant> assistants) {
        try {
            assert assistants != null;
            assistants.forEach(mapper::insert);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateAssistantByList(List<WorkOrderAssistant> assistants) {
        try {
            assert assistants != null;
            assistants.forEach(mapper::update);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 批量删除协助人数据
     *
     * @param ids
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer deleteAllById(List<Long> ids) {
        return mapper.deleteInBatchIds(ids);
    }
}
