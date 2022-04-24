package com.icepoint.framework.web.system.support;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractEntityUrlResolverFactory implements FactoryBean<EntityUrlResolver> {

    @Override
    public Class<?> getObjectType() {
        return EntityUrlResolver.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
