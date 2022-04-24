package com.icepoint.framework.web.system.resource.parser;

import com.icepoint.framework.web.system.resource.Lookup;
import org.springframework.data.domain.Pageable;

/**
 * 通用查询sql解析器
 *
 * @author Jiawei Zhao
 */
public interface QuerySqlParser {

    /**
     * 根据lookup中的元数据和查询参数等，解析为能够直接执行的sql
     *
     * @param lookup   查询对象
     * @param pageable 分页对象
     * @return 解析后的sql
     */
    String parse(Lookup lookup, Pageable pageable);

}
