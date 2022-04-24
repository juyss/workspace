package com.github.tangyi.core.mybatis.support;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.github.tangyi.core.common.util.CheckUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @Author xh
 * @Description update方法
 * @Date 21:35 2020/11/10
 * @Param
 * @return
 **/
@Component
@Intercepts({@Signature(type = Executor.class, method = ModifyDateInterceptor.METHOD_UPDATE, args = {MappedStatement.class, Object.class})})
public class ModifyDateInterceptor implements Interceptor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String METHOD_UPDATE = "update";

    private static final Map<String, Class<?>> STATEMENT_CLASS = new ConcurrentHashMap<>();

    private static final String ENTITY_KEY = "entity";

    private static final String COLLECTION_KEY = "collection";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();


        if (SqlCommandType.SELECT.equals(sqlCommandType)) {
            return invocation.proceed();
        }

        if (CheckUtils.isNotEmpty(invocation.getArgs())) {
            Object parameter = invocation.getArgs()[1];
            if (parameter != null) {
                Class<?> clazz = STATEMENT_CLASS.get(mappedStatement.getId());
                if (clazz == null) {
                    if (parameter instanceof MapperMethod.ParamMap
                            || parameter instanceof DefaultSqlSession.StrictMap) {
                        Map<String, Object> paramMap = (Map<String, Object>) parameter;
                        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                            if (ENTITY_KEY.equals(entry.getKey())) {
                                parameter = paramMap.get(ENTITY_KEY);
                                clazz = parameter.getClass();
                                break;
                            } else if (COLLECTION_KEY.equals(entry.getKey())) {
                                parameter = paramMap.get(COLLECTION_KEY);
                                Iterator<Object> iterator = ((Collection) parameter).iterator();
                                if (iterator.hasNext()) {
                                    clazz = iterator.next().getClass();
                                    break;
                                }
                            }
                        }

                        if (clazz == null) {
                            clazz = parameter.getClass();
                        }
                    } else {
                        clazz = parameter.getClass();
                    }

                    STATEMENT_CLASS.put(mappedStatement.getId(), clazz);
                }

                if (!BaseEntity.class.isAssignableFrom(clazz)) {
                    return invocation.proceed();
                }

                if (parameter instanceof Map) {
                    Map paramMap = (Map) parameter;
                    Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
                    for (Map.Entry<String, Object> entry : entrySet) {
                        if (ENTITY_KEY.equals(entry.getKey())) {
                            parameter = paramMap.get(ENTITY_KEY);
                            break;
                        } else if (COLLECTION_KEY.equals(entry.getKey())) {
                            parameter = paramMap.get(COLLECTION_KEY);
                        }
                    }
                }

                if (parameter instanceof Collection) {
                    savePrepare(sqlCommandType, (Collection<BaseEntity>) parameter);
                } else if (parameter instanceof BaseEntity) {
                    savePrepare(sqlCommandType, (BaseEntity) parameter);
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 保存前准备
     *
     * @param sqlCommandType
     * @param collection
     */
    private void savePrepare(SqlCommandType sqlCommandType, Collection<BaseEntity> collection) {
        for (BaseEntity item : collection) {
            savePrepare(sqlCommandType, item);
        }
    }

    /**
     * 保存前准备
     *
     * @param sqlCommandType
     * @param parameter
     */
    private void savePrepare(SqlCommandType sqlCommandType, BaseEntity parameter) {
        if (SqlCommandType.UPDATE == sqlCommandType) {
            parameter.setModifyDate(new Date());
        }
    }

}
