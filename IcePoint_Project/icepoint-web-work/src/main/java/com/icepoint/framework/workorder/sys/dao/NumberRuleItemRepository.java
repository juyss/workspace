package com.icepoint.framework.workorder.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.workorder.sys.entity.NumberRuleItem;

/**
 * 编码规则详情表数据层
 * @author admin
 */
@Mapper
public interface NumberRuleItemRepository extends LongStdRepository<NumberRuleItem> {

}
