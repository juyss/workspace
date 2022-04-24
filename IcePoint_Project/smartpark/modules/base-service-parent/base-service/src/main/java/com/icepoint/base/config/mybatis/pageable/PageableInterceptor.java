package com.icepoint.base.config.mybatis.pageable;

import com.icepoint.base.config.mybatis.InterceptorComponents;
import com.icepoint.base.config.mybatis.QueryInterceptorSupports;
import com.icepoint.base.config.mybatis.pageable.dialect.PageableSqlParser;
import com.icepoint.base.web.resource.component.query.*;
import com.icepoint.base.web.resource.util.MatchSqlAssistants;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对Spring Data的Pageable对象以及Page对象提供支持的拦截器，
 * 目前只支持Mysql，以后有需求的时候再提供其他数据库的支持
 *
 * @author Jiawei Zhao
 */
@SuppressWarnings("unused")
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class
        })
})
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PageableInterceptor extends QueryInterceptorSupports implements Interceptor {

    private static final String PAGEABLE_OFFSET = "PAGEABLE_OFFSET";
    private static final String PAGEABLE_PAGE = "PAGEABLE_PAGE";
    private static final String COUNT_SUFFIX = "_PAGEABLE_COUNT";
    private final PageableSqlParser sqlParser;

    @Override
    public Object intercept(Invocation invocation, InterceptorComponents components) throws Throwable {
        // 获取一些对象，在后面使用
        MappedStatement ms = components.getMappedStatement();
        Object[] args = components.getArgs();
        Object parameter = components.getParameter();
        BoundSql boundSql = components.getBoundSql();
        String sql = components.getSql();

        long count = 0L;
        boolean hasCountSelect;

        // 不是查询，不过按照拦截器的方法，这个可能性应该是0
        if (!ms.getSqlCommandType().equals(SqlCommandType.SELECT))
            return invocation.proceed();

        Pageable pageable = findParameter(parameter, Pageable.class);
        // 找不到Pageable参数，尝试查找Sort参数
        if (pageable == null) {
            Sort sort = findParameter(parameter, Sort.class);
            if (sort != null && sort.isSorted()) {
                String orderedSql = sqlParser.replaceAsOrderedSql(sql, sort);
                args[0] = createNewMappedStatementForNewSql(ms, boundSql, orderedSql);
            }
            return invocation.proceed();
        }

        // 尝试从原本的sql里找count语句，如果找不到才进行count查询，否则就直接获取sql查询的count结果作为Page对象的total
        String countFieldName = sqlParser.getCountFieldNameIfExists(sql, pageable);
        hasCountSelect = StringUtils.hasText(countFieldName);
        if (!hasCountSelect) {
            String countSql = sqlParser.replaceAsCountSql(sql, pageable);
            QueryParameter queryParameter = findParameter(parameter, QueryParameter.class);
            if (queryParameter != null) {
                countSql = getWhereSql(queryParameter, countSql);
            }
            count = executeCountQuery(components.getExecutor(), ms, parameter,
                    components.getResultHandler(), boundSql, pageable, countSql)
                    .stream()
                    .findFirst()
                    .map(Object::toString)
                    .map(Long::valueOf)
                    .orElse(0L);

            if (count == 0) {
                return PageableSupportUtils.createPage(new ArrayList<>(), pageable, () -> 0L);
            }
        }

        // 是否需要排序
        Sort sort = pageable.getSort();
        if (sort.isSorted()) {
            sql = sqlParser.replaceAsOrderedSql(sql, pageable.getSort());
        }

        // 替换成分页sql
        String pagedSql = sqlParser.replaceAsPagedSql(sql, pageable);
        args[0] = createNewMappedStatementForNewSql(ms, boundSql, pagedSql);
        List<?> resultList = (List<?>) invocation.proceed();

        // 如果select里包含count查询，则从返回结果里获取count值，否则从上面的count查询里获取
        if (hasCountSelect) {
            count = PageableSupportUtils.getCountProperty(resultList, countFieldName);
        }
        Optional<Long> countOptional = Optional.of(count);
        return PageableSupportUtils.createPage(resultList, pageable, countOptional::get);
    }

    private String getWhereSql(QueryParameter queryParameter, String sql) {
        Match match = queryParameter.getParameter(Match.class);
        if (match == null)
            return sql;
        if (match.getClass() == GenericTableMatch.class)
            return sql;

        Map<String, FieldOperation> fieldOps = match.getFieldOps();
        if (CollectionUtils.isEmpty(fieldOps))
            return sql;

        MatchOption option = match.getOption();
        List<String> whereList = new ArrayList<>();

        fieldOps.forEach((field, fieldOp) -> fieldOp.getOps().forEach((op, value) -> {
            if (option.isIgnoreNullValue() && value == null) {
                return;
            }
            if (option.isIgnoreEmptyString() && !StringUtils.hasText(value.toString())) {
                return;
            }
            String conditionSql = field + " ";
            conditionSql = MatchSqlAssistants.parseConditionSql(op, value, conditionSql);
            whereList.add(conditionSql);
        }));

        if (CollectionUtils.isEmpty(whereList)) {
            return sql;
        }

        return sql + " WHERE " + String.join(" AND ", whereList);
    }

    private List<?> executeCountQuery(
            Executor executor, MappedStatement ms,
            Object parameter, ResultHandler<?> resultHandler,
            BoundSql boundSql, Pageable pageable, String newSql)
            throws java.sql.SQLException {
        MappedStatement newMs = createCountMappedStatement(ms, ms.getId() + COUNT_SUFFIX);
        CacheKey newCacheKey = executor.createCacheKey(newMs, parameter, RowBounds.DEFAULT, boundSql);
        BoundSql newBoundSql =
                new BoundSql(newMs.getConfiguration(), newSql, boundSql.getParameterMappings(), parameter);
        copyAdditionalParameter(boundSql, newBoundSql);

        //执行查询
        return executor.query(newMs, parameter, RowBounds.DEFAULT, resultHandler, newCacheKey, newBoundSql);
    }

    private MappedStatement createCountMappedStatement(MappedStatement ms, String countMsId) {
        return newMappedStatement(
                ms.getConfiguration(),
                countMsId,
                ms.getSqlSource(),
                ms.getSqlCommandType(),
                ms.getResource(),
                ms.getFetchSize(),
                ms.getStatementType(),
                ms.getKeyGenerator(),
                ms.getKeyProperties(),
                ms.getTimeout(),
                ms.getParameterMap(),
                Collections.singletonList(
                        new ResultMap.Builder(ms.getConfiguration(), ms.getId(),
                                Long.class, new ArrayList<>()).build()
                ),
                ms.getResultSetType(),
                ms.getCache(),
                ms.isFlushCacheRequired(),
                ms.isUseCache()
        );
    }
}
