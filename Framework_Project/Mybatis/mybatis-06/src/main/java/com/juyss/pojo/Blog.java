package com.juyss.pojo;

import java.sql.Date;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Blog
 * @Desc: mybatis/blog表的实体类
 * @package com.juyss.pojo
 * @project Mybatis
 * @date 2020/9/18 15:43
 */
public class Blog {

    private String id;
    private String title;
    private String author;
    private Date createTime;
    private Integer views;

    public Blog(String id, String title, String author, Date createTime, Integer views) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createTime = createTime;
        this.views = views;
    }

    public Blog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", views=" + views +
                '}';
    }
}
