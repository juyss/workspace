package com.icepoint.framework.data.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
public class SortOnly implements Pageable {

    private final Pageable delegate = Pageable.unpaged();

    private final Sort sort;

    public SortOnly(Sort sort) {
        this.sort = sort;
    }

    @Override
    public boolean isPaged() {
        return this.delegate.isPaged();
    }

    @Override
    public boolean isUnpaged() {
        return this.delegate.isUnpaged();
    }

    @Override
    public int getPageNumber() {
        return this.delegate.getPageNumber();
    }

    @Override
    public int getPageSize() {
        return this.delegate.getPageSize();
    }

    @Override
    public long getOffset() {
        return this.delegate.getOffset();
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return this.delegate.getSortOr(sort);
    }

    @Override
    public Pageable next() {
        return this.delegate.next();
    }

    @Override
    public Pageable previousOrFirst() {
        return this.delegate.previousOrFirst();
    }

    @Override
    public Pageable first() {
        return this.delegate.first();
    }

    @Override
    public boolean hasPrevious() {
        return this.delegate.hasPrevious();
    }

    @Override
    public Optional<Pageable> toOptional() {
        return this.delegate.toOptional();
    }
}
