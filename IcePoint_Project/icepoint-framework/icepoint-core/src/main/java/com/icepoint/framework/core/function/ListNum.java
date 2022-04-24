package com.icepoint.framework.core.function;

import com.icepoint.framework.core.support.ReadWriteProperties;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.Streams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 根据对象列表的字段值，进行可选的去重操作，并且返回最终结果的集合元素个数
 */
public class ListNum extends AbstractProcessor<ListNum.Parameters, Integer> {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Parameters {
        /**
         * Bean对象列表
         */
        private @Nullable List objects;
        /**
         * 是否去重
         */
        private boolean unique;
        /**
         * 去重字段
         */
        private @Nullable List<String> uniqueFields;
    }

    /**
     * @param parameters 参数对象
     * @return 结果列表的元素数量
     */
    @Override
    protected Integer processInternal(Parameters parameters) {

    	List objects = parameters.getObjects();
        boolean unique = parameters.isUnique();
        List<String> uniqueFields = parameters.getUniqueFields();

        // 对象列表为空，直接返回0
        if (CollectionUtils.isEmpty(objects)) {
            return 0;
        }

        // 去重
        if (unique && !CollectionUtils.isEmpty(uniqueFields)) {

            Class<?> targetClass = objects.iterator().next().getClass();
            ReadWriteProperties<?> properties = ReadWriteProperties.of(targetClass);

            // 使用getter获取uniqueFields的属性，再组合运算hashCode进行去重
            long count = Streams.stream(objects)
                    .filter(Streams.distinct(o -> Streams.stream(uniqueFields)
                            .map(f -> properties.readProperty(CastUtils.cast(o), f))
                            .map(value -> value == null ? "null".hashCode() : value.hashCode())
                            .reduce(null, Objects::hash)))
                    .count();
            return Math.toIntExact(count);

        } else {
            return objects.size();
        }
    }

    @Override
    protected Map<String, Object> wrapResult(Integer result) {
        return Collections.singletonMap("size", result);
    }

}
