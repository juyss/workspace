package com.github.tangyi.core.mybatis.page;

import java.util.List;

/**
 * 分页查询
 *
 * @author hedongzhou
 * @since 2018/08/17
 */
public interface PageQuery {

    /**
     * 查询
     *
     * @return
     */
    List<?> query();

}
