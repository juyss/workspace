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
 * 工作流程定义
 *
 * @author 
 */
@Entity
@Table(name = "workflow_flow")
@TableName("workflow_flow")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BpmFlow extends LongStdEntity {
    /**
     * 流程编码
     */
    @Column(name = "`code`")
    private String code;
    
    /**
     * 流程名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 字段字典名
     * 流程状态值域通过数据字典定义，和业务共用一个数据字典。但也有区别，区别在于如果业务中流程很复杂，涉及多表多个字段状态，则在这里仅能通过一个数据字典表示，看是否考虑能够兼容多个
     */
    @Column(name = "`category_en`")
    private String categoryEn;

    /**
     * 业务编码
     */
    @Column(name = "`biz_code`")
    private String bizCode;

    /**
     * 类型
     * 流程类型  1、原始表状态迁移   2、数据集中型状态迁移  3、自定义  4、Activiti工作流  5、JBPM 6、AgileBPM工作流。
     * 通过数据字典定义，可以扩展其他类型
     * 是否存在一个流程中各环节机制不同的，如果存在，类型应放到场景或节点中，目前认为一个流程是统一的
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 备注
     */
    @Column(name = "`note`")
    private String note;
    	
    /**
     * 三方ID
     * Activiti、JBPM、AgileBPM等原生工作流包装使用时，其原生工作流定义的id
     */
    @Column(name = "`third_id`")
    private String thirdId;
    
    /**
     * 扩展参数
     * Activiti、JBPM、AgileBPM等原生工作流包装使用时，需要记录的参数，由各类型自己定义和解析，采用JSON格式记录
     */
    @Column(name = "`parameter`")
    private String parameter;
    
    /**
     * 工作流场景
     */
    private List<BpmScene> scenes;
}
