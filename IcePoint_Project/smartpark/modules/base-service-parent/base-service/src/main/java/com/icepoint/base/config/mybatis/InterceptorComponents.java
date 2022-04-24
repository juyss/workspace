package com.icepoint.base.config.mybatis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterceptorComponents {

    private Object[] args;
    private Executor executor;
    private MappedStatement mappedStatement;
    private Object parameter;
    private Map<String, Object> parameterMap;
    private RowBounds rowBounds;
    private ResultHandler<?> resultHandler;
    private BoundSql boundSql;
    private List<ParameterMapping> parameterMappings;
    private String sql;
}
