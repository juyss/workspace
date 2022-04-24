package com.icepoint.framework.core.support;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
public abstract class BaseApplicationBeanPostProcessor extends ApplicationContextAwareBeanPostProcessor {

    protected abstract boolean supports(@Nullable Object bean, String beanName);

    @Nullable
    @Override
    public final Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (supports(bean, beanName)) {
            return postProcessBeforeInitializationInternal(bean, beanName);
        } else {
            return bean;
        }
    }

    @Nullable
    public Object postProcessBeforeInitializationInternal(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Nullable
    @Override
    public final Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (supports(bean, beanName)) {
            return postProcessAfterInitializationInternal(bean, beanName);
        } else {
            return bean;
        }
    }

    @Nullable
    public Object postProcessAfterInitializationInternal(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
