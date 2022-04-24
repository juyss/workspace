package com.icepoint.framework.data.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.icepoint.framework.data.mybatis.method.DefinedMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mybatis的sql要通过注册Mapper接口+标签id的方式来找到对应的sql，
 * 这个类就是要注册{@link RepositoryMapper}的crud方法的sql。
 *
 * 具体是通过扫描与{@link DefinedMethod}同一个包内并且是其子类的实现类来获取sql的
 *
 * @author Jiawei Zhao
 */
@Component
public class RepositorySupportInjector extends AbstractSqlInjector {

    private static final String PACKAGE = DefinedMethod.class.getPackage().getName();

    private static final ClassPathScanningCandidateComponentProvider PROVIDER;

    static {
        PROVIDER = new ClassPathScanningCandidateComponentProvider(false);
        PROVIDER.addIncludeFilter(new AssignableTypeFilter(DefinedMethod.class));
        PROVIDER.addIncludeFilter((reader, factory) -> !reader.getClassMetadata().isAbstract());
    }

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {

        return PROVIDER.findCandidateComponents(PACKAGE).stream()
                .map(GenericBeanDefinition.class::cast)
                .map(definition -> {
                    try {
                        return definition.resolveBeanClass(RepositorySupportInjector.class.getClassLoader());
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(clazz -> {

                    try {
                        Constructor<?> constructor = ReflectionUtils.accessibleConstructor(clazz);
                        return BeanUtils.instantiateClass(constructor);
                    } catch (NoSuchMethodException e) {
                        throw new IllegalStateException("没有无参构造器: " + clazz);
                    }
                })
                .map(DefinedMethod.class::cast)
                .collect(Collectors.toList());
    }
}
