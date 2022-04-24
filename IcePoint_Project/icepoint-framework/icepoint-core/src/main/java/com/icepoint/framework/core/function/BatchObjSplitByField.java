package com.icepoint.framework.core.function;

import com.icepoint.framework.core.util.MapUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * 批量对象按字段拆分
 */
public class BatchObjSplitByField extends AbstractProcessor<BatchObjSplitByField.Parameters, Map<String, Object>> {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Parameters {
        /**
         * 对象列表
         */
        private Collection<Object> objList;
        /**
         * 拆分字段
         */
        private String field;

    }

    @Override
    public Map<String, Object> processInternal(Parameters parameters) {
        Collection<Object> objList = parameters.getObjList();
        String field = parameters.getField();
        Collection<Object> list = new ArrayList<>();
        for (Object o : objList) {
            //把对象转为map
            Map<String, Object> map = MapUtils.objectToMap(o);
            list.add(map);

            if (map.containsKey(field)) {
                //获取字段
                String[] split;
                Object target = map.get(field);
                if (target.getClass().isArray()) {
                    split = (String[]) target;
                }else {
                    split = target.toString().split(",");
                }
                if (split.length > 1) {
                    for (int i = 0; i < split.length; i++) {
                        Map<String, Object> mapCopy = new HashMap<>();
                        mapCopy.putAll(map);
                        mapCopy.put(field, split[i]);
                        list.add(mapCopy);
                    }
                }
            }
        }
        return Collections.singletonMap("list", list);
    }

    @Override
    protected Map<String, Object> wrapResult(Map<String, Object> result) {
        return null;
    }

}
