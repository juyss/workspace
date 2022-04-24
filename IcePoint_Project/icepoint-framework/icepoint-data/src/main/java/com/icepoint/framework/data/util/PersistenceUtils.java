package com.icepoint.framework.data.util;

import org.hibernate.annotations.*;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.util.Optionals;
import org.springframework.data.util.ProxyUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Optional;

/**
 * 持久层工具类
 *
 * @author Juyss
 */
public class PersistenceUtils {

    private PersistenceUtils() {
    }

    /**
     * 判断clazz是否实体类
     *
     * @param clazz 做判断的Class对象
     * @return boolean 是实体类返回true，否则返回false
     */
    public static boolean isPersistentEntity(Class<?> clazz) {
        Entity entity = AnnotationUtils.findAnnotation(ProxyUtils.getUserClass(clazz), Entity.class);
        return entity != null;
    }

    /**
     * 判断instance是否实体类对象
     *
     * @param instance 做判断的对象
     * @return boolean 是实体类返回true，否则返回false
     */
    public static boolean isPersistentEntity(@Nullable Object instance) {
        return instance != null && isPersistentEntity(instance.getClass());
    }

    public static String getTableName(Class<?> clazz) {

        Assert.isTrue(isPersistentEntity(clazz), "该类不是实体类: " + clazz);

        Table table = AnnotationUtils.findAnnotation(ProxyUtils.getUserClass(clazz), Table.class);
        Assert.notNull(table, "找不到该实体类表名");

        String name = table.name();
        Assert.hasText(name, "表名称为空");
        return name;
    }

    public static boolean isLazyAssociationField(Field field) {

        boolean isLazy = Optionals.firstNonEmpty(
                () -> Optional.ofNullable(field.getDeclaredAnnotation(OneToOne.class)).map(OneToOne::fetch),
                () -> Optional.ofNullable(field.getDeclaredAnnotation(OneToMany.class)).map(OneToMany::fetch),
                () -> Optional.ofNullable(field.getDeclaredAnnotation(ManyToOne.class)).map(ManyToOne::fetch),
                () -> Optional.ofNullable(field.getDeclaredAnnotation(ManyToMany.class)).map(ManyToMany::fetch),
                () -> Optional.ofNullable(field.getDeclaredAnnotation(ManyToAny.class)).map(ManyToAny::fetch),
                () -> Optional.ofNullable(field.getDeclaredAnnotation(Any.class)).map(Any::fetch)
        )
                .map(FetchType.LAZY::equals).orElse(false);

        if (isLazy) {
            return true;
        }

        isLazy = !Optional.ofNullable(field.getDeclaredAnnotation(LazyToOne.class))
                .map(LazyToOne::value)
                .map(LazyToOneOption.FALSE::equals)
                .orElse(true);

        if (isLazy) {
            return true;
        }

        return Optional.ofNullable(field.getDeclaredAnnotation(Proxy.class))
                .map(Proxy::lazy)
                .orElse(false);
    }
}
