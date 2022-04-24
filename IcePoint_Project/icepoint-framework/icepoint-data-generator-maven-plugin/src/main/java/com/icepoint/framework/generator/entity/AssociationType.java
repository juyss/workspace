package com.icepoint.framework.generator.entity;

/**
 * @author Jiawei Zhao
 */
public enum AssociationType {

    ONE_TO_ONE("1"),
    ONE_TO_MANY("2"),
    MANY_TO_ONE("3"),
    MANY_TO_MANY("4")
    ;

    private final String code;

    AssociationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
