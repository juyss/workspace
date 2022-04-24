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
 * 工作流实例任务跟踪
 *
 * @author 
 */
@Entity
@Table(name = "workflow_trail")
@TableName("workflow_trail")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BpmFlowTrail extends LongStdEntity {
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
     * 操作名称
     */
    @Column(name = "`node_name`")
    private String nodeName;

    /**
     * 状态代码
     */
    @Column(name = "`status_val`")
    private String statusVal;
    
    /**
     * 次序
     */
    @Column(name = "`idx`")
    private Integer idx;
    
    /**
     * 当前状态
     * 标识该记录是否当前最新状态
     */
    @Column(name = "`current_state`")
    private String currentState;
    
    /**
     * 操作用户Id
     */
    @Column(name = "`operator_id`")
    private Long operatorId;

    /**
     * 操作用户
     */
    @Column(name = "`operator_name`")
    private Long operatorName;
    
    /**
     * 操作时间
     */
    @Column(name = "`oper_time`")
    private Long operTime;
  
}
