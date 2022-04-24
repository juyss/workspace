package com.icepoint.framework.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 初始化{@link ApplicationContextUtils}。
 *
 * 用BeanFactoryPostProcessor接口实现是因为这个接口会在容器初始化初期，普通Bean还没有实例化之前初始化，
 * 方便其他Bean内部使用{@link ApplicationContextUtils}
 *
 * @author Jiawei Zhao
 */
@Component
public class ApplicationContextUtilsInitializer implements BeanFactoryPostProcessor, ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.initialize(applicationContext);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // do nothing
    }
}
