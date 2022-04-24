package com.icepoint.framework.workorder.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.workorder.sys.entity.NumberRuleItem;
import com.icepoint.framework.workorder.work.entity.CheckPoint;

/**
 * 编码规则详情表数据层
 * @author admin
 */
@Mapper
public interface NumberRuleItemMapper {
	List<NumberRuleItem> findAllByRuleIds(Long appId,Long platformId,List<Long> ruleId);
}
