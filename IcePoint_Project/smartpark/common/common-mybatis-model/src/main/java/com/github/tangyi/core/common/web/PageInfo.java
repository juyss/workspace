package com.github.tangyi.core.common.web;

import lombok.Data;

/**
 * 分页信息
 *
 * @author hedongzhou
 * @date 2018/06/21
 */
@Data
public class PageInfo {

    /**
     * 当前页码
     */
    protected Integer pageNum;

    /**
     * 当前页大小
     */
    protected Integer pageSize;

    /**
     * 当前搜索条件下总记录数量
     */
    protected Integer total = 0;

    public PageInfo() {
        this.pageNum = 1;
        this.pageSize = 20;
    }

    public PageInfo(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageInfo(Integer pageNum, Integer pageSize, Integer defaultPage, Integer defaultLimit) {
        this.pageNum = pageNum != null ? pageNum : defaultPage;
        this.pageSize = pageSize != null ? pageSize : defaultLimit;
    }

    public PageInfo(Integer pageNum, Integer pageSize, Integer defaultLimit) {
        this(pageNum, pageSize, 1, defaultLimit);
    }


    public PageInfo default20LimitInvoke() {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        return new PageInfo(pageNum, pageSize);
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                '}';
    }

}
