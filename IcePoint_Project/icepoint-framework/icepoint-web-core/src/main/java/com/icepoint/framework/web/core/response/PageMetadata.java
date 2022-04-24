package com.icepoint.framework.web.core.response;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;

/**
 * @author Jiawei Zhao
 */
public class PageMetadata extends PagedModel.PageMetadata {

    public PageMetadata(Page<?> page) {
        this(page.getSize(), page.getNumber(), page.getTotalElements());
    }

    public PageMetadata(long size, long number, long totalElements) {
        super(size, number, totalElements);
    }

    public static PageMetadata of(PagedModel.PageMetadata metadata) {
        return new PageMetadata(metadata.getSize(), metadata.getNumber(), metadata.getTotalElements());
    }

    public boolean hasPrevious() {
        return getNumber() > 0;
    }

    public boolean hasNext() {
        return getNumber() + 1 < getTotalPages();
    }

    public boolean isFirst() {
        return !hasPrevious();
    }

    public boolean isLast() {
        return !hasNext();
    }



}
