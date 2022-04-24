package com.icepoint.base.config.mybatis;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class QueryInterceptorSupports extends InterceptorSupports {

    @Override
    protected boolean supports(Invocation invocation) {
        Object[] args = invocation.getArgs();
        return args.length == 4
                && args[0] instanceof MappedStatement
                && (args[2] instanceof RowBounds || args[2] == null)
                && (args[3] instanceof ResultHandler || args[3] == null);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected InterceptorComponents createInterceptorComponents(Invocation invocation) {
        Object[] args = invocation.getArgs();
        Object parameter = args[1];
        MappedStatement ms = (MappedStatement) args[0];
        BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = boundSql.getSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        return InterceptorComponents.builder()
                .args(args)
                .executor((Executor) invocation.getTarget())
                .mappedStatement(ms)
                .parameter(parameter)
                .parameterMap(parameter instanceof Map ? (Map<String, Object>) parameter : null)
                .rowBounds((RowBounds) args[2])
                .resultHandler((ResultHandler<?>) args[3])
                .boundSql(boundSql)
                .sql(sql)
                .parameterMappings(parameterMappings)
                .build();
    }

    @SuppressWarnings("unchecked")
    @Nullable
    protected <T> T findParameter(@Nullable Object parameter, Class<T> requireType) {
        Assert.notNull(requireType, "requireType must not be null");
        if (parameter == null)
            return null;

        if (parameter instanceof Map) {
            Map<String, Object> parameterMap = (Map<String, Object>) parameter;
            if (!parameterMap.isEmpty()) {
                List<T> requireParameterList = parameterMap.values().stream()
                        .distinct()
                        .filter(requireType::isInstance)
                        .map(requireType::cast)
                        .collect(Collectors.toList());

                if (requireParameterList.size() == 1)
                    return requireParameterList.get(0);
                else if (requireParameterList.size() > 1)
                    throw new IllegalArgumentException(
                            "There is more than one " + requireType + " arguments in method");
            }
        } else {
            return requireType.isAssignableFrom(parameter.getClass()) ? (T) parameter : null;
        }

        return null;
    }

    protected MappedStatement newMappedStatement(
            Configuration configuration,
            String msId,
            SqlSource sqlSource,
            SqlCommandType sqlCommandType,
            String resource,
            Integer fetchSize,
            StatementType statementType,
            KeyGenerator keyGenerator,
            @Nullable String[] keyProperties,
            Integer timeout,
            ParameterMap parameterMap,
            List<ResultMap> resultMaps,
            ResultSetType resultSetType,
            Cache cache,
            boolean flushCacheRequired,
            boolean useCache)
    {
        MappedStatement.Builder builder =
                new MappedStatement.Builder(configuration, msId, sqlSource, sqlCommandType);

        builder.resource(resource);
        builder.fetchSize(fetchSize);
        builder.statementType(statementType);
        builder.keyGenerator(keyGenerator);
        if (keyProperties != null && keyProperties.length != 0) {
            StringBuilder newKps = new StringBuilder();
            for (String keyProperty : keyProperties) {
                newKps.append(keyProperty).append(",");
            }
            newKps.delete(newKps.length() - 1, newKps.length());
            builder.keyProperty(newKps.toString());
        }

        //setStatementTimeout()
        builder.timeout(timeout);

        //setStatementResultMap()
        builder.parameterMap(parameterMap);

        //setStatementResultMap()
        builder.resultMaps(resultMaps);
        builder.resultSetType(resultSetType);

        //setStatementCache()
        builder.cache(cache);
        builder.flushCacheRequired(flushCacheRequired);
        builder.useCache(useCache);

        return builder.build();
    }

    protected void copyAdditionalParameter(BoundSql source, BoundSql target) {
        for (ParameterMapping mapping : source.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (source.hasAdditionalParameter(prop)) {
                target.setAdditionalParameter(prop, source.getAdditionalParameter(prop));
            }
        }
    }

    protected MappedStatement copyMappedStatementWithNewSql(MappedStatement ms, SqlSource newSqlSource) {
        return newMappedStatement(
                ms.getConfiguration(),
                ms.getId(),
                newSqlSource,
                ms.getSqlCommandType(),
                ms.getResource(),
                ms.getFetchSize(),
                ms.getStatementType(),
                ms.getKeyGenerator(),
                ms.getKeyProperties(),
                ms.getTimeout(),
                ms.getParameterMap(),
                ms.getResultMaps(),
                ms.getResultSetType(),
                ms.getCache(),
                ms.isFlushCacheRequired(),
                ms.isUseCache()
        );
    }

    protected MappedStatement createNewMappedStatementForNewSql(MappedStatement ms, BoundSql boundSql, String newSql) {
        BoundSql newBoundSql = new BoundSql(
                ms.getConfiguration(), newSql,
                boundSql.getParameterMappings(), boundSql.getParameterObject()
        );

        // 创建新sql的MappedStatement
        MappedStatement newMs = copyMappedStatementWithNewSql(ms, new BoundSqlSqlSource(newBoundSql));
        copyAdditionalParameter(boundSql, newBoundSql);
        return newMs;
    }

    protected static class BoundSqlSqlSource implements SqlSource {
        private final BoundSql boundSql;
        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
