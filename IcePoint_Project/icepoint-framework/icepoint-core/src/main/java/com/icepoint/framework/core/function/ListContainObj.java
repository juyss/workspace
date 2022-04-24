package com.icepoint.framework.core.function;

import com.icepoint.framework.core.support.ReadWriteProperties;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.Streams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 列表包含对象判断
 */
public class ListContainObj extends AbstractProcessor<ListContainObj.Parameters, Map<String, Object>> {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Parameters {
        /**
         * 对象列表
         */
        private @Nullable Collection<Object> objList;
        /**
         * 值所在字段
         */
        private String field;
        /**
         * 值列表
         */
        private @Nullable Collection<Object> values;
        /**
         * 包含判断模式，1-包含 2-不包含
         */
        private Integer type;
    }

    @Override
    public Map<String, Object> processInternal(Parameters parameters) {
        Collection<Object> objList = parameters.getObjList();
        String field = parameters.getField();
        Collection<Object> values = parameters.getValues();
        Integer type = parameters.getType();
        if (ObjectUtils.isEmpty(objList) || ObjectUtils.isEmpty(field) ||
                ObjectUtils.isEmpty(values) || ObjectUtils.isEmpty(type)) {
            return Collections.singletonMap("isEmpty", null);
        }

        Class<?> objClass = objList.iterator().next().getClass();
        ReadWriteProperties<?> properties = ReadWriteProperties.of(objClass);
        List<Object> collect = Streams.stream(objList)
                .map(f -> properties.readProperty(CastUtils.cast(f), field))
                .collect(Collectors.toList());
        boolean disjoint = Collections.disjoint(collect, values);

        if (type.equals(1)) {
            return Collections.singletonMap("isEmpty", !disjoint);
        }
        return Collections.singletonMap("isEmpty", disjoint);
    }

    @Override
    protected Map<String, Object> wrapResult(Map<String, Object> result) {
        return null;
    }


}
