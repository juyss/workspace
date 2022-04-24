package com.icepoint.framework.data.mybatis.pagination;

import com.icepoint.framework.data.util.SortOnly;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
public class PageableQueryInterceptor extends AbstractPaginationInterceptor {

    @Nullable
    @Override
    protected Pageable getPageable(Object paramObject) {

        Pageable pageable = PageableSupportUtils.findArgument(paramObject, Pageable.class);

        if (pageable != null) {
            return pageable;
        }

        Sort sort = PageableSupportUtils.findArgument(paramObject, Sort.class);
        if (sort != null) {
            return new SortOnly(sort);
        }

        return null;
    }

}
