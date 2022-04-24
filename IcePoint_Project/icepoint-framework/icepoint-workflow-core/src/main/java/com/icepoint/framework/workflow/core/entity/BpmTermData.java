package com.icepoint.framework.workflow.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 流程输入数据
 *
 * @author 
 */
@Entity
@Table(name = "workflow_term_data")
@TableName("workflow_term_data")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BpmTermData extends LongStdEntity {
    /**
     * 流程编码
     */
    @Column(name = "`flow_code`")
    private String flowCode;
    
    /**
     * 场景编码
     */
    @Column(name = "`scene_code`")
    private String sceneCode;
    
    /**
     * 业务编码
     */
    @Column(name = "`biz_code`")
    private String bizCode;
    
    /**
     * 实例编码
     */
    @Column(name = "`obj_code`")
    private String objCode;
    
    /**
     * 节点编码
     */
    @Column(name = "`node_code`")
    private String nodeCode;
    
    /**
     * 数据名称
     */
    @Column(name = "`name`")
    private String name;
    
    /**
     * 数据变量
     */
    @Column(name = "`name_en`")
    private String nameEn;
    
    /**
     * 数据值
     */
    @Column(name = "`value`")
    private String value;

	/**
	 * 次序
	 */
	@Column(name = "`idx`")
	private Integer idx;
}
