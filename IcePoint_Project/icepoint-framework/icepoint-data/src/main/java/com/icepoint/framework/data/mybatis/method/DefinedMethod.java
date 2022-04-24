package com.icepoint.framework.data.mybatis.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author Jiawei Zhao
 */
public abstract class DefinedMethod extends AbstractMethod {

    protected static final String BLANK = " ";

    protected static final String UNSAFE_PROPERTY = "${%s}";

    protected static final String BLANK_AND_UNSAFE_PROPERTY = BLANK + UNSAFE_PROPERTY;

    protected static final String NONNULL = "%s != null";

    protected static final String NOT_EMPTY_AND_BOOLEAN = NONNULL + " and %s != '' and %s";

    protected static final String EXISTS = "_parameter instanceof java.util.Map and _parameter.containsKey('%s')";

    protected static final String EXISTS_AND_NONNULL = EXISTS + " and " + NONNULL;

    protected static final String EXISTS_AND_NONNULL_AND_PROPERTY_NONNULL = EXISTS_AND_NONNULL + " and " + NONNULL;

    protected abstract String getMethod(TableInfo info);

    protected abstract String getSql(Class<?> mapperClass, Class<?> modelClass, TableInfo info);

    @Override
    public final MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo info) {
        return injectMappedStatementInternal(
                getMethod(info),
                languageDriver.createSqlSource(configuration, getSql(mapperClass, modelClass, info), modelClass),
                mapperClass,
                modelClass,
                info
        );
    }

    protected abstract MappedStatement injectMappedStatementInternal(String method, SqlSource sqlSource,
            Class<?> mapperClass, Class<?> modelClass, TableInfo info);

    /**
     * {@inheritDoc}
     */
    @Override
    protected String sqlFirst() {
        return SqlScriptUtils.convertChoose(String.format(EXISTS_AND_NONNULL_AND_PROPERTY_NONNULL,
                WRAPPER, WRAPPER, Q_WRAPPER_SQL_FIRST),
                SqlScriptUtils.unSafeParam(Q_WRAPPER_SQL_FIRST), EMPTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String sqlSelectColumns(TableInfo table, boolean queryWrapper) {
        /* 假设存在 resultMap 映射返回 */
        String selectColumns = ASTERISK;
        if (table.getResultMap() == null || (table.getResultMap() != null && table.isInitResultMap())) {
            /* 普通查询 */
            selectColumns = table.getAllSqlSelect();
        }
        if (!queryWrapper) {
            return selectColumns;
        }
        return SqlScriptUtils.convertChoose(String.format(EXISTS_AND_NONNULL_AND_PROPERTY_NONNULL,
                WRAPPER, WRAPPER, Q_WRAPPER_SQL_SELECT),
                SqlScriptUtils.unSafeParam(Q_WRAPPER_SQL_SELECT), selectColumns);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String sqlSelectObjsColumns(TableInfo table) {
        return SqlScriptUtils.convertChoose(String.format(EXISTS_AND_NONNULL_AND_PROPERTY_NONNULL,
                WRAPPER, WRAPPER, Q_WRAPPER_SQL_SELECT),
                SqlScriptUtils.unSafeParam(Q_WRAPPER_SQL_SELECT), table.getAllSqlSelect());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String sqlComment() {
        return SqlScriptUtils.convertChoose(String.format(EXISTS_AND_NONNULL_AND_PROPERTY_NONNULL,
                WRAPPER, WRAPPER, Q_WRAPPER_SQL_COMMENT),
                SqlScriptUtils.unSafeParam(Q_WRAPPER_SQL_COMMENT), EMPTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String sqlWhereEntityWrapper(boolean newLine, TableInfo table) {
        if (table.isLogicDelete()) {
            String sqlScript = table.getAllSqlWhere(true, true, WRAPPER_ENTITY_DOT);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format(NONNULL, WRAPPER_ENTITY),
                    true);
            sqlScript += (NEWLINE + table.getLogicDeleteSql(true, true) + NEWLINE);
            String normalSqlScript = SqlScriptUtils.convertIf(String.format("AND ${%s}", WRAPPER_SQLSEGMENT),
                    String.format(NOT_EMPTY_AND_BOOLEAN, WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                            WRAPPER_NONEMPTYOFNORMAL), true);
            normalSqlScript += NEWLINE;
            normalSqlScript += SqlScriptUtils.convertIf(String.format(BLANK_AND_UNSAFE_PROPERTY, WRAPPER_SQLSEGMENT),
                    String.format(NOT_EMPTY_AND_BOOLEAN, WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                            WRAPPER_EMPTYOFNORMAL), true);
            sqlScript += normalSqlScript;
            sqlScript = SqlScriptUtils.convertChoose(String.format(EXISTS_AND_NONNULL, WRAPPER, WRAPPER), sqlScript,
                    table.getLogicDeleteSql(false, true));
            sqlScript = SqlScriptUtils.convertWhere(sqlScript);
            return newLine ? NEWLINE + sqlScript : sqlScript;
        } else {
            String sqlScript = table.getAllSqlWhere(false, true, WRAPPER_ENTITY_DOT);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format(NONNULL, WRAPPER_ENTITY), true);
            sqlScript += NEWLINE;
            sqlScript += SqlScriptUtils.convertIf(
                    String.format(SqlScriptUtils.convertIf(" AND", String.format("%s and %s", WRAPPER_NONEMPTYOFENTITY, WRAPPER_NONEMPTYOFNORMAL), false) + BLANK_AND_UNSAFE_PROPERTY, WRAPPER_SQLSEGMENT),
                    String.format(NOT_EMPTY_AND_BOOLEAN, WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                            WRAPPER_NONEMPTYOFWHERE), true);
            sqlScript = SqlScriptUtils.convertWhere(sqlScript) + NEWLINE;
            sqlScript += SqlScriptUtils.convertIf(String.format(BLANK_AND_UNSAFE_PROPERTY, WRAPPER_SQLSEGMENT),
                    String.format(NOT_EMPTY_AND_BOOLEAN, WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                            WRAPPER_EMPTYOFWHERE), true);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format(EXISTS_AND_NONNULL, WRAPPER, WRAPPER), true);
            return newLine ? NEWLINE + sqlScript : sqlScript;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String sqlSet(boolean logic, boolean ew, TableInfo table, boolean judgeAliasNull, final String alias,
            final String prefix) {
        String sqlScript = table.getAllSqlSet(logic, prefix);
        if (judgeAliasNull) {
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format(NONNULL, alias), true);
        }
        if (ew) {
            sqlScript += NEWLINE;
            sqlScript += SqlScriptUtils.convertIf(SqlScriptUtils.unSafeParam(U_WRAPPER_SQL_SET),
                    String.format(EXISTS_AND_NONNULL_AND_PROPERTY_NONNULL,
                            WRAPPER, WRAPPER, U_WRAPPER_SQL_SET), false);
        }
        sqlScript = SqlScriptUtils.convertSet(sqlScript);
        return sqlScript;
    }
}
