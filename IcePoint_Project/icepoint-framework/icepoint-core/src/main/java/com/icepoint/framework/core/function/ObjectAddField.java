package com.icepoint.framework.core.function;

import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author Mozr
 */
public class ObjectAddField {

    /**
     * 给定一组对象，以标识字段关联，合并为一组对象
     *
     * @param resultMap 给定的集合对象
     * @param args      给定的字段-值对参数
     * @return resultMap
     */
    public static Map<String, Object> execute(Map<String, Object> resultMap, Object... args) {
        Assert.isTrue(args.length % 2 == 0, "参数数目错误");
        for (int i = 0; i < args.length; i += 2) {
            Assert.isTrue(args[i] instanceof String, "参数类型错误");
            if (args[i] instanceof String) {
                resultMap.put((String) args[i], args[i + 1]);
            }
        }
        return resultMap;
    }
}
