package com.icepoint.framework.core.function;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapJoin extends AbstractProcessor<MapJoin.Parameters, Map<String, Object>> {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Parameters {
        /**
         * 左对象
         */
        private Map<String, Object> mapLeft;
        /**
         * 右对象
         */
        private Map<String, Object> mapRight;
        /**
         * 右对象合并到最对象时的映射字段，如果Map为空则以原字段名，合并所有字段
         */
        @Nullable
        private Map<String, String> rFieldMap;
        /**
         * 若左对象字段存在，是否覆盖
         */
        private boolean cover;
    }

    /**
     * 对象合并器
     * 两个对象关联合并为一个对象
     *
     * @param parameters 参数对象
     * @return map        返回合并后的左对象
     */
    @Override
    protected Map<String, Object> processInternal(Parameters parameters) {

        Map<String, Object> mapLeft = parameters.getMapLeft();
        Map<String, Object> mapRight = parameters.getMapRight();
        Map<String, String> rFieldMap = parameters.getRFieldMap();
        boolean cover = parameters.isCover();

        Map<String, Object> map = new HashMap<>(mapLeft);

        if (!CollectionUtils.isEmpty(rFieldMap)) {
            rFieldMap.forEach((key, value) -> {
                if (mapRight.containsKey(key)) {
                    if (cover) {
                        mapLeft.put(value, mapRight.get(key));
                    } else {
                        mapLeft.putIfAbsent(value, mapRight.get(key));
                    }
                }
            });
        } else {
            if (cover) {
                mapLeft.putAll(mapRight);
            } else {
                mapRight.forEach(mapLeft::putIfAbsent);
            }
        }

        return map;
    }

    @Override
    protected Map<String, Object> wrapResult(Map<String, Object> result) {
        return Collections.singletonMap("map", result);
    }

}
