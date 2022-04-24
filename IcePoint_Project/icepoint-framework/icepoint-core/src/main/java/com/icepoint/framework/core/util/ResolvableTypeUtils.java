package com.icepoint.framework.core.util;

import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.data.util.Lazy;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Objects;

/**
 * 通过反射解析类型的工具类，能够针对泛型类型进行解析
 *
 * @author Jiawei Zhao
 */
public class ResolvableTypeUtils {

    private ResolvableTypeUtils() {
    }

    private static final Lazy<Method> RESOLVE_VARIABLE = Lazy.of(() -> {
        Method method = ReflectionUtils.findMethod(ResolvableType.class, "resolveVariable", TypeVariable.class);
        Assert.notNull(method, "ResolvableType中找不到resolveVariable方法");
        ReflectionUtils.makeAccessible(method);
        return method;
    });

    /**
     * 获取方法的参数类型，可以解析类泛型参数
     *
     * @param method         方法对象
     * @param parameterIndex 要解析参数下标
     * @return 返回实际的参数类型
     */
    public static Class<?> getParameterType(Method method, int parameterIndex) {
        return getParameterType(method.getDeclaringClass(), method, parameterIndex);
    }

    /**
     * 获取方法的参数类型，可以解析类泛型参数
     *
     * @param targetClass    目标Class对象
     * @param method         方法对象
     * @param parameterIndex 要解析参数下标
     * @return 返回实际的参数类型
     */
    public static Class<?> getParameterType(Class<?> targetClass, Method method, int parameterIndex) {

        Assert.notNull(targetClass, "目标Class不能为空");
        Assert.notNull(method, "方法对象不能为空");

        MethodParameter methodParameter = new MethodParameter(method, parameterIndex);
        Type genericParameterType = methodParameter.getGenericParameterType();

        if (genericParameterType instanceof Class) {
            return (Class<?>) genericParameterType;
        } else if (genericParameterType instanceof TypeVariable) {

            TypeVariable<?> variable = (TypeVariable<?>) genericParameterType;
            final ResolvableType originalType = ResolvableType.forClass(targetClass);
            ResolvableType resolvableType = originalType;

            // 从继承结构解析泛型类型
            while (resolvableType != ResolvableType.NONE) {
                ResolvableType resolvedVariable = (ResolvableType) ReflectionUtils
                        .invokeMethod(RESOLVE_VARIABLE.get(), resolvableType, variable);

                if (resolvedVariable != null) {
                    return resolvedVariable.toClass();
                }

                resolvableType = resolvableType.getSuperType();
            }

            // 从接口结构解析泛型类型
            Class<?> result = Streams.stream(originalType.getInterfaces())
                    .map(type -> (ResolvableType) ReflectionUtils.invokeMethod(RESOLVE_VARIABLE.get(), type, variable))
                    .filter(Objects::nonNull)
                    .findAny()
                    .map(ResolvableType::toClass)
                    .orElse(null);

            if (result != null) {
                return result;
            }
        }

        throw new IllegalStateException("方法参数类型解析失败, 类型: " + genericParameterType);
    }
}
