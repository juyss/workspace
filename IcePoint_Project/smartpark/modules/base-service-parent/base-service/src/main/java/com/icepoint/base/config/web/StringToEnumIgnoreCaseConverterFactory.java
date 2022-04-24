package com.icepoint.base.config.web;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.Assert;

@SuppressWarnings({"rawtypes", "unchecked"})
public class StringToEnumIgnoreCaseConverterFactory implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        Class<T> enumType = findEnumType(targetType);
        return source -> (T) EnumUtils.getEnumIgnoreCase(enumType, source.trim());
    }

    private <T extends Enum> Class<T> findEnumType(Class<T> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        Assert.notNull(enumType, "The target type " + targetType.getName() + " does not refer to an enum");
        return (Class<T>) enumType;
    }

}
