package com.juyss.pojo;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Page
 * @Desc:  分页模型对象,<T>是具体的JavaBean
 * @package com.juyss.pojo
 * @project BookStore
 * @date 2020/8/14 10:46
 */
public class Page<T> {

    /**
     * 页面大小
     */
    public static final Integer PAGE_SIZE = 4;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 总页数
     */
    private Integer pageTotal;

    /**
     * 总数据记录数
     */
    private Integer pageTotalCount;

    /**
     * 页面大小
     */
    private Integer pageSize = PAGE_SIZE;

    /**
     * 分页条请求地址
     */
    private String url;

    /**
     * 当前页面要显示的数据
     */
    private List<T> items;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", url='" + url + '\'' +
                ", items=" + items +
                '}';
    }
}
