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

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 查询满足条件总记录数
 *
 * @author hubin
 * @since 2018-04-08
 */
public class ExistsById extends DefinedMethod {

    @Override
    protected String getMethod(TableInfo info) {
        return "existsById";
    }

    @Override
    protected String getSql(Class<?> mapperClass, Class<?> modelClass, TableInfo info) {
        String format = "SELECT COUNT(%s) FROM %s WHERE %s = #{%s} %s";

        return String.format(format,
                sqlCount(), info.getTableName(), info.getKeyColumn(), info.getKeyProperty(),
                info.getLogicDeleteSql(true, true));
    }

    @Override
    protected MappedStatement injectMappedStatementInternal(String method, SqlSource sqlSource, Class<?> mapperClass,
            Class<?> modelClass, TableInfo info) {

        return this.addSelectMappedStatementForOther(mapperClass, method, sqlSource, Boolean.class);
    }
}
