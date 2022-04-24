package com.icepoint.framework.core.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 因为BeanPostProcessor会比其他业务Bean更早初始化，如果通过普通方式注入业务bean会导致注入的bean无法进行代理，
 * 从而导致有注入的bean有部分功能会失效，例如事务、异步、缓存等注解
 *
 * 这里通过ApplicationContextAware获取ApplicationContext，再通过ApplicationContext来获取容器的bean
 *
 * @author Jiawei Zhao
 */
public abstract class ApplicationContextAwareBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
