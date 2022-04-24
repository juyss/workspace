package com.icepoint.framework.workorder.config.agilebpm.constant;

/**
 * @author Jiawei Zhao
 */
public enum TaskAction {

    AGREE("agree"),

    ;

    private final String name;

    TaskAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
