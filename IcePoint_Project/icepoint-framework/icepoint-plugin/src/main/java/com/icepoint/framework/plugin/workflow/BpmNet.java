package com.icepoint.framework.plugin.workflow;

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
 * 工作流网络
 *
 * @author 
 */
@Entity
@Table(name = "workflow_net")
@TableName("workflow_net")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BpmNet extends LongStdEntity {
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
     * 前节点
     */
    @Column(name = "`from_code`")
    private String fromCode;
    
    /**
     * 后节点
     * 操作名称，包括正向操作和逆向操作(驳回)
     */
    @Column(name = "`to_code`")
    private String toCode;

    /**
     * 条件参数
     * 在绘制时候界面上显示的条件，是否必要记录，基于状态的迁移里会自己记录
     */
    @Column(name = "`parameter`")
    private String parameter;
}
