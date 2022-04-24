package com.icepoint.framework.core.support;

import com.icepoint.framework.core.util.CastUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
public abstract class TypedApplicationBeanPostProcessor<T> extends ApplicationContextAwareBeanPostProcessor {

    private final Class<T> requiredBeanType;

    protected TypedApplicationBeanPostProcessor() {
        this.requiredBeanType = CastUtils.cast(
                ResolvableType.forInstance(this)
                        .as(TypedApplicationBeanPostProcessor.class)
                        .resolveGeneric(0));
        Assert.notNull(this.requiredBeanType, "TypedApplicationBeanPostProcessor泛型解析异常");
    }

    @Contract("null -> false")
    private  boolean supports(@Nullable Object bean) {
        return bean != null && requiredBeanType.isAssignableFrom(bean.getClass());
    }

    @Nullable
    @Override
    public final Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (supports(bean)) {
            return postProcessBeforeInitializationInternal(CastUtils.cast(bean), beanName); // NOSONAR
        } else {
            return bean;
        }
    }

    @Nullable
    public Object postProcessBeforeInitializationInternal(T bean, String beanName) throws BeansException {
        return bean;
    }

    @Nullable
    @Override
    public final Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (supports(bean)) {
            return postProcessAfterInitializationInternal(CastUtils.cast(bean), beanName); // NOSONAR
        } else {
            return bean;
        }
    }

    @Nullable
    public Object postProcessAfterInitializationInternal(T bean, String beanName) throws BeansException {
        return bean;
    }

}
