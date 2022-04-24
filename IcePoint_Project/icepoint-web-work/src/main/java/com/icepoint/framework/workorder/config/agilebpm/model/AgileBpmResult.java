package com.icepoint.framework.workorder.config.agilebpm.model;

import lombok.Data;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
@Data
public class AgileBpmResult {

    private String code;

    private String msg;

    private Map<String, Object> data;

    public boolean isOk() {
        return "200".equals(code);
    }
}
