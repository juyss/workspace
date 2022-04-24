package com.icepoint.framework.web.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.data.domain.ColumnConstants;
import com.icepoint.framework.data.domain.PropertyConstants;
import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.security.util.SecurityUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Jiawei Zhao
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class MybatisDataAccessQueryInterceptor extends AbstractSqlParserHandler implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

        // SQL 解析
        this.sqlParser(metaObject);

        // 先判断是不是SELECT操作  (2019-04-10 00:37:31 跳过存储过程)
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (SqlCommandType.SELECT != mappedStatement.getSqlCommandType()
                || StatementType.CALLABLE == mappedStatement.getStatementType()) {
            return invocation.proceed();
        }

        // 针对定义了rowBounds，做为mapper接口方法的参数
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        Object originalParamObj = boundSql.getParameterObject();


        String id = mappedStatement.getId();
        String mapperClassName = id.substring(0, id.lastIndexOf('.'));
        String statementMethodName = id.substring(id.lastIndexOf('.') + 1);
        Class<?> mapperClass = ClassUtils.forName(mapperClassName, this.getClass().getClassLoader());
        Class<?>[] parameterTypes = getParameterTypes(originalParamObj);

        Method[] repositoryMapperMethodFound = Streams.stream(mapperClass.getMethods())
                .filter(method -> method.getName().equals(statementMethodName))
                .filter(method -> method.getDeclaringClass().isAssignableFrom(RepositoryMapper.class))
                .filter(method -> {

                    Class<?>[] pts = method.getParameterTypes();
                    if (pts.length != parameterTypes.length) {
                        return false;
                    } else if (parameterTypes.length == 0) {
                        return true;
                    } else {

                        for (int i = 0; i < pts.length; i++) {
                            Class<?> pt = pts[i];
                            Class<?> parameterType = parameterTypes[i];

                            if (!pt.isAssignableFrom(parameterType)) {
                                return false;
                            }
                        }
                        return true;
                    }
                })
                .toArray(Method[]::new);

        if (repositoryMapperMethodFound.length == 0) {
            return invocation.proceed();
        } else if (repositoryMapperMethodFound.length > 1) {
            throw new IllegalStateException("插入平台查询参数时方法解析异常");
        }

        if (originalParamObj instanceof Map) {
            Map<String, Object> paramMap = CastUtils.cast(originalParamObj);
            QueryWrapper<?> wrapper = paramMap.values().stream()
                    .filter(QueryWrapper.class::isInstance)
                    .findAny()
                    .map(QueryWrapper.class::cast)
                    .orElse(null);

            if (wrapper != null) {
                handleQueryWrapper(wrapper);
                return invocation.proceed();
            }

        } else if (originalParamObj instanceof QueryWrapper) {
            handleQueryWrapper((QueryWrapper<?>) originalParamObj);
            return invocation.proceed();
        }

        String newSql = newSql(boundSql);
        List<ParameterMapping> mappings = new ArrayList<>(boundSql.getParameterMappings());

        metaObject.setValue("delegate.boundSql.sql", newSql);
        metaObject.setValue("delegate.boundSql.parameterMappings", mappings);

        return invocation.proceed();
    }


    private String newSql(BoundSql boundSql) throws JSQLParserException {

        String sql = boundSql.getSql();

        // TODO 如果参数是实体类的情况

        Select selectStatement = (Select) CCJSqlParserUtil.parse(sql);
        PlainSelect plainSelect = (PlainSelect) selectStatement.getSelectBody();
        Expression where = plainSelect.getWhere();

        BiFunction<String, Long, Expression> eqOrNull = (column, value) -> { // NOSONAR
            StringBuilder newWhereStr = new StringBuilder(" ");
            newWhereStr.append(column);
            if (value == null) {
                newWhereStr.append(" IS NULL ");
            } else {
                newWhereStr.append("=");
                newWhereStr.append(value);
            }
            try {
                return CCJSqlParserUtil.parseCondExpression(newWhereStr.toString());
            } catch (JSQLParserException e) {
                throw new IllegalStateException("插入平台查询参数时sql解析异常");
            }
        };

        Expression ownerIdCondition = eqOrNull.apply(ColumnConstants.OWNER_ID, SecurityUtils.getOwnerId());
        Expression appIdCondition = eqOrNull.apply(ColumnConstants.APP_ID, SecurityUtils.getAppId());
        Expression platformIdCondition = eqOrNull.apply(ColumnConstants.PLATFORM_ID, SecurityUtils.getPlatformId());

        Expression newWhere = new AndExpression(new AndExpression(ownerIdCondition, appIdCondition), platformIdCondition);

        if (where == null) {
            plainSelect.setWhere(newWhere);
        } else {
            plainSelect.setWhere(new AndExpression(where, newWhere));
        }

        return selectStatement.toString();
    }

    private Class<?>[] getParameterTypes(Object parameter) {
        if (parameter instanceof MapperMethod.ParamMap) {
            MapperMethod.ParamMap<?> parameterMap = (MapperMethod.ParamMap<?>) parameter;
            return parameterMap.values().stream()
                    .map(Object::getClass)
                    .toArray(Class[]::new);
        } else if (parameter instanceof ParameterMap) {
            ParameterMap parameterMap = (ParameterMap) parameter;
            return parameterMap.getParameterMappings().stream()
                    .map(ParameterMapping::getJavaType)
                    .toArray(Class[]::new);
        } else {
            return new Class[]{ parameter.getClass() };
        }
    }

    private void handleQueryWrapper(QueryWrapper<?> queryWrapper) {
        Map<String, Object> paramMap = queryWrapper.getParamNameValuePairs();

        if (!paramMap.containsKey(PropertyConstants.OWNER_ID)) {
            Long ownerId = SecurityUtils.getOwnerId();
            if (ownerId == null) {
                queryWrapper.isNull(ColumnConstants.OWNER_ID);
            } else {
                queryWrapper.eq(ColumnConstants.OWNER_ID, ownerId);
            }
        }
        if (!paramMap.containsKey(PropertyConstants.APP_ID)) {
            Long appId = SecurityUtils.getAppId();
            if (appId == null) {
                queryWrapper.isNull(ColumnConstants.APP_ID);
            } else {
                queryWrapper.eq(ColumnConstants.APP_ID, appId);
            }
        }
        if (!paramMap.containsKey(PropertyConstants.PLATFORM_ID)) {
            Long platformId = SecurityUtils.getPlatformId();
            if (platformId == null) {
                queryWrapper.isNull(ColumnConstants.PLATFORM_ID);
            } else {
                queryWrapper.eq(ColumnConstants.PLATFORM_ID, platformId);
            }
        }
    }

}
