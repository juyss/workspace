package com.icepoint.framework.core.function;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mozr
 */
public class ListsMerger {
    /**
     * 给定一组对象，以标识字段关联，合并为一组对象
     *
     * @param cover 拼接过程中若对象字段存在，是否覆盖  1-覆盖  0-不覆盖
     * @param args  偶数为List，奇数为Map的不定长参数
     * @return 对象列表
     */
    public static List<Map<String, Object>> execute(String idFields, Integer cover, Object... args) {
//        List<String> fields = Arrays.asList(idFields.split(","));
//        fields = fields.stream()
//                .filter(field -> field != null && !field.isEmpty())
//                .collect(Collectors.toList());
        Map<String, Object> record = new HashMap<>();
        Assert.isTrue(args.length % 2 == 0, "参数个数错误");
        for (int i = 0; i < args.length; i += 2) {
            Assert.isTrue(args[i] instanceof List, "参数类型错误");
            Assert.isTrue(args[i + 1] instanceof Map, "参数类型错误");
            if (args[i] instanceof List && args[i + 1] instanceof Map) {
                Map<String, String> keys = (Map<String, String>) args[i + 1];
                List<Map<String, Object>> objs = (List<Map<String, Object>>) args[i];
                keys.forEach((key, value) ->
                        objs.forEach(map -> {
                            if (1 == cover) {
                                Object obj = map.get(key);
                                if (null != obj) {
                                    record.put(value, obj);
                                }
                            } else if (0 == cover) {
                                if (null == record.get(value)) {
                                    Object obj = map.get(key);
                                    if (null != obj) {
                                        record.put(value, obj);
                                    }
                                }
                            }
                        }));
            }
        }
        List<Map<String, Object>> result = new ArrayList<>();
        result.add(record);
        return result;
    }
}
