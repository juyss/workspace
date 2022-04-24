package com.icepoint.framework.web.system.util;

import com.icepoint.framework.data.domain.PropertyConstants;

public final class SystemConstants {

    private SystemConstants() {
    }

    /**
     * 数据库表
     */
    public static final Integer GENERIC_TABLE_TYPE_TABLE = 1;

    /**
     * 服务
     */
    public static final Integer GENERIC_TABLE_TYPE_SERVICE = 2;

    /**
     * 逻辑删除查询条件
     */
    public static final String NOT_DELETED = PropertyConstants.DELETED + " = 0";

    /**
     * 通用表
     */
    public static final String GENERIC_DATA_TABLE_NAME = "sys_resource_generic_data";

    /**
     * 通用Sequence表
     */
    public static final String GENERIC_DATA_TABLE_SEQUENCE_NAME = "sys_resource_generic_data_sequence";
}
