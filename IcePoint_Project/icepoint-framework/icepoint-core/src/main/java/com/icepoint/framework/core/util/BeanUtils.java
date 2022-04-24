package com.icepoint.framework.core.util;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Nullable;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author Jiawei Zhao
 */
public class BeanUtils {

    public static final String NOT_EXISTS_WRITE_METHOD = "该Bean类中不存在属性'%s'对应的setter";

    private static final ConversionService CONVERSION_SERVICE = new SerializableBeanConversionService();

    private BeanUtils() {
    }

    /**
     * @see org.springframework.beans.BeanUtils#instantiateClass(Class)
     */
    public static <T> T instantiateClass(Class<T> clazz) throws BeanInstantiationException {
        return org.springframework.beans.BeanUtils.instantiateClass(clazz);
    }

    /**
     * @see org.springframework.beans.BeanUtils#instantiateClass(Class, Class)
     */
    public static <T> T instantiateClass(Class<?> clazz, Class<T> assignableTo) throws BeanInstantiationException {
        return org.springframework.beans.BeanUtils.instantiateClass(clazz, assignableTo);
    }

    /**
     * @see org.springframework.beans.BeanUtils#instantiateClass(Constructor, Object...)
     */
    public static <T> T instantiateClass(Constructor<T> ctor, Object... args) throws BeanInstantiationException {
        return org.springframework.beans.BeanUtils.instantiateClass(ctor, args);
    }

    /**
     * @see org.springframework.beans.BeanUtils#getResolvableConstructor(Class)
     */
    public static <T> Constructor<T> getResolvableConstructor(Class<T> clazz) {
        return org.springframework.beans.BeanUtils.getResolvableConstructor(clazz);
    }

    /**
     * @see org.springframework.beans.BeanUtils#findPrimaryConstructor(Class)
     */
    @Nullable
    public static <T> Constructor<T> findPrimaryConstructor(Class<T> clazz) {
        return org.springframework.beans.BeanUtils.findPrimaryConstructor(clazz);
    }

