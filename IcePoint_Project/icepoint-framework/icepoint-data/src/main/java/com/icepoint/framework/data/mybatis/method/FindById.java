package com.icepoint.framework.data.mybatis.method;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据ID 查询一条数据
 *
 * @author hubin
 * @since 2018-04-06
 */
public class FindById extends DefinedMethod {

    @Override
    protected String getMethod(TableInfo info) {
        return "findById";
    }

    @Override
    protected String getSql(Class<?> mapperClass, Class<?> modelClass, TableInfo info) {
        return String.format(SqlMethod.SELECT_BY_ID.getSql(),
                sqlSelectColumns(info, false),
                info.getTableName(), info.getKeyColumn(), info.getKeyProperty(),
                info.getLogicDeleteSql(true, true));
    }

    @Override
    protected MappedStatement injectMappedStatementInternal(String method, SqlSource sqlSource, Class<?> mapperClass,
            Class<?> modelClass, TableInfo info) {

        return this.addSelectMappedStatementForTable(mapperClass, method, sqlSource, info);
    }
}
