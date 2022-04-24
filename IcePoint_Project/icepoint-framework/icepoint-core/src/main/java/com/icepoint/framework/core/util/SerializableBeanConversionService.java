package com.icepoint.framework.core.util;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

/**
 * 用来转换序列化或者反序列化的bean中字段类型
 *
 * @author Jiawei Zhao
 */
public class SerializableBeanConversionService implements ConversionService {

    private static final TypeDescriptor NUMBER_TYPE = TypeDescriptor.valueOf(Number.class);
    private static final TypeDescriptor STRING_TYPE = TypeDescriptor.valueOf(String.class);

    @Override
    public boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType) {
        return canConvert((sourceType != null ? TypeDescriptor.valueOf(sourceType) : null),
                TypeDescriptor.valueOf(targetType));
    }

    @Override
    public boolean canConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        // 参数类型和value类型不匹配的情况
        // 如果value和参数类型都是数字类型，这里做特殊的转型处理
        return sourceType == null
                || sourceType.isAssignableTo(targetType)
                || (sourceType.isAssignableTo(NUMBER_TYPE) && targetType.isAssignableTo(NUMBER_TYPE))
                || (sourceType.isAssignableTo(STRING_TYPE) && SimpleTypeUtils.isSimpleType(targetType.getType()));
    }

    @Nullable
    @Override
    public <T> T convert(@Nullable Object source, Class<T> targetType) {
        Object result = convert(source,
                source == null ? null : TypeDescriptor.valueOf(source.getClass()),
                TypeDescriptor.valueOf(targetType));
        return CastUtils.cast(result);
    }

    @Nullable
    @Override
    public Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {

        if (source == null || sourceType == null) {
            return null;
        }

        if (sourceType.isAssignableTo(targetType) && !(targetType.isPrimitive() || sourceType.isPrimitive())) {
            return targetType.getType().cast(source);
        } else {
            return SimpleTypeUtils.parse(source, targetType.getType());
        }
    }
}
