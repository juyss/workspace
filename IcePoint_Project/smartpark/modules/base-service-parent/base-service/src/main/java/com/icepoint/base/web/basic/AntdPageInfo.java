package com.icepoint.base.web.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AntdPageInfo<T> extends PageInfo<T> {
    private Pagination pagination;

    public AntdPageInfo(PageInfo<T> info, Pageable pageable) {
        super();
        this.setList(info.getList());
        pagination = Pagination.builder()
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .total(info.getTotal())
                .pages((int) (info.getTotal() / pageable.getPageSize()) + (info.getTotal() % pageable.getPageSize() == 0 ? 0 : 1))
                .build();
    }

    public AntdPageInfo(Page<T> page, Pageable pageable) {
        super();
        this.setList(page.getContent());
        pagination = Pagination.builder()
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .total(page.getTotalElements())
                .pages(page.getTotalPages())
                .build();
    }

    public AntdPageInfo(Pageable pageable) {
        super();
        pagination = Pagination.builder()
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .build();
    }

    @JsonProperty("rows")
    public List<T> getData() {
        return super.getList();
    }

    @JsonIgnore
    public long getCount() {
        return super.getTotal();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    private static class Pagination {
        private int page = 0;
        private int size = 10;
        private long total = 0L;
        private int pages = 0;
    }
}
