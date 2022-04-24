package com.icepoint.framework.data.mybatis.pagination;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectFactory;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * 根据不同的分页参数，调用不同分页实现类
 *
 * @author Jiawei Zhao
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }),
                @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
        }
)
public class PaginationAdapterInterceptor implements Interceptor {

    private final List<AbstractPaginationInterceptor> interceptors;

    public PaginationAdapterInterceptor(List<AbstractPaginationInterceptor> interceptors) {
        Assert.notEmpty(interceptors, "分页拦截器至少配置一个");
        this.interceptors = interceptors;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler<?> resultHandler = (ResultHandler<?>) args[3];
        Executor executor = (Executor) invocation.getTarget();
        CacheKey cacheKey;
        BoundSql boundSql;

        //由于逻辑关系，只会进入一次
        if (args.length == 4) {
            //4 个参数时
            boundSql = ms.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        } else {
            //6 个参数时
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }

        // 先判断是不是SELECT操作  (2019-04-10 00:37:31 跳过存储过程)
        if (SqlCommandType.SELECT != ms.getSqlCommandType()
                || StatementType.CALLABLE == ms.getStatementType()) {

            return invocation.proceed();
        }

        // 匹配分页拦截器
        for (AbstractPaginationInterceptor interceptor : interceptors) {
            Pageable pageable = interceptor.getPageable(parameter);
            if (pageable != null) {
                return interceptor.paginate(executor, parameter, ms, resultHandler, cacheKey, boundSql, pageable);
            }
        }

        return invocation.proceed();
    }

    /**
     * 决定此Interceptor是否进行代理的关键方法
     *
     * @param target 可以被代理的对象
     * @return 代理结果
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

        Optional.ofNullable(properties.getProperty("dialectType"))
                .map(DbType::getDbType)
                .ifPresent(dbType -> interceptors.forEach(interceptor -> interceptor.setDbType(dbType)));

        Optional.ofNullable(properties.getProperty("dialectClazz"))
                .map(DialectFactory::getDialect)
                .ifPresent(dialect -> interceptors.forEach(interceptor -> interceptor.setDialect(dialect)));
    }
}
