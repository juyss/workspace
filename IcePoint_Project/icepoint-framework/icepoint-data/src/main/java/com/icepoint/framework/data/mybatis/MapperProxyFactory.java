package com.icepoint.framework.data.mybatis;

import com.baomidou.mybatisplus.core.override.MybatisMapperMethod;
import com.baomidou.mybatisplus.core.override.MybatisMapperProxy;
import com.baomidou.mybatisplus.core.override.MybatisMapperProxyFactory;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jiawei Zhao
 */
public class MapperProxyFactory<T> extends MybatisMapperProxyFactory<T> {

    private final Class<T> mapperInterface;
    private final Map<Method, MybatisMapperMethod> methodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        super(mapperInterface);
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Map<Method, MybatisMapperMethod> getMethodCache() {
        return methodCache;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T newInstance(MybatisMapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{ mapperInterface }, mapperProxy);
    }

    protected MybatisMapperProxy<T> newProxy(SqlSession sqlSession, Class<T> mapperInterface,
            Map<Method, MybatisMapperMethod> methodCache) {

        return new MapperProxy<>(sqlSession, mapperInterface, methodCache);
    }

    @Override
    public T newInstance(SqlSession sqlSession) {
        final MybatisMapperProxy<T> mapperProxy = newProxy(sqlSession, mapperInterface, methodCache);
        return newInstance(mapperProxy);
    }

}
