package com.icepoint.framework.core.function;

import com.icepoint.framework.core.util.PropertyDescriptorUtils;
import com.icepoint.framework.core.util.Streams;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class ParametersConverter {

    /**
     * 字段的映射列表，key是result的key，value是参数的字段名
     */
    private final Map<String, String> mapper;

    public ParametersConverter() {
        this(null);
    }

    public ParametersConverter(@Nullable Map<String, String> mapper) {
        this.mapper = mapper;
    }

    public <P> P convert(Class<P> parametersType, Map<String, Object> result) {

        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(parametersType);
        P parameters = BeanUtils.instantiateClass(parametersType);

        if (descriptors.length == 0) {
            return parameters;
        }

        result.forEach((key, value) -> {

            if (value == null) {
                return;
            }

            // 获取对应的映射属性名，如果没有映射关系则直接作为参数的字段名
            String mappedField = mapper == null ? key : mapper.get(key);
            if (!StringUtils.hasText(mappedField)) {
                return;
            }

            Streams.stream(descriptors)
                    .filter(d -> d.getName().equals(mappedField))
                    .findAny()
                    .ifPresent(pd -> PropertyDescriptorUtils.invokeWriteMethod(pd, parameters, value, false));
        });

        return parameters;
    }

}
