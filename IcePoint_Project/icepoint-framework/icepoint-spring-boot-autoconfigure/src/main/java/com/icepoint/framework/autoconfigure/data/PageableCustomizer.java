package com.icepoint.framework.autoconfigure.data;

import com.icepoint.framework.core.support.TypedApplicationBeanPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

/**
 * @author Jiawei Zhao
 */
public class PageableCustomizer extends TypedApplicationBeanPostProcessor<PageableHandlerMethodArgumentResolver> {

    private final SpringDataWebProperties properties;

    public PageableCustomizer(SpringDataWebProperties properties) {
        this.properties = properties;
    }


    @Override
    public Object postProcessBeforeInitializationInternal(PageableHandlerMethodArgumentResolver bean,
            String beanName) throws BeansException {

        bean.setFallbackPageable(PageRequest.of(0, properties.getPageable().getDefaultPageSize()));
        return bean;
    }
}
