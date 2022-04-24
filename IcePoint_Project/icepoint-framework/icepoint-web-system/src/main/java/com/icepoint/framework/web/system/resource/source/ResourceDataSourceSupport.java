package com.icepoint.framework.web.system.resource.source;

import com.icepoint.framework.web.core.util.ServletUtils;
import com.icepoint.framework.web.system.resource.ResourceModel;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
public abstract class ResourceDataSourceSupport {

    protected boolean isPatchRequest() {
        return Optional.ofNullable(ServletUtils.getRequest())
                .map(HttpServletRequest::getMethod)
                .map(HttpMethod::resolve)
                .map(HttpMethod.PATCH::equals)
                .orElse(Boolean.FALSE);
    }

    /**
     * 获取oldModel的数据，如果新model中不存在对应字段，则赋值到新model中
     *
     * @param model    新model
     * @param oldModel 旧model
     */
    protected void combineModel(ResourceModel model, ResourceModel oldModel) {
        oldModel.forEach((name, value) -> {
            if (!model.hasProperty(name)) {
                model.setProperty(name, value);
            }
        });
    }
}
