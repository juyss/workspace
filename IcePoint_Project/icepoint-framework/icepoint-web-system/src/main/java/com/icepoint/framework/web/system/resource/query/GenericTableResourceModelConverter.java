package com.icepoint.framework.web.system.resource.query;

import com.icepoint.framework.core.util.SimpleTypeUtils;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.GenericData;
import com.icepoint.framework.web.system.resource.DefaultResourceModelConverter;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.MapResourceModel;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.util.SystemResourceUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
public class GenericTableResourceModelConverter extends DefaultResourceModelConverter {

    public List<ResourceModel> convert(Lookup lookup, List<GenericData> dataList) {

        Map<String, FieldMetadata> fieldsByName = Streams.stream(lookup.getMetadata().getFields())
                .collect(Collectors.toMap(SystemResourceUtils::getFieldName, field -> field));

        Map<String, List<GenericData>> dataListByNo = dataList.stream()
                .collect(Collectors.groupingBy(GenericData::getNo));

        return Streams.stream(dataListByNo.values())
                .map(list -> convertAsSingleModel(lookup, fieldsByName, list))
                .collect(Collectors.toList());
    }

    private ResourceModel convertAsSingleModel(Lookup lookup, Map<String, FieldMetadata> fieldsByName,
            List<GenericData> dataList) {

        MapResourceModel model = new MapResourceModel(lookup);

        dataList.forEach(data -> {

            String name = data.getName();
            FieldMetadata field = fieldsByName.get(name);
            if (field == null) {
                return;
            }

            String value = Boolean.TRUE.equals(data.getBig()) ? data.getBigValue() : data.getValue();

            // TODO: 增加验证
            if (Boolean.TRUE.equals(field.getPrimaryKey())) {
                model.setId(SimpleTypeUtils.parseLong(value));
            } else {
                model.setProperty(name, value);
            }
        });

        return model;
    }
}
