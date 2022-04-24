package com.icepoint.framework.web.system.support;

import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.web.system.entity.Module;
import com.icepoint.framework.web.system.entity.TableService;
import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractEntityUrlResolver implements EntityUrlResolver {

    protected EntityUrlBuilder getBuilder(String moduleSegment, String serviceSegment) {

        Assert.hasText(moduleSegment, MessageTemplates.notNull("moduleSegment"));
        Assert.hasText(serviceSegment, MessageTemplates.notNull("serviceSegment"));

        return EntityUrlBuilder.fromUrl(moduleSegment, serviceSegment);
    }

    protected String getModuleSegment(Module module) {
        return module.getName();
    }

    protected String getServiceSegment(TableService service) {
        return service.getName();
    }
}
