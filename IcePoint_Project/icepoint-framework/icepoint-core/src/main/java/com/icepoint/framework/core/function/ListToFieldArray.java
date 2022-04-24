package com.icepoint.framework.core.function;

import com.icepoint.framework.core.support.ReadWriteProperties;
import com.icepoint.framework.core.util.Streams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListToFieldArray extends AbstractProcessor<ListToFieldArray.Parameters, List<Object>> {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Parameters {
        /**
         * 对象列表
         */
        @Nullable
        private List collection;
        /**
         * 字段名
         */
        private String field;
        /**
         * 默认值
         */
        private Object defaultValue;
        /**
         * 是否去重
         */
        private boolean unique;
    }

    /**
     * @param parameters 参数对象
     * @return 值列表
     */
    @Override
    protected List<Object> processInternal(Parameters parameters) {

    	List collection = parameters.getCollection();
        String field = parameters.getField();
        Object defaultValue = parameters.getDefaultValue();
        boolean unique = parameters.isUnique();

        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }

        Class<Object> elementType = getElementType(collection);
        ReadWriteProperties<Object> properties = ReadWriteProperties.of(elementType);

        Stream<Object> stream = Streams.stream(collection)
                .map(item -> {
                    Object obj = properties.readProperty(item, field);
                    if (null == obj) {
                        return defaultValue;
                    }
                    return obj;
                });

        if (unique) {
            stream = stream.distinct();
        }

        return stream.collect(Collectors.toList());
    }

    @Override
    protected Map<String, Object> wrapResult(List<Object> result) {
        return Collections.singletonMap("array", result);
    }

}
