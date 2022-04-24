package com.icepoint.framework.workorder.sys.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 编码规则表实体类
 *
 * @author admin
 * @since 2021-07-13 11:51:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_num_rule")
public class NumberRule extends LongStdEntity {
	/**
	 * 资产ID
	 */
    @Column(name = "assert_id")
    private Long assertId;
    
	/**
	 * 编码规则名称
	 */
    @Column(name = "name")
    private String name;
    
	/**
	 *  生成规则
	 */
    @Column(name = "mode")
    private Integer mode;
    
	/**
	 * 备注
	 */
    @Column(name = "remark")
    private String remark;
    
	/**
	 * 示例
	 */
    @Column(name = "example")
    private String example;
    
	/**
	 * 最新编码
	 */
    @Column(name = "latest_num")
    private String latestNum;
    
    @JoinTable(name = "sys_num_rule_item",
            joinColumns = @JoinColumn(name = "`rule_id`"))
    @ManyToMany(fetch = FetchType.LAZY)
    @TableField(exist = false)
    private List<NumberRuleItem> items;
    
    public NumberRule(Map<String,Object> map){
    	
    }
}
