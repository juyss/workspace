package com.github.tangyi.core.mybatis.page;

import com.github.tangyi.core.common.web.PageRequest;
import com.github.tangyi.core.common.web.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;
import java.util.function.Function;

/**
 * 分页
 *
 * @author hedongzhou
 * @since 2018/08/17
 */
public class PageUtils {

    /**
     * 查询
     *
     * @param pageRequest
     * @param mapper
     * @return
     */
    public static <T extends PageRequest> PageResult query(T pageRequest,
                                                           Function<T, ? extends List<?>> mapper) {
        return query(pageRequest, () -> mapper.apply(pageRequest));
    }

    /**
     * 查询
     *
     * @param pageRequest
     * @param pageQuery
     * @return
     */
    public static PageResult query(PageRequest pageRequest,
                                   PageQuery pageQuery) {
        return query(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getDefaultLimit(), pageQuery);
    }

    /**
     * 查询
     *
     * @param page
     * @param limit
     * @param defaultLimit
     * @param pageQuery
     * @return
     */
    public static PageResult query(Integer page,
                                   Integer limit,
                                   Integer defaultLimit,
                                   PageQuery pageQuery) {
        PageResult pageResult = new PageResult(page, limit, defaultLimit);

        List<?> list = queryPage(pageResult.getPage(), pageResult.getLimit(), pageQuery, true);

        pageResult.setRows(list);
        pageResult.setTotal(((Page) list).getTotal());
        return pageResult;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param pageQuery
     * @return
     */
    public static List queryPage(Integer page,
                                 Integer limit,
                                 PageQuery pageQuery) {
        return queryPage(page, limit, pageQuery, false);
    }

    /**
     * 合计
     *
     * @param pageQuery
     * @return
     */
    public static long count(PageQuery pageQuery) {
        return PageHelper.count(pageQuery::query);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param pageQuery
     * @param count
     * @return
     */
    private static List queryPage(Integer page,
                                  Integer limit,
                                  PageQuery pageQuery,
                                  boolean count) {
        PageHelper.startPage(page, limit, count);
        List<?> list;
        try {
            list = pageQuery.query();
        } finally {
            PageHelper.clearPage();
        }

        return list;
    }

}
