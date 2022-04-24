package com.github.tangyi.core.common.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础模型
 *
 * @author hedongzhou
 * @since 2018/07/26
 */
public class BaseModel implements Serializable {

    protected static final long serialVersionUID = 1L;

    /**
     * deleted 删除状态【0：可用／1：删除】
     */
    @Column(name = "deleted")
    @JsonProperty("deleted")
    protected Integer deleted;

    /**
     * created_time 创建时间
     */
    @Column(name = "created_time")
    @JsonProperty("created_time")
    protected Date createdTime;

    /**
     * modified_time 修改时间
     */
    @Column(name = "modified_time")
    @JsonProperty("modified_time")
    protected Date modifiedTime;

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "deleted=" + deleted +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }

    /**
     * 是否有效
     *
     * @return
     */
    public boolean isValid() {
        return this.deleted == null || this.deleted.equals(0);
    }

}
