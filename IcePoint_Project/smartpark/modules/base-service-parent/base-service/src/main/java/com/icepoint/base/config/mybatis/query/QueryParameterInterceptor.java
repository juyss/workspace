package com.icepoint.base.config.mybatis.query;

import com.icepoint.base.config.mybatis.InterceptorComponents;
import com.icepoint.base.config.mybatis.QueryInterceptorSupports;
import com.icepoint.base.web.entp.map.IdentityHashMapOperate;
import com.icepoint.base.web.resource.component.generic.DatabaseTableParam;
import com.icepoint.base.web.resource.component.query.*;
import com.icepoint.base.web.resource.util.MatchSqlAssistants;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Intercepts(
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class
        })
)
@Component
public class QueryParameterInterceptor extends QueryInterceptorSupports implements Interceptor {

    private static final Pattern MAIN_TABLE_PATTERN = Pattern.compile("^(select)[.|\\s]+from\\s+(as\\s+)*\\s*.+", Pattern.CASE_INSENSITIVE);
    private static final Pattern WHERE_PATTERN = Pattern.compile("where+", Pattern.CASE_INSENSITIVE);
    private static final Pattern HAS_AS_PATTERN = Pattern.compile("\\s+as\\s+.+", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_LIMIT = Pattern.compile("(limit)\\s*[0-9]+\\s*,*\\s*[0-9]*\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_ORDER_BY = Pattern.compile("(order)\\s+(by)\\s+.+\\s+(asc|desc)*\\s*$", Pattern.CASE_INSENSITIVE);

    @Override
    public Object intercept(Invocation invocation, InterceptorComponents components) throws Throwable {
        // 获取一些对象，在后面使用
        MappedStatement ms = components.getMappedStatement();
        Object[] args = components.getArgs();
        Object parameter = components.getParameter();
        BoundSql boundSql = components.getBoundSql();
        final String sql = components.getSql();

        QueryParameter queryParameter = findParameter(parameter, QueryParameter.class);

        if (queryParameter == null)
            return invocation.proceed();

        Match match = queryParameter.getParameter(Match.class);
        if (match == null)
            return invocation.proceed();
        if (match.getClass() == GenericTableMatch.class)
            return invocation.proceed();

        Map<String, FieldOperation> fieldOps = match.getFieldOps();
        if (CollectionUtils.isEmpty(fieldOps))
            return invocation.proceed();

        MatchOption option = match.getOption();
        List<String> whereList = new ArrayList<>();
        String tableAlias = getMainTableAlias(sql).orElse("");

        fieldOps.forEach((field, fieldOp) -> fieldOp.getOps().forEach((op, value) -> {
            if (option.isIgnoreNullValue() && value == null) {
                return;
            }
            if (option.isIgnoreEmptyString() && !StringUtils.hasText(value.toString())) {
                return;
            }
            String conditionSql = tableAlias + field + " ";
            conditionSql = MatchSqlAssistants.parseConditionSql(op, value, conditionSql);
            whereList.add(conditionSql);
        }));

        StringBuilder whereSqlStringBuilder = new StringBuilder();
        final int[] index = {0};
        fieldOps.forEach((field, fieldOp) -> {
            for (int i = 0; i < whereList.size(); i++) {
                if ("0".equals(fieldOp.getField())) {
                    whereSqlStringBuilder.append(whereList.get(index[0]));
                    if (index[0] != whereList.size() - 1) {
                        whereSqlStringBuilder.append(" AND ");
                    }
                    ++index[0];
                    break;
                }
                if ("1".equals(fieldOp.getField())) {
                    whereSqlStringBuilder.append(whereList.get(index[0]));
                    if (index[0] != whereList.size() - 1) {
                        whereSqlStringBuilder.append(" OR ");
                    }
                    ++index[0];
                    break;
                }
            }
        });

        // 适配企业管理
        if (ms.getId().endsWith("DatabaseTableMapper.list") && "ent_enterprise_base".equals(((DatabaseTableParam) components.getParameterMap().get("param")).getName())) {
            String newSql = sql;
            if (!CollectionUtils.isEmpty(whereList) && whereSqlStringBuilder.length() > 0) {
                newSql = addWhereConditions(sql, whereSqlStringBuilder);
            }

            args[0] = createNewMappedStatementForNewSql(ms, boundSql, newSql);
            return invocation.proceed();
        }

        String newSql = sql;
        if (!CollectionUtils.isEmpty(whereList)) {
            newSql = addWhereConditions(sql, whereList);
        }

        args[0] = createNewMappedStatementForNewSql(ms, boundSql, newSql);
        return invocation.proceed();
    }

    /**
     * 目前先不考虑除了WHERE和ORDER之外还有其他子句
     *
     * @param sql       原本的sql
     * @param whereList where的查询条件列表
     * @return 返回生成的sql
     */
    private String addWhereConditions(String sql, List<String> whereList) {
        String newSql = sql.trim();

        Matcher limitMatcher = PATTERN_LIMIT.matcher(newSql);
        String limitSql = "";
        if (limitMatcher.find()) {
            limitSql = limitMatcher.group();
            newSql = limitMatcher.replaceAll("").trim();
        }

        Matcher orderByMatcher = PATTERN_ORDER_BY.matcher(newSql);
        String orderBySql = "";
        if (orderByMatcher.find()) {
            orderBySql = orderByMatcher.group();
            newSql = orderByMatcher.replaceAll("").trim();
        }

        Matcher matcher = WHERE_PATTERN.matcher(sql);

        String whereSql;
        if (matcher.find()) {
            whereSql = " AND ";
        } else {
            whereSql = " WHERE ";
        }
        whereSql = whereSql + String.join(" AND ", whereList);

        return String.format("%s %s %s %s", newSql, whereSql, orderBySql, limitSql);
    }

    private String addWhereConditions(String sql, StringBuilder whereSqlStringBuilder) {
        String newSql = sql.trim();

        Matcher limitMatcher = PATTERN_LIMIT.matcher(newSql);
        String limitSql = "";
        if (limitMatcher.find()) {
            limitSql = limitMatcher.group();
            newSql = limitMatcher.replaceAll("").trim();
        }

        Matcher orderByMatcher = PATTERN_ORDER_BY.matcher(newSql);
        String orderBySql = "";
        if (orderByMatcher.find()) {
            orderBySql = orderByMatcher.group();
            newSql = orderByMatcher.replaceAll("").trim();
        }

        Matcher matcher = WHERE_PATTERN.matcher(sql);

        String whereSql;
        if (matcher.find()) {
            whereSql = " AND ";
        } else {
            whereSql = " WHERE ";
        }
        whereSql = whereSql + whereSqlStringBuilder.toString();

        return String.format("%s %s %s %s", newSql, whereSql, orderBySql, limitSql);
    }

    private Optional<String> getMainTableAlias(String sql) {
        Matcher matcher = MAIN_TABLE_PATTERN.matcher(sql);
        if (matcher.find()) {
            String selectFromSql = matcher.group();
            Matcher asMatcher = HAS_AS_PATTERN.matcher(selectFromSql);
            if (asMatcher.find()) {
                String asSql = asMatcher.group();
                String result = asSql.replaceAll(" (as|AS|aS|As) ", "").trim();
                result += ".";
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }

}
