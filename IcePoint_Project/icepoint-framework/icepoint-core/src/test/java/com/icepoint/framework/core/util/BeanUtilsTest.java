package com.icepoint.framework.core.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
@Slf4j
class BeanUtilsTest {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Entity extends LongID {
        private String str;
        private int number;
        private long bigNumber;
        private boolean bool;
    }

    public static class LongID extends ID<Long> {
    }

    @Data
    public static class ID<T> {
        private T id;
    }

    @Test
    void toBean() {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", 1);
        map.put("str", "testStr");
        map.put("number", 2);
        map.put("bigNumber", 3);
        map.put("bool", true);

        Entity entity = BeanUtils.toBean(map, Entity.class);
        log.info(entity.toString());

        Assertions.assertEquals(SimpleTypeUtils.parseLong(map.get("id")), entity.getId());
        Assertions.assertEquals(map.get("str"), entity.getStr());
        Assertions.assertEquals(map.get("number"), entity.getNumber());
        Assertions.assertEquals(SimpleTypeUtils.parseLong(map.get("bigNumber")), entity.getBigNumber());
        Assertions.assertEquals(map.get("bool"), entity.isBool());
    }


}