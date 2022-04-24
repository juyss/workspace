package com.icepoint.base.api.util.validate;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * 对象属性验证器，方便对属性中的多个属性进行不同条件的验证
 *
 * @author Jiawei Zhao
 */
public final class PropertyValidator {

    static final PropertyMapper MAPPER = PropertyMapper.get();

    private PropertyValidator() {
        throw new UnsupportedOperationException();
    }

    public static <T> MultipleValidator<T> validate(@Nullable T target) {
        Assert.notNull(target, "validate target must not be null");
        return new MultipleValidator<>(target);
    }

}
