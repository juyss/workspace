package com.icepoint.framework.web.system.model;

import lombok.Data;

/**
 * @author ck
 * @version 1.0
 * @date 2021/5/19 11:36
 */
@Data
public class Table {
    /**
     * 表名
     */
    private String Name;
    /**
     * 中文名称
     */
    private String nameEn;
    /**
     * 主键列名
     */
    private String  pkField;
    /**
     * 表列
     */
    private Column[] cols;
    /**
     * 表描述
     */
    private String description;

}
