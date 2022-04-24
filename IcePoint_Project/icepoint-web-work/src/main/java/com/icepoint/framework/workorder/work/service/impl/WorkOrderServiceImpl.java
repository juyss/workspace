package com.icepoint.framework.workorder.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.web.system.entity.AssetDefine;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.service.AssetService;
import com.icepoint.framework.web.system.service.ResourceService;
import com.icepoint.framework.workorder.work.dao.WorkDefectMapper;
import com.icepoint.framework.workorder.work.dao.WorkOrderMapper;
import com.icepoint.framework.workorder.work.dao.WorkOrderRecordSubMapper;
import com.icepoint.framework.workorder.work.dao.WorkOrderRepository;
import com.icepoint.framework.workorder.work.entity.WorkDefect;
import com.icepoint.framework.workorder.work.entity.WorkOrder;
import com.icepoint.framework.workorder.work.entity.WorkOrderRecordSub;
import com.icepoint.framework.workorder.work.entity.WorkTask;
import com.icepoint.framework.workorder.work.service.WorkOrderService;
import com.icepoint.framework.workorder.work.service.WorkTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author ck
 */
@Service
@RequiredArgsConstructor
public class WorkOrderServiceImpl implements WorkOrderService {

    private final String WORK_ORD_RECORD = "WORK_ORD_RECORD";
    private final String WORK_ORD_RECORD_SUB = "WORK_ORD_RECORD_SUB";

