package com.icepoint.framework.workorder.config.agilebpm.model;

import com.icepoint.framework.workorder.config.agilebpm.DataConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 任务数据
 *
 * @author Jiawei Zhao
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class Task implements DataConverter, Serializable {

    /**
     * 任务id
     */
    private String id;

    /**
     * 流程定义ID
     */
    private String defId;

    /**
     * 流程实例id
     */
    private String instId;

    /**
     * 节点id
     */
    private String nodeId;

    /**
     * 流程定义名字
     */
    private String defName;

    /**
     * 流程当前节点表单
     */
    private Form form;

    /**
     * 流程自定义表单业务数据
     */
    private Map<String, Object> data;

    /**
     * 流程自定义表单 权限
     */
    private Map<String, Object> permission;

    /**
     * 流程自定义表单 表权限
     */
    private Map<String, Object> tablePermission;

    /**
     * 流程自定义表单 初始化数据，可用于子表数据复制赋值
     */
    private Map<String, Object> initData;

    /**
     * 流程 当前节点按钮信息
     */
    private List<Button> buttonList;

}