package com.icepoint.framework.plugin.workflow;

import java.util.List;

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
 * 工作流场景
 *
 * @author 
 */
@Entity
@Table(name = "workflow_scene")
@TableName("workflow_scene")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BpmScene extends LongStdEntity {
	
    /**
     * 流程编码
     */
    @Column(name = "`flow_code`")
    private String flowCode;
    
    /**
     * 场景编码
     */
    @Column(name = "`code`")
    private String code;
    
    /**
     * 场景名称
     */
    @Column(name = "`name`")
    private String name;
    
    /**
     * 业务编码
     */
    @Column(name = "`biz_code`")
    private String bizCode;

    /**
     * 备注
     */
    @Column(name = "`note`")
    private String note;
    	
    /**
     * 工作流节点
     */
    private List<BpmNode> nodes;
    
    /**
     * 工作流网络记录
     */
    private List<BpmNet> nets;
}
