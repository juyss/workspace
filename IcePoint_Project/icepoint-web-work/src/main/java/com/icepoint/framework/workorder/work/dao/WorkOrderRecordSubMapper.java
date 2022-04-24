package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.workorder.work.entity.WorkOrderRecordSub;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface WorkOrderRecordSubMapper extends RepositoryMapper<WorkOrderRecordSub,Long> {
    /**
     * 根据父工单ids 删除所有子工单
     * @param ids 父工单集合
     * @return 删除的条数
     */
    Integer deleteByOrderIds(@Param("ids") List<Long> ids);

    /**
     * 根据父工单id 查询所有子工单
     * @param ids 父工单集合
     * @return 子工单集合
     */
    List<WorkOrderRecordSub> queryAllByIds(@Param("ids") List<Long> ids);

}
