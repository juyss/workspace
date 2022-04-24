package com.github.tangyi.core.common.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.tangyi.core.common.util.CheckUtils;
import lombok.Data;

/**
 * 分页请求
 *
 * @author hedongzhou
 * @since 2018/11/08
 */
@Data
public class PageRequest {

    /**
     * 排序类型
     */
    @JsonProperty("order_by")
    protected String orderBy;

    /**
     * 顺序类型，默认降序
     */
    @JsonProperty("sort_type")
    protected String sortType = "desc";

    /**
     * 页码
     */
    protected Integer page;

    /**
     * 页大小
     */
    protected Integer limit;

    /**
     * 默认页大小
     */
    @JsonProperty("default_limit")
    protected Integer defaultLimit = 20;

    /**
     * 默认排序
     *
     * @param orderBy
     */
    public void defaultOrder(String orderBy) {
        if (CheckUtils.isEmpty(this.orderBy)) {
            this.orderBy = orderBy;
        }
    }

    /**
     * 初始化分页
     *
     * @param pageRequest
     */
    public void initPage(PageRequest pageRequest) {
        this.orderBy = pageRequest.getOrderBy();
        this.sortType = pageRequest.getSortType();
        this.page = pageRequest.getPage();
        this.limit = pageRequest.getLimit();
        this.defaultLimit = pageRequest.getDefaultLimit();
    }

}
