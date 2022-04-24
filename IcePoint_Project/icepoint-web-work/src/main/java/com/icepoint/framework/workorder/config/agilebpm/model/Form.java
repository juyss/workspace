package com.icepoint.framework.workorder.config.agilebpm.model;

import com.icepoint.framework.workorder.config.agilebpm.constant.FormCategory;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jiawei Zhao
 */
@Data
public class Form implements Serializable {

    /**
     * 表单名称描述
     */
    private String name;

    /**
     * 表单类型
     */
    private FormCategory type;

    /**
     * Inner表单Key，iframe的URL值
     */
    private String formValue;

    /**
     * Url 表单处理器
     */
    private String formHandler;

    /**
     * Inner 表单HTML 内容
     */
    private String formHtml;

    /**
     * 节点id
     */
    private String nodeId;
}
