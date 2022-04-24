package com.icepoint.framework.core.function;

import com.icepoint.framework.core.util.CastUtils;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @author Jiawei Zhao
 */
public abstract class ProcessorSupports {

    protected <T> Class<T> getElementType(Collection<T> collection) {

        Assert.notNull(collection, "空集合无法获取元素类型");

        Class<?> clazz = null;
        for (T value : collection) {
            if (clazz == null && value != null) {
                clazz = value.getClass();
            } else if (value != null){
                Assert.isTrue(clazz == value.getClass(), "集合内的元素类型不一致");
            }
        }

        Assert.notNull(clazz, "无法解析元素类型");
        return CastUtils.cast(clazz);
    }
}
