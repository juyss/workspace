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
 * 流程迁移条件定义
 *
 * @author 
 */
@Entity
@Table(name = "workflow_term_def")
@TableName("workflow_term_def")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BpmTermDef extends LongStdEntity {
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
     * 条件值
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 迁移节点
     */
    @Column(name = "`next_code`")
    private String nextCode;

    /**
     * 伴随操作
     */
    @Column(name = "`oper_process`")
    private String operProcess;

    /**
     * 伴随操作模式
     */
    @Column(name = "`oper_sync`")
    private Integer operSync;
    
    /**
     * 伴随后续处理
     */
    @Column(name = "`after_process`")
    private String afterProcess;
    
    /**
     * 伴随后续操作模式
     */
    @Column(name = "`after_sync`")
    private Integer afterSync;
    
}
