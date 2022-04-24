package com.icepoint.framework.data.mybatis.pagination;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisDefaultParameterHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlParserUtils;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.MessageTemplates;
import net.sf.jsqlparser.JSQLParserException;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Lazy;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页查询拦截器的顶级父类, 统一通过Spring Data的标准进行实现, 以及具体解析sql参考了MybatisPlus和PageHelper的实现
 *
 * @author Jiawei Zhao
 * @see org.springframework.data.domain.Pageable
 * @see org.springframework.data.domain.Page
 * @see DelegatingListPage
 */
public abstract class AbstractPaginationInterceptor {

    /**
     * COUNT SQL 解析
     */
    protected ISqlParser countSqlParser;

    /**
     * 数据库类型
     */
    private Lazy<DbType> dbType = Lazy.empty();

    /**
     * 方言实现类
     */
    private Lazy<IDialect> dialect = Lazy.empty();

    public final List<?> paginate(Executor executor, Object paramObject,
            MappedStatement ms, ResultHandler<?> resultHandler, CacheKey cacheKey, BoundSql boundSql,
            Pageable pageable)
            throws SQLException, JSQLParserException {

        MetaObject boundSqlMetaObj = SystemMetaObject.forObject(boundSql);
        String originalSql = boundSql.getSql();
        Connection connection = executor.getTransaction().getConnection();
        Sort sort = pageable.getSort();
        List<?> resultList;

        // 处理分页
        if (pageable.isPaged()) {

            // 查询count
            SqlInfo sqlInfo = SqlParserUtils.getOptimizeCountSql(false, countSqlParser, originalSql);
            long count = this.queryTotal(sqlInfo.getSql(), ms, boundSql, connection);

            // 记录小于等于0返回空Page
            if (count <= 0) {
                return PageableSupportUtils.emptyPage(pageable);
            }

            long pages = count / pageable.getPageSize();
            if (pageable.getPageNumber() > pages) {
                return PageableSupportUtils.emptyPage(pageable);
            }

            String url = connection.getMetaData().getURL();
            dialect = dialect.or(() -> DialectFactory.getDialect(
                    dbType.or(() -> JdbcUtils.getDbType(url)).get()));

            // 拼接order sql
            String orderBySql = PageableSupportUtils.concatOrderBy(originalSql, sort);
            DialectModel model = dialect.get()
                    .buildPaginationSql(orderBySql, pageable.getOffset(), pageable.getPageSize());

            Configuration configuration = ms.getConfiguration();
            List<ParameterMapping> mappings = new ArrayList<>(boundSql.getParameterMappings());
            Map<String, Object> additionalParameters = CastUtils.cast(
                    boundSqlMetaObj.getValue("additionalParameters"));

            model.consumers(mappings, configuration, additionalParameters);
            boundSqlMetaObj.setValue("sql", model.getDialectSql());
            boundSqlMetaObj.setValue("parameterMappings", mappings);

            resultList = executeQuery(executor, paramObject, ms, resultHandler, cacheKey, boundSql);
            return CollectionUtils.isEmpty(resultList)
                    ? PageableSupportUtils.emptyPage(pageable)
                    : PageableSupportUtils.getPage(resultList, pageable, () -> count);
        }
        //  处理只排序的情况
        else if (sort.isSorted()) {

            String orderBySql = PageableSupportUtils.concatOrderBy(originalSql, sort);
            boundSqlMetaObj.setValue("sql", orderBySql);
            resultList = executeQuery(executor, paramObject, ms, resultHandler, cacheKey, boundSql);

        } else {

            resultList = executeQuery(executor, paramObject, ms, resultHandler, cacheKey, boundSql);
        }

        return CollectionUtils.isEmpty(resultList)
                ? PageableSupportUtils.emptyPage(pageable)
                : PageableSupportUtils.getPage(resultList, pageable, resultList::size);
    }

    /**
     * 获取Pageable对象, 可以返回null, null时不分页
     *
     * @param paramObject 参数对象, 可能是参数本身或者Map
     * @return 返回Pageable, 可以为null
     */
    @Nullable
    protected abstract Pageable getPageable(Object paramObject);

    /**
     * 查询总记录条数
     *
     * @param sql             count sql
     * @param mappedStatement MappedStatement
     * @param boundSql        BoundSql
     * @param connection      Connection
     * @return 返回记录总数
     */
    protected final long queryTotal(String sql, MappedStatement mappedStatement, BoundSql boundSql,
            Connection connection) {

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            DefaultParameterHandler parameterHandler =
                    new MybatisDefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
            parameterHandler.setParameters(statement);

            long total = 0;
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    total = resultSet.getLong(1);
                }
            }

            return total;

        } catch (Exception e) {
            throw ExceptionUtils.mpe("Error: Method queryTotal execution error of sql : \n %s \n", e, sql);
        }
    }

    protected final List<?> executeQuery(Executor executor, Object paramObject, MappedStatement ms,
            ResultHandler<?> resultHandler, CacheKey cacheKey, BoundSql boundSql) throws SQLException {

        return executor.query(ms, paramObject, RowBounds.DEFAULT, resultHandler, cacheKey, boundSql);
    }

    public void setDbType(DbType dbType) {
        Assert.notNull(dbType, MessageTemplates.notNull("dbType"));
        this.dbType = Lazy.of(dbType);
    }

    public void setDialect(IDialect dialect) {
        Assert.notNull(dialect, MessageTemplates.notNull("dialect"));
        this.dialect = Lazy.of(dialect);
    }

}
