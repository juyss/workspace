package com.icepoint.framework.workorder.config.agilebpm.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jiawei Zhao
 */
@Data
public class Button implements Serializable {

    /**
     * 按钮名字
     */
    private String name;

    /**
     * 按钮标识
     */
    private String alias;

    /**
     * 按钮提交前置js脚本
     */
    private String beforeScript;

    /**
     * 按钮提交后置js脚本
     */
    private String afterScript;

    /**
     * 按钮后台权限 groovy脚本
     */
    private String groovyScript;

    /**
     * 按钮对应控制器后端处理器更多配置信息页
     */
    private String configPage;
}
