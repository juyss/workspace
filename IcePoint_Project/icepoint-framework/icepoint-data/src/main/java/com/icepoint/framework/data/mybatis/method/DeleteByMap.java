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
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Map;

/**
 * 根据columnMap 条件删除记录
 *
 * @author hubin
 * @since 2018-04-06
 */
public class DeleteByMap extends DefinedMethod {

    @Override
    protected String getMethod(TableInfo info) {
        return "deleteByMap";
    }

    @Override
    protected String getSql(Class<?> mapperClass, Class<?> modelClass, TableInfo info) {
        if (info.isLogicDelete()) {
            return String.format(SqlMethod.LOGIC_DELETE_BY_MAP.getSql(), info.getTableName(), sqlLogicSet(info),
                    sqlWhereByMap(info));
        } else {
            return String.format(SqlMethod.DELETE_BY_MAP.getSql(), info.getTableName(), this.sqlWhereByMap(info));
        }
    }

    @Override
    protected MappedStatement injectMappedStatementInternal(String method, SqlSource sqlSource, Class<?> mapperClass,
            Class<?> modelClass, TableInfo info) {

        return info.isLogicDelete()
                ? addUpdateMappedStatement(mapperClass, Map.class, method, sqlSource)
                : this.addDeleteMappedStatement(mapperClass, method, sqlSource);
    }

}
