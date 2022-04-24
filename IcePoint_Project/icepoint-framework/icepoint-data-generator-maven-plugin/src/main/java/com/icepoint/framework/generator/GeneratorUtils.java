package com.icepoint.framework.generator;

import com.icepoint.framework.data.domain.LongBaseEntity;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.data.domain.StringBaseEntity;
import com.icepoint.framework.data.domain.StringStdEntity;
import com.icepoint.framework.generator.entity.FieldMetadata;
import com.squareup.javapoet.TypeName;

import java.math.BigDecimal;

/**
 * @author Jiawei Zhao
 */
public class GeneratorUtils {

    static TypeName getStdEntityTypeName(FieldMetadata idField) {
        Class<?> idClass = getIdType(idField);
        if (idClass == Long.class) {
            return TypeName.get(LongStdEntity.class);
        } else if (idClass == String.class) {
            return TypeName.get(StringStdEntity.class);
        } else {
            throw new IllegalStateException("不支持的主键类型: " + idClass);
        }
    }

    static TypeName getBasicEntityTypeName(FieldMetadata idField) {

        Class<?> idClass = getIdType(idField);
        if (idClass == Long.class) {
            return TypeName.get(LongBaseEntity.class);
        } else if (idClass == String.class) {
            return TypeName.get(StringBaseEntity.class);
        } else {
            throw new IllegalStateException("不支持的主键类型: " + idClass);
        }
    }

    static Class<?> extractFieldClass(FieldMetadata field) {

        String type = field.getJavaType();

        if (type.equalsIgnoreCase("byte")) {
            return Byte.class;
        } else if (type.equalsIgnoreCase("short")) {
            return Short.class;
        } else if (type.equalsIgnoreCase("int") || type.equalsIgnoreCase("Integer")) {
            return Integer.class;
        } else if (type.equalsIgnoreCase("long")) {
            return Long.class;
        } else if (type.equalsIgnoreCase("string")) {
            return String.class;
        } else if (type.equalsIgnoreCase("bool") || type.equalsIgnoreCase("boolean")) {
            return Boolean.class;
        } else if (type.equalsIgnoreCase("double")) {
            return Double.class;
        } else if (type.equalsIgnoreCase("float")) {
            return Float.class;
        } else if (type.equalsIgnoreCase("decimal")) {
            return BigDecimal.class;
        } else {
            throw new IllegalStateException("不支持的字段类型: " + type);
        }
    }

    static Class<?> getIdType(FieldMetadata field) {

        Class<?> idClass = extractFieldClass(field);

        if (idClass == Long.class) {
            return Long.class;
        } else if (idClass == String.class) {
            return String.class;
        } else {
            throw new IllegalStateException("不支持的主键类型: " + idClass);
        }
    }

    static boolean hasClass(String className, ClassLoader classLoader) {
        try {
            Class.forName(className, true, classLoader);
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }
}
