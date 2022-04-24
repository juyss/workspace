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
 * 流程迁移条件实例
 *
 * @author 
 */
@Entity
@Table(name = "workflow_term")
@TableName("workflow_term")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BpmTerm extends LongStdEntity {
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
     * 迁移条件
     */
    @Column(name = "`term`")
    private String term;
    
    /**
     * 计算式
     */
    @Column(name = "`expression`")
    private String expression;
    
    /**
     * 结果值
     */
    @Column(name = "`value`")
    private String value;
    
    /**
     * 迁移节点
     */
    @Column(name = "`next_code`")
    private String nextCode;
    
}
