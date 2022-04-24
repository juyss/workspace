package com.icepoint.framework.web.system.resource.parser;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

/**
 * 分页sql生成器
 *
 * @author Jiawei Zhao
 */
public interface PageableSqlParser {

    String replaceAsPagedSql(String sql, Pageable pageable);

    String replaceAsOrderedSql(String sql, Sort sort);

    String replaceAsCountSql(String sql, Pageable pageable);

    @Nullable
    String getCountFieldNameIfExists(String sql, Pageable pageable);
}
