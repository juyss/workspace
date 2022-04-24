package com.icepoint.framework.web.system.resource;

import lombok.Data;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
@Data
public class UnmodifiableResourceModelAndView implements ResourceModelAndView {

    private final ResourceModel model;

    private final Map<String, Object> view;
}
