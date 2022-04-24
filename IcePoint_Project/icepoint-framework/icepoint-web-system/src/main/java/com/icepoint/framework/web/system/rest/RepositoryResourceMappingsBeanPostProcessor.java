package com.icepoint.framework.web.system.rest;

import com.icepoint.framework.core.support.ApplicationContextAwareBeanPostProcessor;
import com.icepoint.framework.core.util.FieldUtils;
import com.icepoint.framework.web.system.util.EntityUrlUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.Path;
import org.springframework.data.rest.core.mapping.CollectionResourceMapping;
import org.springframework.data.rest.core.mapping.RepositoryResourceMappings;
import org.springframework.data.rest.core.mapping.ResourceMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Spring Data Rest没有开放配置动态URI的功能，这里用反射改变属性来实现
 *
 * @author Jiawei Zhao
 */
@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Component
public class RepositoryResourceMappingsBeanPostProcessor extends ApplicationContextAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (!(bean instanceof RepositoryResourceMappings)) {
            return bean;
        }

        RepositoryResourceMappings mappings = (RepositoryResourceMappings) bean;

        Field cacheField = ReflectionUtils.findField(mappings.getClass(), "cache");
        Assert.notNull(cacheField, "[RepositoryResourceMappings]中找不到属性cache");

        ReflectionUtils.makeAccessible(cacheField);
        Map<Class<?>, ResourceMetadata> cache = (Map<Class<?>, ResourceMetadata>) ReflectionUtils.getField(cacheField, mappings);

        if (cache == null) {
            return bean;
        }

        cache.forEach((clazz, metadata) -> {

            if (Repository.class.isAssignableFrom(clazz))
                return;

            String  url = EntityUrlUtils.getUrl(clazz);
            if (!StringUtils.hasText(url))
                return;

            CollectionResourceMapping mapping = FieldUtils.getRequiredField(metadata, "mapping");

            Field pathField = ReflectionUtils.findField(mapping.getClass(), "path");
            Assert.notNull(pathField, "[ResourceMapping]中找不到属性path");
            ReflectionUtils.makeAccessible(pathField);

            Field modifierField = ReflectionUtils.findField(Field.class, "modifiers");
            assert modifierField != null;

            ReflectionUtils.makeAccessible(modifierField);
            ReflectionUtils.setField(modifierField, pathField, pathField.getModifiers() & ~Modifier.FINAL);

            Path newPath = new Path(url);

            try {
                pathField.set(mapping, newPath); // NOSONAR
            } catch (IllegalAccessException e) {
                ReflectionUtils.handleReflectionException(e);
            }

        });

        return bean;
    }

}
