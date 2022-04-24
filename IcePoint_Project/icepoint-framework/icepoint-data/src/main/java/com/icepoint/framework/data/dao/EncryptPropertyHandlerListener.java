package com.icepoint.framework.data.dao;

import com.icepoint.framework.data.annotation.EncryptProperty;
import com.icepoint.framework.data.domain.BaseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * TODO: 加密解密
 *
 * @author Jiawei Zhao
 */
public class EncryptPropertyHandlerListener {

    enum Type {
        ENCRYPT, DECRYPT
    }

    @PreUpdate
    @PrePersist
    public void encrypt(Object o) {
        process(o, Type.ENCRYPT);
    }

    @PostLoad
    public void decrypt(@Nullable Object o) {
        process(o, Type.DECRYPT);
    }

    private void process(@Nullable Object o, Type type) {
        if (o == null)
            return;

//        doProcess(o, type, new HashSet<>());
    }

    private void doProcess(Object o, Type type, Set<Object> resolved) {

        if (resolved.contains(o)) {
            return;
        }

        Class<?> clazz = o.getClass();
        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(clazz);
        if (descriptors.length <= 0)
            return;

        for (PropertyDescriptor descriptor : descriptors) {
            String name = descriptor.getName();
            Field field = ReflectionUtils.findField(clazz, name);
            if (field == null) {
                continue;
            }

            Class<?> fieldType = field.getType();
            Method readMethod = descriptor.getReadMethod();
            Object property = ReflectionUtils.invokeMethod(readMethod, o);
            if (property == null) {
                continue;
            }

            resolved.add(property);

            if (BaseEntity.class.isAssignableFrom(fieldType)) {
                doProcess(property, type, resolved);
            } else if (Collection.class.isAssignableFrom(fieldType)) {
                ((Collection<?>) property).forEach(item -> doProcess(item, type, resolved));
            } else if (Map.class.isAssignableFrom(fieldType)) {
                ((Map<?, ?>) property).values().forEach(item -> doProcess(item, type, resolved));
            }

            EncryptProperty annotation = AnnotationUtils.findAnnotation(field, EncryptProperty.class);
            String newProperty;
            if (annotation != null) {
                Assert.isTrue(String.class == fieldType, "加密字段只支持字符串类型");
                switch (type) {
                    case ENCRYPT:
                        newProperty = encrypt(annotation, (String) property);
                        break;
                    case DECRYPT:
                        newProperty = decrypt(annotation, (String) property);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + type);
                }

                Method writeMethod = descriptor.getWriteMethod();
                ReflectionUtils.invokeMethod(writeMethod, o, newProperty);
            }
        }
    }

    private String encrypt(EncryptProperty annotation, String property) {
        return "";
    }

    private String decrypt(EncryptProperty annotation, String encrypted) {
        return "";
    }
}
