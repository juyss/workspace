package com.icepoint.base.web.basic.service;

import com.icepoint.base.api.util.ApplicationContextUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 复杂Service的支持类，虽然是接口，不建议在Service接口直接继承，虽然理论上没什么问题，
 * 但是会暴露很多可能是多余的方法到Service接口上。
 * <p>
 * 建议在Service的实现类上实现此接口。
 *
 * @author Jiawei Zhao
 */

public interface ComplexServiceSupports {

    @Nullable
    default <T> T getBean(Class<T> beanType) {
        Assert.notNull(beanType, "Bean type must not be null.");
        return ApplicationContextUtils.getBean(beanType);
    }

    default <T> T getRequiredBean(Class<T> beanType) {
        T bean = getBean(beanType);
        if (bean == null)
            throw new NoSuchBeanDefinitionException(beanType);
        return bean;
    }

    /**
     * 把传入的对象尽可能的解析成对应的基本类型，
     * 只有当传入的object为null时，才会返回null。
     * <p>
     * 当要解析的类型是Boolean类型的时候，可以对多种值进行解析，
     * 无视大小写，对照表如下：
     * Boolean.TRUE: "true", "1", 1, "t", "on", "yes", "y"
     * Boolean.FALSE: "false", "0", 0, "f", "off", "no", "n"
     *
     * @param <T>           解析的类型
     * @param object        要解析的对象
     * @param primitiveType 需要解析成的基本类型Class
     * @return 只有当传入的object为null时，才会返回null
     */
    @SuppressWarnings({"unchecked"})
    @Nullable
    default <T> T parsePrimitive(@Nullable Object object, Class<T> primitiveType) {
        if (object == null) {
            return null;
        } else if (object.getClass() == primitiveType) {
            return (T) object;
        } else if (Number.class.isAssignableFrom(primitiveType)) {
            return (T) NumberUtils.parseNumber(
                    object.getClass() == String.class ? ((String) object).trim() : object.toString(),
                    (Class<Number>) primitiveType
            );
        } else if (Boolean.class == primitiveType) {
            String str = object.getClass() == String.class
                    ? ((String) object).trim().toLowerCase()
                    : object.toString().toLowerCase();

            Boolean bool = BooleanUtils.toBooleanObject(str);
            bool = bool == null ? BooleanUtils.toBooleanObject(str, "1", "0", null) : bool;
            return (T) Objects.requireNonNull(bool);
        } else if (String.class == primitiveType) {
            return (T) object.toString();
        } else {
            throw new IllegalArgumentException("Unsupported primitive type: " + primitiveType);
        }
    }

    /**
     * 把传入的对象尽可能的解析成对应的基本类型，传入的是一个Supplier，可以支持更加复杂的业务场景
     *
     * @param <T>            解析的类型
     * @param objectSupplier 要解析的对象的Supplier
     * @param primitiveType  需要解析成的基本类型Class
     * @return 只有当Supplier返回的对象为null时，才会返回null
     * @see #parsePrimitive(Object, Class)
     */
    @Nullable
    default <T> T parsePrimitive(Supplier<Object> objectSupplier, Class<T> primitiveType) {
        Assert.notNull(objectSupplier, "Object supplier must not be null.");
        return parsePrimitive(objectSupplier.get(), primitiveType);
    }

    default <T> T parseRequiredPrimitive(Object object, Class<T> primitiveType) {
//        Assert.notNull(object, "Primitive object must not be null.");
        T primitive = parsePrimitive(object, primitiveType);
//        Assert.state(primitive != null, "Parse primitive fail, almost impossible exception.");
        return primitive;
    }

    default <T> T parseRequiredPrimitive(Supplier<Object> objectSupplier, Class<T> primitiveType) {
        Assert.notNull(objectSupplier, "Object supplier must not be null.");
        return parseRequiredPrimitive(objectSupplier.get(), primitiveType);
    }

    default <T> T getRequiredSingleElement(Collection<T> collection) {
        return Optional.of(collection)
                .filter(c -> c.size() == 1)
                .map(c -> c.iterator().next())
                .orElseThrow(() -> new IncorrectResultSizeDataAccessException(1, collection.size()));
    }

    default <T> T getRequiredSingleElement(Map<?, T> map) {
        return Optional.of(map)
                .filter(m -> m.size() == 1)
                .map(m -> m.values().iterator().next())
                .orElseThrow(() -> new IncorrectResultSizeDataAccessException(1, map.size()));
    }

}
