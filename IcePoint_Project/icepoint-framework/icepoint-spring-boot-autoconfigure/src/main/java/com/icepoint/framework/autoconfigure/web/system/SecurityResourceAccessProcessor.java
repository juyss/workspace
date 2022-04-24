package com.icepoint.framework.autoconfigure.web.system;

import com.icepoint.framework.data.domain.PropertyConstants;
import com.icepoint.framework.web.security.util.SecurityProperties;
import com.icepoint.framework.web.security.util.SecurityPropertiesFetcher;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceAccessProcessor;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.resource.query.ConditionAddingVisitor;
import com.icepoint.framework.web.system.resource.query.LogicCondition;
import com.icepoint.framework.web.system.resource.query.Operation;
import com.icepoint.framework.web.system.resource.query.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问{@link ResourceModel}数据之前插入的前置后置处理器
 *
 * @author Jiawei Zhao
 */
public class SecurityResourceAccessProcessor implements ResourceAccessProcessor {

    @Override
    public void preLoad(Lookup lookup) {

        SecurityProperties properties = SecurityPropertiesFetcher.fetchFromRequest();

        Long appId = properties.getAppId();
        Long ownerId = properties.getOwnerId();
        Long platformId = properties.getPlatformId();

        List<Parameter> parameters = new ArrayList<>();
        parameters.add(newParameter(PropertyConstants.APP_ID, appId));
        parameters.add(newParameter(PropertyConstants.OWNER_ID, ownerId));
        parameters.add(newParameter(PropertyConstants.PLATFORM_ID, platformId));

        lookup.getQuery().accept(new ConditionAddingVisitor(parameters));
    }

    @Override
    public void prePersist(ResourceModel model) {

        SecurityProperties properties = SecurityPropertiesFetcher.fetchFromSignedUser();

        if (model.getProperty(PropertyConstants.APP_ID) == null) {
            model.setProperty(PropertyConstants.APP_ID, properties.getAppId());
        }
        if (model.getProperty(PropertyConstants.OWNER_ID) == null) {
            model.setProperty(PropertyConstants.OWNER_ID, properties.getOwnerId());
        }
        if (model.getProperty(PropertyConstants.PLATFORM_ID) == null) {
            model.setProperty(PropertyConstants.PLATFORM_ID, properties.getPlatformId());
        }
    }

    private Parameter newParameter(String name, Long value) {
        return new Parameter(
                Long.class,
                name,
                value,
                Operation.EQ,
                LogicCondition.AND
        );
    }
}
