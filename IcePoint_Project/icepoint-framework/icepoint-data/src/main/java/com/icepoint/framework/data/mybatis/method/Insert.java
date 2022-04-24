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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 插入一条数据（选择字段插入）
 *
 * @author hubin
 * @since 2018-04-06
 */
public class Insert extends DefinedMethod {

    @Override
    protected String getMethod(TableInfo info) {
        return "insert";
    }

    @Override
    protected String getSql(Class<?> mapperClass, Class<?> modelClass, TableInfo info) {
        // 不在这里做处理
        return "";
    }

    @Override
    protected MappedStatement injectMappedStatementInternal(String method, SqlSource sqlSource, Class<?> mapperClass,
            Class<?> modelClass, TableInfo info) {

        KeyGenerator keyGenerator = NoKeyGenerator.INSTANCE;
        SqlMethod sqlMethod = SqlMethod.INSERT_ONE;
        String columnScript = SqlScriptUtils.convertTrim(info.getAllInsertSqlColumnMaybeIf(),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = SqlScriptUtils.convertTrim(info.getAllInsertSqlPropertyMaybeIf(null),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String keyProperty = null;
        String keyColumn = null;

        // 表包含主键处理逻辑,如果不包含主键当普通字段处理
        if (StringUtils.isNotBlank(info.getKeyProperty())) {
            if (info.getIdType() == IdType.AUTO) {
                /* 自增主键 */
                keyGenerator = new Jdbc3KeyGenerator();
                keyProperty = info.getKeyProperty();
                keyColumn = info.getKeyColumn();
            } else {
                if (null != info.getKeySequence()) {
                    keyGenerator = TableInfoHelper.genKeyGenerator(getMethod(sqlMethod), info, builderAssistant);
                    keyProperty = info.getKeyProperty();
                    keyColumn = info.getKeyColumn();
                }
            }
        }

        String sql = String.format(sqlMethod.getSql(), info.getTableName(), columnScript, valuesScript);
        SqlSource newSqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return this.addInsertMappedStatement(
                mapperClass,
                modelClass,
                method,
                newSqlSource,
                keyGenerator,
                keyProperty,
                keyColumn
        );
    }

}
