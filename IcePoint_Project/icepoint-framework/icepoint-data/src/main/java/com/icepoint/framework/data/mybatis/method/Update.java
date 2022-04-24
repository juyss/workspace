/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.icepoint.framework.data.mybatis.method;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据 whereEntity 条件，更新记录
 *
 * @author hubin
 * @since 2018-04-06
 */
public class Update extends DefinedMethod {

    private static final String WHERE_ID = "%s=#{%s}";

    @Override
    protected String getMethod(TableInfo info) {
        return "update";
    }

    @Override
    protected String getSql(Class<?> mapperClass, Class<?> modelClass, TableInfo info) {
        return String.format(SqlMethod.UPDATE.getSql(), info.getTableName(),
                sqlSet(true, true, info, true, ENTITY, ENTITY_DOT),
                sqlWhereEntityWrapper(true, info), sqlComment());
    }

    @Override
    protected MappedStatement injectMappedStatementInternal(String method, SqlSource sqlSource, Class<?> mapperClass,
            Class<?> modelClass, TableInfo info) {
        return this.addUpdateMappedStatement(mapperClass, modelClass, method, sqlSource);
    }

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
                    sqlWhereId(table) + table.getLogicDeleteSql(true, true));
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
            // 根据情况使用id或者ew来作为更新where条件
            sqlScript = SqlScriptUtils.convertChoose(String.format(EXISTS_AND_NONNULL, WRAPPER, WRAPPER), sqlScript,
                    NEWLINE + SqlScriptUtils.convertWhere(sqlWhereId(table)) + NEWLINE);
            return newLine ? NEWLINE + sqlScript : sqlScript;
        }
    }

    private String sqlWhereId(TableInfo info) {
        return String.format(WHERE_ID, info.getKeyColumn(), ENTITY_DOT + info.getKeyProperty());
    }
}