    /**
     * @see org.springframework.beans.BeanUtils#findMethod(Class, String, Class...)
     */
    @Nullable
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        return org.springframework.beans.BeanUtils.findMethod(clazz, methodName, paramTypes);
    }

    /**
     * @see org.springframework.beans.BeanUtils#findDeclaredMethod(Class, String, Class...)
     */
    @Nullable
    public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        return org.springframework.beans.BeanUtils.findDeclaredMethod(clazz, methodName, paramTypes);
    }

    /**
     * @see org.springframework.beans.BeanUtils#findMethodWithMinimalParameters(Class, String)
     */
    @Nullable
    public static Method findMethodWithMinimalParameters(Class<?> clazz, String methodName) {
        return org.springframework.beans.BeanUtils.findMethodWithMinimalParameters(clazz, methodName);
    }

    /**
     * @see org.springframework.beans.BeanUtils#findDeclaredMethodWithMinimalParameters(Class, String)
     */
    @Nullable
    public static Method findDeclaredMethodWithMinimalParameters(Class<?> clazz, String methodName) {
        return org.springframework.beans.BeanUtils.findDeclaredMethodWithMinimalParameters(clazz, methodName);
    }

    /**
     * @see org.springframework.beans.BeanUtils#findMethodWithMinimalParameters(Method[], String)
     */
    @Nullable
    public static Method findMethodWithMinimalParameters(Method[] methods, String methodName)
            throws IllegalArgumentException {
        return org.springframework.beans.BeanUtils.findMethodWithMinimalParameters(methods, methodName);
    }

    /**
     * @see org.springframework.beans.BeanUtils#resolveSignature(String, Class)
     */
    @Nullable
    public static Method resolveSignature(String signature, Class<?> clazz) {
        return org.springframework.beans.BeanUtils.resolveSignature(signature, clazz);
    }

    /**
     * @see org.springframework.beans.BeanUtils#getPropertyDescriptors(Class)
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws BeansException {
        return org.springframework.beans.BeanUtils.getPropertyDescriptors(clazz);
    }

    /**
     * @see org.springframework.beans.BeanUtils#getPropertyDescriptor(Class, String)
     */
    @Nullable
    public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName) throws BeansException {
        return org.springframework.beans.BeanUtils.getPropertyDescriptor(clazz, propertyName);
    }

    /**
     * @see org.springframework.beans.BeanUtils#findPropertyForMethod(Method)
     */
    @Nullable
    public static PropertyDescriptor findPropertyForMethod(Method method) throws BeansException {
        return org.springframework.beans.BeanUtils.findPropertyForMethod(method);
    }

    /**
     * @see org.springframework.beans.BeanUtils#findPropertyForMethod(Method, Class)
     */
    @Nullable
    public static PropertyDescriptor findPropertyForMethod(Method method, Class<?> clazz) throws BeansException {
        return org.springframework.beans.BeanUtils.findPropertyForMethod(method, clazz);
    }

    /**
     * @see org.springframework.beans.BeanUtils#findEditorByConvention(Class)
     */
    @Nullable
    public static PropertyEditor findEditorByConvention(@Nullable Class<?> targetType) {
        return org.springframework.beans.BeanUtils.findEditorByConvention(targetType);
    }

    /**
     * @see org.springframework.beans.BeanUtils#findPropertyType(String, Class...)
     */
    public static Class<?> findPropertyType(String propertyName,
            @Nullable Class<?>... beanClasses) {
        return org.springframework.beans.BeanUtils.findPropertyType(propertyName, beanClasses);
    }

    /**
     * @see org.springframework.beans.BeanUtils#getWriteMethodParameter(PropertyDescriptor)
     */
    public static MethodParameter getWriteMethodParameter(PropertyDescriptor pd) {
        return org.springframework.beans.BeanUtils.getWriteMethodParameter(pd);
    }

    /**
     * @see org.springframework.beans.BeanUtils#getParameterNames(Constructor)
     */
    public static String[] getParameterNames(Constructor<?> ctor) {
        return org.springframework.beans.BeanUtils.getParameterNames(ctor);
    }

    /**
     * @see org.springframework.beans.BeanUtils#isSimpleProperty(Class)
     */
    public static boolean isSimpleProperty(Class<?> type) {
        return org.springframework.beans.BeanUtils.isSimpleProperty(type);
    }

    /**
     * @see org.springframework.beans.BeanUtils#isSimpleValueType(Class)
     */
    public static boolean isSimpleValueType(Class<?> type) {
        return org.springframework.beans.BeanUtils.isSimpleValueType(type);
    }


    public void copyProperties(Object source, Object target) {
        copyProperties(source, target, null, false, (String[]) null);
    }

    public static void copyProperties(Object source, Object target, Class<?> editable) throws BeansException {
        copyProperties(source, target, editable, false, (String[]) null);
    }


    public static void copyProperties(Object source, Object target, String... ignoreProperties) throws BeansException {
        copyProperties(source, target, null, false, ignoreProperties);
    }

    /**
     * 拷贝属性，并且根据ignoreNullProperties决定是否忽略null值
     *
     * @param source               源对象
     * @param target               目标对象
     * @param ignoreNullProperties 是否忽略null值
     * @throws BeansException 如果拷贝属性失败
     */
    public static void copyProperties(Object source, Object target,
            boolean ignoreNullProperties) throws BeansException {
        copyProperties(source, target, null, ignoreNullProperties, (String[]) null);
    }

    /**
     * Copy the property values of the given source bean into the given target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     * <p>As of Spring Framework 5.3, this method honors generic type information
     * when matching properties in the source and target objects.
     *
     * @param source           the source bean
     * @param target           the target bean
     * @param editable         the class (or interface) to restrict property setting to
     * @param ignoreProperties array of property names to ignore
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    private static void copyProperties(Object source, Object target, @Nullable Class<?> editable,
            boolean ignoreNullProperties, @Nullable String... ignoreProperties) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();

        if (editable != null) {
            Assert.isTrue(editable.isInstance(target), "Target class [" + target.getClass().getName() +
                    "] not assignable to Editable class [" + editable.getName() + "]");
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {

            // 忽略字段跳过
            if (ignoreList != null && ignoreList.contains(targetPd.getName())) {
                continue;
            }

            // 获取目标对象写入方法
            Optional<Method> writeMethod = Optional.ofNullable(targetPd.getWriteMethod());

            // 获取源对象读取方法
            Optional<Method> readMethod = Optional.ofNullable(getPropertyDescriptor(source.getClass(), targetPd.getName()))
                    .map(PropertyDescriptor::getReadMethod);

            if (writeMethod.isPresent() && readMethod.isPresent()) {
                ResolvableType sourceResolvableType = ResolvableType.forMethodReturnType(readMethod.get());
                ResolvableType targetResolvableType = ResolvableType.forMethodParameter(writeMethod.get(), 0);

                // Ignore generic types in assignable check if either ResolvableType has unresolvable generics.
                boolean isAssignable =
                        (sourceResolvableType.hasUnresolvableGenerics() || targetResolvableType.hasUnresolvableGenerics() ?
                                ClassUtils.isAssignable(writeMethod.get().getParameterTypes()[0], readMethod.get()
                                        .getReturnType()) :
                                targetResolvableType.isAssignableFrom(sourceResolvableType));

                if (isAssignable) {
                    try {
                        ReflectionUtils.makeAccessible(readMethod.get());
                        Object value = readMethod.get().invoke(source);
                        if (value == null && ignoreNullProperties) {
                            continue;
                        }

                        ReflectionUtils.makeAccessible(writeMethod.get());
                        writeMethod.get().invoke(target, value);
                    } catch (Exception ex) {
                        throw new FatalBeanException(
                                "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                    }
                }
            }
        }
    }

    /**
     * 将Map转为Bean实例，忽略bean不包含的属性，如果字段类型不匹配将抛出异常
     *
     * @param properties 属性和值Map
     * @param beanType   要转化的bean类型
     * @param <T>        bean的类型
     * @return 转化完成的bean实例
     */
    public static <T> T toBean(Map<String, Object> properties, Class<T> beanType) {
        return toBean(properties, beanType, true, false);
    }

    /**
     * 将Map转为Bean实例
     *
     * @param properties                   属性和值Map
     * @param beanType                     要转化的bean类型
     * @param ignoreUnknownProperties      是否忽略Bean类型中不包含的属性，如果此项选为false，出现不包含的属性将抛出异常
     * @param ignoreTypeNotMatchProperties 是否忽略Bean字段类型与Map的值类型不匹配的属性，如果次选项为false，出现不匹配的属性将抛出异常
     * @param <T>                          bean的类型
     * @return 转化完成的bean实例
     */
    public static <T> T toBean(Map<String, Object> properties, Class<T> beanType, boolean ignoreUnknownProperties,
            boolean ignoreTypeNotMatchProperties) {

        T bean = instantiateClass(beanType);
        if (CollectionUtils.isEmpty(properties)) {
            return bean;
        }

        properties.forEach((name, value) -> {

            Method writeMethod = Optional.ofNullable(getPropertyDescriptor(beanType, name))
                    .map(PropertyDescriptor::getWriteMethod)
                    .orElse(null);

            if (writeMethod == null && ignoreUnknownProperties) {
                return;
            } else if (writeMethod == null) {
                throw new IllegalArgumentException(String.format(NOT_EXISTS_WRITE_METHOD, name));
            }

            TypeDescriptor parameterType = TypeDescriptor.valueOf(ResolvableTypeUtils
                    .getParameterType(beanType, writeMethod, 0));
            TypeDescriptor valueType = value == null ? null : TypeDescriptor.valueOf(value.getClass());

            Object finalValue = value;
            if (CONVERSION_SERVICE.canConvert(valueType, parameterType)) {
                finalValue = CONVERSION_SERVICE.convert(value, valueType, parameterType);
            }

            if (finalValue != null && !TypeDescriptor.valueOf(finalValue.getClass()).isAssignableTo(parameterType)) {
                if (ignoreTypeNotMatchProperties) {
                    return;
                } else {
                    throw new IllegalArgumentException(String.format(
                            "属性\"%s\"setter的参数类型与值的实际类型不相符, 也无法进行转换, 参数类型: %s, 实际类型: %s",
                            name, parameterType.getType(), valueType.getType()));
                }
            } else if (finalValue == null && parameterType.isPrimitive()) {
                return;
            }

            ReflectionUtils.invokeMethod(writeMethod, bean, finalValue);
        });

        return bean;
    }

    public static Map<String, Object> toMap(@Nullable Object bean, boolean ignoreTransientProperties,
            String... ignoreProperties) {

        if (bean == null) {
            return Collections.emptyMap();
        }

        Class<?> clazz = bean.getClass();
        PropertyDescriptor[] descriptors = getPropertyDescriptors(clazz);
        if (descriptors.length == 0) {
            return Collections.emptyMap();
        }

        Map<String, Object> map = new LinkedHashMap<>();
        for (PropertyDescriptor descriptor : descriptors) {

            String name = descriptor.getName();
            boolean isIgnoreProperty = ignoreProperties.length > 0 && ArrayUtils.contains(ignoreProperties, name);
            boolean isTransient = Optional.ofNullable(ReflectionUtils.findField(clazz, name))
                    .map(field -> Modifier.isTransient(field.getModifiers()))
                    .orElse(false);

            Method readMethod = descriptor.getReadMethod();

            if ("class".equals(name)
                    || isIgnoreProperty
                    || (ignoreTransientProperties && isTransient)
                    || readMethod == null) {
                continue;
            }

            Object value = ReflectionUtils.invokeMethod(readMethod, bean);
            map.put(name, value);
        }

        return map;
    }
}
