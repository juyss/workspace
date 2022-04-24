package com.icepoint.framework.workorder.config.agilebpm.constant;

import lombok.Getter;

/**
 * @author Jiawei Zhao
 */
@Getter
public enum FormCategory {

    INNER("inner"),
    FRAME("frame");

    private final String value;

    FormCategory(String value) {
        this.value = value;
    }
}
