package com.icepoint.framework.web.system.resource.parser;

import com.icepoint.framework.web.system.resource.query.Operation;

/**
 * 解析{@link Operation}相关的sql的解析器
 *
 * @author Jiawei Zhao
 */
public interface OperationSqlParser {

    /**
     * 根据查询属性名、查询条件{@link Operation}和值，解析条件查询sql
     *
     * @param property 原sql
     * @param op       查询条件
     * @param value    查询值
     * @return 解析后的条件查询sql
     */
    String parse(String property, Operation op, Object value);
}
