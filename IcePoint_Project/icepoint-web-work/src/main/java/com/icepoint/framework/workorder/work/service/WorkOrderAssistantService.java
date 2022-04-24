package com.icepoint.framework.workorder.work.service;

import com.icepoint.framework.workorder.work.entity.WorkOrderAssistant;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Administrator
 */
public interface WorkOrderAssistantService {
    /**
     * 新增协助人
     * @param workOrderAssistant 协助人实体
     * @return 是否成功
     */
    Boolean addAssistant(WorkOrderAssistant workOrderAssistant);

    /**
     * 查询单个协助人
     * @param workOrderAssistant 条件实体
     * @return 协助人
     */
    Optional<WorkOrderAssistant> query(WorkOrderAssistant workOrderAssistant);

    /**
     * 根据tableId和objId查询所有协助人
     * @param tableId
     * @param objId
     * @return 协助人集合
     */
    List<WorkOrderAssistant> queryAll(Long tableId, Long objId);

    /**
     * 删除协助人
     * @param workOrderAssistant 条件实体
     * @return 是否成功
     */
    Boolean updateAssistant(WorkOrderAssistant workOrderAssistant);

    /**
     * 批量删除 需要两个值  一个是tableId 一个是objId
     * @param assistants 删除集合
     * @return 是否成功
     */
    Boolean deleteAssistant(List<WorkOrderAssistant> assistants);

    /**
     * 批量新增协助人
     * @param assistants 新增集合
     * @return
     */
    Boolean addAssistantByList(List<WorkOrderAssistant> assistants);

    /**
     * 批量修改协助人
     * @param assistants
     * @return
     */
    Boolean updateAssistantByList(List<WorkOrderAssistant> assistants);

    /**
     * 批量删除协助人数据
     * @author Juyss
     * @param ids
     * @return Integer
     */
    Integer deleteAllById(List<Long> ids);
}
