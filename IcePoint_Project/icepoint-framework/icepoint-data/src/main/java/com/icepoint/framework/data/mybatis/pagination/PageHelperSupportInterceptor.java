package com.icepoint.framework.data.mybatis.pagination;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * 基于PageHelper的拦截器
 *
 * @author Jiawei Zhao
 */
public class PageHelperSupportInterceptor extends AbstractPaginationInterceptor {

    @Nullable
    @Override
    protected Pageable getPageable(Object paramObject) {

        Page<Object> page = PageMethod.getLocalPage();

        return page == null
                ? null
                : PageableSupportUtils.toPageable(page);
    }

}
