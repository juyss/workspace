package com.icepoint.base.config.mybatis.pageable;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

public class PageSupportObjectFactory extends DefaultObjectFactory {

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type) || Page.class.isAssignableFrom(type);
    }

    @Override
    protected Class<?> resolveInterface(Class<?> type) {
        Class<?> classToCreate = super.resolveInterface(type);
        return classToCreate == Page.class ? ArrayList.class : classToCreate;
    }
}
