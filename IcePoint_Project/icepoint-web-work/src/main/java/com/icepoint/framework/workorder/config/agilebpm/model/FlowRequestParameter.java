package com.icepoint.framework.workorder.config.agilebpm.model;

import com.icepoint.framework.workorder.config.agilebpm.constant.FormCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
@Data
public class FlowRequestParameter {

    @ApiModelProperty("流程定义id，流程启动等场景必须")
    private String defId;
    @ApiModelProperty("流程实例id，流程草稿等场景")
    private String instanceId;
    @ApiModelProperty("流程任务id，流程任务处理时必须")
    private String taskId;
    private String nodeId;
    @NotBlank
    @ApiModelProperty("action name 必须")
    private String action;
    @ApiModelProperty("前端节点人员设置")
    private Object nodeUsers;
    @ApiModelProperty("流程业务数据，JSON格式：{boCodeA:{},boCodeB:{}}")
    private Map<String, Object> data;
    @ApiModelProperty("流程业务主键。 URL表单，可以直接赋值调用rest接口启动流程")
    private String businessKey;
    @ApiModelProperty("表单类型")
    private String formType;
    @ApiModelProperty("流程任务审批意见")
    private String opinion;
    @ApiModelProperty("目标节点")
    private String destination;
    @ApiModelProperty("特殊属性扩展配置：可以再 ActionCmd 中拿到此配置")
    private Object extendConf;

    public FlowRequestParameter(String taskId, String action, Map<String, Object> data, String opinion) {
        this.formType = FormCategory.INNER.getValue();
        this.taskId = taskId;
        this.action = action;
        this.data = data;
        this.opinion = opinion;
    }

    public FlowRequestParameter(String defId, String action, Map<String, Object> data) {
        this.formType = FormCategory.INNER.getValue();
        this.defId = defId;
        this.action = action;
        this.data = data;
    }
}
