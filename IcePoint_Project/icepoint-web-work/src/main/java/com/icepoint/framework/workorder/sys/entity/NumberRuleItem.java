package com.icepoint.framework.workorder.sys.entity;

import javax.persistence.Column;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 编码规则详情表
 *
 * @author admin
 * @since 2021-07-13 11:54:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_num_rule_item")
public class NumberRuleItem extends LongStdEntity {
	/**
	 * 编码规则ID
	 */
    @Column(name = "rule_id")
    private Long ruleId;
    
	/**
	 *  编码规则类型
	 */
    @Column(name = "type")
    private Integer type;
    
	/**
	 * 内容
	 */
    @Column(name = "content")
    private String content;
    
	/**
	 *  起始流水号
	 */
    @Column(name = "start_no")
    private Integer startNo;
    
	/**
	 *  流水号增量
	 */
    @Column(name = "increment")
    private Integer increment;
    
	/**
	 *  重置周期
	 */
    @Column(name = "cycle")
    private Integer cycle;

	/**
	 *  序号
	 */
    @Column(name = "idx")
    private Integer idx;
}
