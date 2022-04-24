package com.icepoint.framework.data.mybatis;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisMapperAnnotationBuilder;
import com.baomidou.mybatisplus.core.MybatisMapperRegistry;
import com.baomidou.mybatisplus.core.override.MybatisMapperProxyFactory;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class MapperRegistry extends MybatisMapperRegistry {

    private final Map<Class<?>, MybatisMapperProxyFactory<?>> knownMappers = new HashMap<>();
    private final MybatisConfiguration config;

    public MapperRegistry(MybatisConfiguration config) {
        super(config);
        this.config = config;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        // 这里换成 MybatisMapperProxyFactory 而不是 MapperProxyFactory
        final MybatisMapperProxyFactory<T> mapperProxyFactory = (MybatisMapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new BindingException("Type " + type + " is not known to the MybatisPlusMapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new BindingException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    @Override
    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    @Override
    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                // 果之前注入 直接返回
                return;
                // 这里就不抛异常了
//                throw new BindingException("Type " + type + " is already known to the MapperRegistry.")
            }
            boolean loadCompleted = false;
            try {
                // 这里也换成 MybatisMapperProxyFactory 而不是 MapperProxyFactory
                knownMappers.put(type, newMybatisMapperProxyFactory(type));
                // It's important that the type is added before the parser is run
                // otherwise the binding may automatically be attempted by the
                // mapper parser. If the type is already known, it won't try.
                // 这里也换成 MybatisMapperAnnotationBuilder 而不是 MapperAnnotationBuilder
                MybatisMapperAnnotationBuilder parser = new MybatisMapperAnnotationBuilder(config, type);
                parser.parse();
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownMappers.remove(type);
                }
            }
        }
    }

    protected MybatisMapperProxyFactory<?> newMybatisMapperProxyFactory(Class<?> type) {
        return new MapperProxyFactory<>(type);
    }

    /**
     * 使用自己的 knownMappers
     */
    @Override
    public Collection<Class<?>> getMappers() {
        return Collections.unmodifiableCollection(knownMappers.keySet());
    }


}
