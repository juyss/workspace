package com.icepoint.framework.web.system.resource;

import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.core.util.SimpleTypeUtils;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.data.domain.PropertyConstants;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.util.SystemResourceUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Jiawei Zhao
 */
public class DefaultResourceModelViewResolver implements ResourceModelViewResolver {

    private static final String VALUE_NAME = "_value";

    @Override
    public Map<String, Object> resolve(ResourceModel model, ViewType viewType) {

        if (model.getPropertyNames().length == 0) {
            return Collections.emptyMap();
        }

        ResourceModelMetadata metadata = model.getLookup().getMetadata();
        List<FieldMetadata> fields = metadata.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return Collections.emptyMap();
        }

        Map<String, Object> view = new LinkedHashMap<>();
        switch (viewType) {
            case SIMPLE:

                for (FieldMetadata field : fields) {

                    String name = SystemResourceUtils.getFieldName(field);
                    Object value = model.getProperty(name);

                    view.put(name, resolveValue(value));
                }

                break;
            case WITH_METADATA:

                for (FieldMetadata field : fields) {

                    String name = SystemResourceUtils.getFieldName(field);
                    Object value = model.getProperty(name);

                    Map<String, Object> fieldMap = BeanUtils.toMap(field, true,
                            PropertyConstants.APP_ID, PropertyConstants.OWNER_ID,
                            PropertyConstants.OWNER_ID, PropertyConstants.CREATE_TIME,
                            PropertyConstants.CREATE_USER_ID, PropertyConstants.UPDATE_TIME,
                            PropertyConstants.UPDATE_USER_ID);

                    fieldMap.put(VALUE_NAME, resolveValue(value));

                    view.put(name, fieldMap);
                }
                break;
            default:
                throw new UnsupportedOperationException("不支持的视图类型: " + viewType);
        }

        return view;
    }

    @Override
    public Map<String, Object> resolve(ResourceModel model) {
        return resolve(model, model.getLookup().getViewType());
    }

    @Nullable
    private Object resolveValue(@Nullable Object value) {

        if (value == null) {
           return null;
        } else if (value instanceof Collection) {
            return Streams.streamable((Collection<?>) value)
                    .map(this::resolveValue)
                    .toList();
        } else if (value.getClass().isArray()) {
            return Streams.streamable((Object[]) value)
                    .map(this::resolveValue)
                    .toList();
        } else if (SimpleTypeUtils.isSimpleType(value.getClass())) {
            return SimpleTypeUtils.parseString(value);
        } else {
            throw new UnsupportedOperationException("不支持的值类型: " + value.getClass());
        }
    }
}
