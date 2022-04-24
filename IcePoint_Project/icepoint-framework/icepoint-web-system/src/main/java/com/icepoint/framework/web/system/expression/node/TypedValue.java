package com.icepoint.framework.web.system.expression.node;

import com.icepoint.framework.core.util.CastUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
public class TypedValue {

    private final Object value;

    public <T> TypedValue(@Nullable Object value, @Nullable Class<T> valueType) {

        if (value != null) {
            Assert.isTrue(value.getClass() == valueType, "表达式数据类型解析异常");
        }

        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public <T> T getValueAs() {
        return CastUtils.cast(getValue());
    }
}
