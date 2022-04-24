package com.icepoint.framework.web.system.support;

import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceAccessProcessor;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.resource.query.*;
import com.icepoint.framework.web.system.util.SystemResourceUtils;

import java.util.Collections;

/**
 * 处理逻辑删除
 *
 * @author Jiawei Zhao
 */
public class LogicalResourceAccessProcessor implements ResourceAccessProcessor {

    @Override
    public void preLoad(Lookup lookup) {
        SystemResourceUtils.getUniqueDeleteMarker(lookup.getMetadata().getFields())
                .ifPresent(deleteMarker -> lookup.getQuery().accept(getVisitor(deleteMarker)));
    }

    @Override
    public void prePersist(ResourceModel model) {
        SystemResourceUtils.getUniqueDeleteMarker(model.getLookup().getMetadata().getFields())
                .map(SystemResourceUtils::getFieldName)
                .filter(deleteMarker -> model.getProperty(deleteMarker) == null)
                .ifPresent(deleteMarker -> model.setProperty(deleteMarker, 0));
    }

    private ConditionAddingVisitor getVisitor(FieldMetadata deleteMarker) {
        Parameter parameter = new Parameter(
                Integer.class,
                SystemResourceUtils.getFieldName(deleteMarker),
                0,
                Operation.EQ,
                LogicCondition.AND);

        return new ConditionAddingVisitor(Collections.singletonList(parameter));
    }
}
