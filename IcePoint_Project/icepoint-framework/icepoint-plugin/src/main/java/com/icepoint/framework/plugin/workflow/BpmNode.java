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
 * 工作流节点
 *
 * @author 
 */
@Entity
@Table(name = "workflow_node")
@TableName("workflow_node")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BpmNode extends LongStdEntity {
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
     * 节点编号
     */
    @Column(name = "`code`")
    private String code;
    
    /**
     * 操作名称
     * 操作名称，包括正向操作和逆向操作(驳回)
     */
    @Column(name = "`name`")
    private String name;
    
    /**
     * 业务编码
     */
    @Column(name = "`biz_code`")
    private String bizCode;
    
    /**
     * 状态值
     * 状态值，即数据字典中定义的值，其中文含义见数据字典表
     */
    @Column(name = "`status_val`")
    private String statusVal;

    /**
     * 准入自定义判断
     * 准入自定义判断，通过函数流来定义，该操作是否可点击
     * 定义接口，用于显示界面按钮是否可显示可操作，调用时，通过该判断返回结果确定
     */
    @Column(name = "`check_process`")
    private String checkProcess;

    /**
     * 操作自定义
     * 流程操作自定义，返回下一个流程状态的编码
     * 当界面上点击操作触发流程时，通过该操作进行处理(需要讨论诸如支付，和三方交互，这个交互过程如何处理)
     * 非自定义处理时候，这里是扩展伴随动作，需要定义是同步还是异步处理
     */
    @Column(name = "`oper_process`")
    private String operProcess;
    
    /**
     * 后续自定义处理
     * 操作完成之后的，后续处理，如发短信、微信消息等，由应用系统决定，也可以是消息广播，其他业务系统对接消息，具体由实现系统决定
     */
    @Column(name = "`after_process`")
    private String afterProcess;
    
    /**
     * 备注
     */
    @Column(name = "`note`")
    private String note;
    	
}
