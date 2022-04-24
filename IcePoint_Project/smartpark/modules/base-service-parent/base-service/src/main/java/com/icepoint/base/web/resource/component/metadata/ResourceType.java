package com.icepoint.base.web.resource.component.metadata;

import java.util.Arrays;

/**
 * 资源类型
 *
 * @author Jiawei Zhao
 */
public enum ResourceType {

    /**
     * 子对象集合
     */
    CHILD_COLLECTION(0),

    /**
     * 通用表
     */
    GENERIC_TABLE(1),

    /**
     * 数据库表
     */
    DATABASE_TABLE(2),

    /**
     * 第三方服务
     */
    THIRD_PARTY(3);

    private final int code;

    ResourceType(int code) {
        this.code = code;
    }

    public static ResourceType valueOf(int code) {
        ResourceType[] values = values();
        return Arrays.stream(values)
                .filter(value -> value.getCode() == code)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("未知资源类型码: " + code));
    }

    public int getCode() {
        return code;
    }

}
