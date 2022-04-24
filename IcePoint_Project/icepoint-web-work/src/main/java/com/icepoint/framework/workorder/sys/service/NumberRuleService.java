package com.icepoint.framework.workorder.sys.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.icepoint.framework.workorder.sys.entity.NumberRule;

public interface NumberRuleService {
	/**
	 * 添加编码规则
	 * @param entity 编码规则{@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule NumberRule}
	 * @return 编码规则
	 */
	NumberRule add(NumberRule entity);
	
	/**
     * 编辑编码规则
     *
     * @param entity 编辑的编码规则{@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule NumberRule}
     * @return 返回编辑后的编码规则{@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule NumberRule}
     */
    NumberRule edit(NumberRule entity);
    
    /**
     * 更新最新编码
     * @param assertId 资产ID
     * @param latestNum 最新编码
     * @return 是否更新成功
     */
    Boolean editLatestNum(Long assertId,String latestNum);

    /**
     * 删除编码规则
     *
     * @param ids 要删除的数据主键
     * @return 返回删除后的数据
     */
    Integer delete(List<Long> ids);

    /**
     * 列表查询编码规则
     *
     * @param entity 查询实体
     * @param pageable 分页对象
     * @return 所有编码规则{@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule NumberRule}
     */
    Page<NumberRule> list(Map<String, Object> entity, Pageable pageable);
    
    /**
     * 根据资产ID获取最新的编码
     * 
     * @param assertId 资产ID
     * @return 最新编码
     */
    String getNumber(Long assertId);
    
    /**
     * 确认资产ID及对应编码
     * 
     * @param assertId 资产ID
     * @param number 编码
     * @return 如果成功返回ok，如果已经被占返回新编码
     */
    String confirmNumber(Long assertId,String number);
}
