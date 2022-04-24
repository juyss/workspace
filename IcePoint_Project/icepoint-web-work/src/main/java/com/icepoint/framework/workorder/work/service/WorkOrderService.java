package com.icepoint.framework.workorder.work.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ck
 */
public interface WorkOrderService {
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-工单
    //////////////////////////////////////////////////////////////////////////////////////////////////＼

    /**
     *新增工单　
     * @param entity　实体
     * @return　新增后的实体
     */
    Map<String, Object> addOrder(Map<String, Object> entity);

    /**
     * 删除
     * @param ids　id集合
     * @return 删除数据条数
     */
    Integer deleteOrders(@RequestParam("ids") List<Long> ids);

    /**
     * 修改工单
     * @param entity 实体
     * @return 修改后的实体
     */
    Map<String, Object> updateOrder(Map<String, Object> entity);
    
    /**
     * 列表查询工单
     *
     * @param filter 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
   Page<Map<String, Object>> orderList(Map<String, Object> filter, Pageable pageable);

    /**
     * 详情查询工单
     *
     * @param id 工单主键
     * @return 工单
     */
    Map<String, Object> getOrderById(Long id);

    /**
     * 问题转工单
     * @param defect  问题实体
     * @return 工单实体
     */
    Map<String,Object> defectToOrder(Map<String,Object> defect);
}
