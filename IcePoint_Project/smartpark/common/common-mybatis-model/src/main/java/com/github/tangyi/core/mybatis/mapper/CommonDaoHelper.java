package com.github.tangyi.core.mybatis.mapper;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.LogUtils;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextAware;
import tk.mybatis.mapper.util.MetaObjectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 专门用于通用Dao工厂
 *
 * @author hedongzhou
 * @since 2018/11/28
 */
public class CommonDaoHelper implements ApplicationContextAware, InitializingBean {

    /**
     * 映射关系
     */
    private static final Map<Class<?>, CommonDaoMapper> MAPPERS = new ConcurrentHashMap<>(10);
    /**
     * 映射类
     */
    private static final Class<CommonDaoMapper> MAPPER_CLASS = CommonDaoMapper.class;
    /**
     * 上下文
     */
    private org.springframework.context.ApplicationContext applicationContext;

    /**
     * 获取Mapper
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> CommonDaoMapper<T> getMapper(T model) {
        return getMapper((Class<T>) model.getClass());
    }

    /**
     * 获取Mapper
     *
     * @param modelClass
     * @param <T>
     * @return
     */
    public static <T> CommonDaoMapper<T> getMapper(Class<T> modelClass) {
        return MAPPERS.get(modelClass);
    }

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, CommonDaoMapper> beanMap = null;
        try {
            beanMap = this.applicationContext.getBeansOfType(MAPPER_CLASS);
        } catch (Exception e) {
            LogUtils.error(e);
        }

        LogUtils.info("find common dao mapper: {}", beanMap);

        if (CheckUtils.isNotEmpty(beanMap)) {
            beanMap.values()
                    .stream()
                    .forEach(commonDaoMapper -> {
                        try {
                            Class clazz = commonDaoMapper.getClass();
                            clazz = clazz.getSuperclass();
                            Field hField = clazz.getDeclaredField("h");
                            hField.setAccessible(true);
                            MapperProxy mapperProxy = (MapperProxy) hField.get(commonDaoMapper);
                            MetaObject metaObject = MetaObjectUtil.forObject(mapperProxy);
                            Class mapperInterface = (Class) metaObject.getValue("mapperInterface");
                            Type[] types = mapperInterface.getGenericInterfaces();
                            for (Type type : types) {
                                if (!(type instanceof ParameterizedType)) {
                                    continue;
                                }

                                ParameterizedType t = (ParameterizedType) type;
                                if (t.getRawType() != MAPPER_CLASS
                                        && !MAPPER_CLASS.isAssignableFrom((Class<?>) t.getRawType())) {
                                    continue;
                                }

                                Class<?> modelClass = (Class<?>) t.getActualTypeArguments()[0];
                                MAPPERS.put(modelClass, commonDaoMapper);
                            }
                        } catch (Exception e) {
                            LogUtils.error(e);
                        }
                    });
        }

        LogUtils.info("common dao mapper count: {}", MAPPERS.size());
    }

}