    private final WorkOrderRepository workOrderRepository;
    private final AssetService assetService;
    private final WorkOrderRecordSubMapper workOrderRecordSubMapper;
    private final WorkTaskService workTaskService;
    private final WorkOrderMapper workOrderMapper;
    private final WorkDefectMapper workDefectMapper;
    private final ResourceService resourceService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> addOrder(Map<String, Object> entity) {
        WorkOrder workOrder = BeanUtils.toBean(entity, WorkOrder.class,true,false);
        //作业问题记录id
        AssetDefine assetDefine = assetService.getAssetDef(WORK_ORD_RECORD)
                .orElseThrow(() -> new IllegalArgumentException("未找到资产"));
        workOrder.setCode("10001");
        //设置 工单默认状态
        //dictService
        workOrder.setOrderStatus(1);
        //物理表添加
        workOrder = workOrderRepository.save(workOrder);
        entity.put("id", workOrder.getId());
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(entity);
        //扩展属性添加
        assetService.addAllObjAttr(assetDefine.getId(), list);
        //获取子工单
        List<Map<String, Object>> detailsList = CastUtils.cast(entity.get("details"));
        if (ObjectUtils.isEmpty(detailsList)) {
            return MapUtils.objectToMap(workOrder);
        }
        for (Map<String, Object> map : detailsList) {
            WorkOrderRecordSub workOrderRecordSub = BeanUtils.toBean(entity, WorkOrderRecordSub.class,true,false);
            workOrderRecordSub.setWorkOrderRecordId(workOrder.getId());
            workOrderRecordSub.setOrderStatus(workOrder.getOrderStatus());
            workOrderRecordSubMapper.insert(workOrderRecordSub);
            map.put("id", workOrderRecordSub.getId());
        }
        //插入子工单扩展属性
        AssetDefine detailsAsset = assetService.getAssetDef(WORK_ORD_RECORD_SUB)
                .orElseThrow(() -> new IllegalArgumentException("未定义资产"));
        assetService.addAllObjAttr(detailsAsset.getId(), detailsList);
        //子工单工程量表，第一版本暂时不做
        return MapUtils.objectToMap(workOrder);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteOrders(List<Long> ids) {
        //判断工单状态是否可以删除
        List<WorkOrder> allById = workOrderRepository.findAllById(ids);
        boolean b = allById.stream().anyMatch(item -> item.getOrderStatus().equals(0));
        if (b) {
            throw new IllegalArgumentException("此状态下的工单不可修改");
        }
        AssetDefine assetDefine = assetService.getAssetDef(WORK_ORD_RECORD)
                .orElseThrow(() -> new IllegalArgumentException("未找到资产"));
        //删除扩展属性
        assetService.deleteAllObjAttrByObjId(assetDefine.getId(), ids);
        //删除实体
        workOrderMapper.deleteInBatchIds(ids);
        //删除子工单
        //查询要删除子工单的id
        List<WorkOrderRecordSub> list = workOrderRecordSubMapper.queryAllByIds(ids);
        if (ObjectUtils.isEmpty(list)) {
            ids.size();
        }
        List<Long> subIds = list.stream().map(WorkOrderRecordSub::getWorkOrderRecordId).collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(subIds)) {
            workOrderRecordSubMapper.deleteByOrderIds(subIds);
        }
        //删除工单的扩展属性
        AssetDefine subAssetDefine = assetService.getAssetDef("WORK_ORD_SUB")
                .orElseThrow(() -> new IllegalArgumentException("未定义资产"));
        assetService.deleteAllObjAttrByObjId(subAssetDefine.getId(), subIds);
        return ids.size();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> updateOrder(Map<String, Object> entity) {
        WorkOrder workOrder = BeanUtils.toBean(entity, WorkOrder.class,true,false);
        //TODO 判断状态是否可以修改 先写死
        if (workOrder.getOrderStatus() == 0) {
            throw new IllegalArgumentException("此状态下的工单不可修改");
        }
        //作业问题记录id
        AssetDefine assetDefine = assetService.getAssetDef(WORK_ORD_RECORD)
                .orElseThrow(() -> new IllegalArgumentException("未找到资产"));
        workOrderMapper.update(workOrder);
        //扩展字段属性修改
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(entity);
        assetService.updateAllObjAttr(assetDefine.getId(), list);
        //子工单修改
        List<Map<String, Object>> detailsList = CastUtils.cast(entity.get("details"));
        if (ObjectUtils.isEmpty(detailsList)) {
            return entity;
        }
        for (Map<String, Object> map : detailsList) {
            //判断被修改的子工单的状态
            WorkOrderRecordSub workOrderRecordSub = BeanUtils.toBean(map, WorkOrderRecordSub.class,true,false);
            if (workOrderRecordSub.getOrderStatus() == 0) {
                throw new IllegalArgumentException("此状态下的子工单不可修改");
            }
            workOrderRecordSubMapper.update(workOrderRecordSub);
        }
        AssetDefine detailsAsset = assetService.getAssetDef(WORK_ORD_RECORD_SUB)
                .orElseThrow(() -> new IllegalArgumentException("未定义资产"));
        //修改扩展属性
        assetService.updateAllObjAttr(detailsAsset.getId(), detailsList);
        return entity;
    }

    @Override
    public Page<Map<String, Object>> orderList(Map<String, Object> filter, Pageable pageable) {
        //工单列表查询
        WorkOrder workOrder = BeanUtils.toBean(filter, WorkOrder.class,true,false);
        Page<WorkOrder> all;
        if (ObjectUtils.isEmpty(filter)) {
            all = workOrderMapper.findAll(pageable);
        } else {
            QueryWrapper<WorkOrder> queryWrapper = new QueryWrapper<>();
            Map<String, Object> map = MapUtils.objectToLineMap(workOrder);
            queryWrapper.allEq(map);
            all = workOrderMapper.findAll(queryWrapper, pageable);
        }
        AssetDefine assetDefine = assetService.getAssetDef(WORK_ORD_RECORD)
                .orElseThrow(() -> new IllegalArgumentException("未找到资产"));
        AssetDefine assetsDefine = assetService.getAssetDef(assetDefine.getAssetCode()).orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));
        Long resourceId = assetsDefine.getResourceId();
        TableMetadata table = resourceService.getInfoByResourceId(resourceId);
        //查询扩展字段
        List<Map<String, Object>> list = addAttr(all.getContent(), table.getId());
        long totalElements = all.getTotalElements();
        return new PageImpl<>(list, pageable, totalElements);
    }

    @Override
    public Map<String, Object> getOrderById(Long id) {
        WorkOrder workOrder = workOrderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("工单不存在"));
        //查询工单扩展属性
        AssetDefine assetsDefine = assetService.getAssetDef(WORK_ORD_RECORD).orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));
        Long resourceId = assetsDefine.getResourceId();
        TableMetadata table = resourceService.getInfoByResourceId(resourceId);
        Map<String, Object> allObjAttr = assetService.findAllObjAttrById(table.getId(), id);
        Map<String, Object> map = MapUtils.objectToMap(workOrder);
        if (!ObjectUtils.isEmpty(allObjAttr)) {
            map.putAll(allObjAttr);
        }
        //查询子工单
        LambdaQueryWrapper<WorkOrderRecordSub> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(WorkOrderRecordSub::getWorkOrderRecordId, id);
        List<WorkOrderRecordSub> workOrderRecordSubSet = workOrderRecordSubMapper.findAll(lambdaQueryWrapper);
        TableMetadata subTable = workTaskService.getTable(WORK_ORD_RECORD_SUB);
        List<Map<String, Object>> list = addAttr(workOrderRecordSubSet, subTable.getId());
        map.put("workOrderRecordSubSet", list);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> defectToOrder(Map<String, Object> defect) {
        //获取问题实体
        long id = Long.parseLong(defect.get("id").toString());
        WorkDefect workDefect = workDefectMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("问题不存在"));
        //获取作业任务
        Map<String, Object> task = workTaskService.findTaskById(workDefect.getTaskId());
        WorkTask workTask = BeanUtils.toBean(task, WorkTask.class,true,false);
        WorkOrder workOrder = new WorkOrder();
        workOrder.setCode(workTask.getCode());
        //设置为问题转工单
        workOrder.setWorkObjId(workDefect.getDefectObjId());
        //TODO 数据字典查询出问题转工单类型
        workOrder.setType(workTask.getWorkType().toString());
        workOrder.setWorkObjId(workTask.getWorkObj());
        workOrder.setWorkObjName(workTask.getWorkObjName());
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        workOrder.setRegisterTime(timestamp.getTime());
        workOrder.setContent(workDefect.getNote());
        workOrder.setRegion(workDefect.getRegion());
        workOrderMapper.insert(workOrder);
        return MapUtils.objectToMap(workOrder);
    }

    /**
     * @param collection 需要添加字段的集合
     * @param tableId    表id
     * @return 返回添加扩展字段之后的集合
     * @author ck
     */
    public List<Map<String, Object>> addAttr(Collection<? extends LongStdEntity> collection, Long tableId) {
        List<Map<String, Object>> attrList = new ArrayList<>();
        collection.forEach(item -> {
            Map<String, Object> allObjAttrById = assetService.findAllObjAttrById(tableId, item.getId());
            Map<String, Object> map = MapUtils.objectToMap(item);
            if (!ObjectUtils.isEmpty(allObjAttrById)) {
                map.putAll(allObjAttrById);
            }
            attrList.add(map);
        });
        return attrList;
    }
}
