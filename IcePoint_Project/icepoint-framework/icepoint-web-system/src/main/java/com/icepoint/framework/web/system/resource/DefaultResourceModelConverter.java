package com.icepoint.framework.web.system.resource;

import com.icepoint.framework.core.util.SimpleTypeUtils;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.util.SystemResourceUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class DefaultResourceModelConverter implements ResourceModelConverter {

    @Override
    public ResourceModel convert(Lookup lookup, Map<String, Object> properties) {

        ResourceModelMetadata metadata = lookup.getMetadata();
        List<FieldMetadata> fields = metadata.getFields();

        MapResourceModel model = new MapResourceModel(lookup);

        // TODO: 增加类型转换
        Streams.stream(fields)
                .map(SystemResourceUtils::getFieldName)
                .filter(properties::containsKey)
                .forEach(field -> {
                    Object value = properties.get(field);
                    if (value != null && !SimpleTypeUtils.isSimpleType(value.getClass())) {
                        throw new IllegalArgumentException(String.format(
                                "该字段的值不是简单类型, 字段名: %s, 字段类型: %s",
                                field, value.getClass()));
                    }

                    model.setProperty(field, value);
                });

        return model;
    }
}
