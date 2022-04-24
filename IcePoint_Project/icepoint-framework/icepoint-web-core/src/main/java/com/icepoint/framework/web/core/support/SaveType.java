package com.icepoint.framework.web.core.support;

/**
 * @author Jiawei Zhao
 */
public enum SaveType {

    /**
     * 强制进行全量更新操作
     */
    FORCE_UPDATE_FULLY,

    /**
     * 强制更新非空的字段
     */
    FORCE_UPDATE_NONNULL,

    /**
     * 强制进行插入操作
     */
    FORCE_INSERT,

    /**
     * 默认
     */
    DEFAULT

}
