package com.icepoint.framework.web.system.resource;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public interface ResourceModelAndView {

    ResourceModel getModel();

    Map<String, Object> getView();

}
