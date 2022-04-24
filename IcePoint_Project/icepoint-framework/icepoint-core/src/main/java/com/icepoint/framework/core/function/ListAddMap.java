package com.icepoint.framework.core.function;

import com.icepoint.framework.core.support.ReadWriteProperties;
import com.icepoint.framework.core.util.CastUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.ObjectUtils;

import java.util.*;

public class ListAddMap extends AbstractProcessor<ListAddMap.Parameters,  Collection<Map<String, Object>>> {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Parameters {
        /**
         * 对象列表
         */
        private @Nullable Collection<Object> list;
        /**
         * 属性对象
         */
        private @Nullable Map<String, Object> map;
        /**
         * 属性对象合并到左对象时的映射字段，如果Map为空则以原字段名，合并所有字段
         */
        private Map<String, String> fieldMap;
        /**
         * 若左对象字段存在，是否覆盖  true-覆盖  false-不覆盖
         */
        private boolean cover;
    }

    @Override
    protected Collection<Map<String, Object>> processInternal(Parameters parameters) {
         Collection<Object> objList = parameters.getList();
         Map<String, Object> objMap = parameters.getMap();
         Map<String, String> fieldMap = parameters.getFieldMap();
         boolean cover = parameters.isCover();
        if (ObjectUtils.isEmpty(objList) || ObjectUtils.isEmpty(objMap)) {
            return Collections.emptyList();
        }
         Class<?> objClass = objList.iterator().next().getClass();
         ReadWriteProperties<?> objProperties = ReadWriteProperties.of(objClass);
        Collection<Map<String, Object>> collection = new ArrayList<>(objList.size());
        for (Object t : objList) {
            Map<String, Object> map = new HashMap<>();
            objProperties.forEach(CastUtils.cast(t), map::put);
            objMap.forEach((key, value) -> {
                String field = fieldMap != null && fieldMap.containsKey(key) ? fieldMap.get(key) : key;
                if (cover) {
                    map.put(field, value);
                } else {
                    map.putIfAbsent(field, value);
                }
            });

            collection.add(map);
        }
        return collection;
    }

    @Override
    protected Map<String, Object> wrapResult(Collection<Map<String, Object>> result) {
        return Collections.singletonMap("list", result);
    }
}
