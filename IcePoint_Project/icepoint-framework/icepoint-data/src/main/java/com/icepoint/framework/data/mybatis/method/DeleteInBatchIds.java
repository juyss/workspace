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
 * 根据 ID 集合删除
 *
 * @author hubin
 * @since 2018-04-06
 */
public class DeleteInBatchIds extends DefinedMethod {

    @Override
    protected String getMethod(TableInfo info) {
        return "deleteInBatchIds";
    }

    @Override
    protected String getSql(Class<?> mapperClass, Class<?> modelClass, TableInfo info) {
        if (info.isLogicDelete()) {
            return String.format(SqlMethod.LOGIC_DELETE_BATCH_BY_IDS.getSql(), info.getTableName(), sqlLogicSet(info),
                    info.getKeyColumn(),
                    SqlScriptUtils.convertForeach("#{item}", COLLECTION, null, "item", COMMA),
                    info.getLogicDeleteSql(true, true));
        } else {
            return String.format(SqlMethod.DELETE_BATCH_BY_IDS.getSql(), info.getTableName(), info.getKeyColumn(),
                    SqlScriptUtils.convertForeach("#{item}", COLLECTION, null, "item", COMMA));
        }
    }

    @Override
    protected MappedStatement injectMappedStatementInternal(String method, SqlSource sqlSource, Class<?> mapperClass,
            Class<?> modelClass, TableInfo info) {

        return info.isLogicDelete()
                ? addUpdateMappedStatement(mapperClass, modelClass, method, sqlSource)
                : this.addDeleteMappedStatement(mapperClass, method, sqlSource);
    }

}
