package com.icepoint.framework.workorder.config.agilebpm;

import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.data.util.SqlUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public interface DataConverter {

    Map<String, Object> getData();

    void setData(Map<String, Object> data);

    default <T> T getDataAsBean(Class<T> beanType) {
        Map<String, Object> data = getData();
        if (data == null) {
            return null;
        }

        Map<String, Object> result = new LinkedHashMap<>();
        data.forEach((name, value) -> result.put(SqlUtils.toEntityField(name), value));
        return BeanUtils.toBean(result, beanType);
    }

    default void setBeanAsData(Object bean) {
        if (bean == null) {
            setData(null);
        }

        Map<String, Object> map = BeanUtils.toMap(bean, true);
        if (map.isEmpty()) {
            setData(map);
        }

        Map<String, Object> result = new LinkedHashMap<>();
        map.forEach((column, value) -> result.put(SqlUtils.toEntityField(column), value));
        setData(result);
    }
}
