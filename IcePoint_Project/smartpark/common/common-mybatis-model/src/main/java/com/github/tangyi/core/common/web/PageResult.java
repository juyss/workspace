package com.github.tangyi.core.common.web;

import com.github.tangyi.core.common.util.CollectionUtils;

import java.util.List;

/**
 * 分页结果
 *
 * @author hedongzhou
 * @date 2018/06/21
 */
public class PageResult {

    /**
     * 分页信息
     */
    protected PageInfo pageInfo;

    /**
     * 结果
     */
    protected List<?> rows = CollectionUtils.emptyList();

    public PageResult(Integer page, Integer limit) {
        this.pageInfo = new PageInfo(page, limit);
    }

    public PageResult(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public PageResult(Integer page, Integer limit, Integer defaultLimit) {
        this.pageInfo = new PageInfo(page, limit, defaultLimit);
    }

    public PageResult(PageRequest pageRequest) {
        this.pageInfo = new PageInfo(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getDefaultLimit());
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void setTotal(Integer total) {
        this.pageInfo.setTotal(total);
    }

    public void setTotal(Long total) {
        this.pageInfo.setTotal(total.intValue());
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return pageInfo.getPageNum();
    }

    public Integer getLimit() {
        return pageInfo.getPageSize();
    }

    @Override
    public String toString() {
        return "PageResult{" + "pageInfo=" + pageInfo + ", rows=" + rows + '}';
    }

}
