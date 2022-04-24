package com.icepoint.framework.generator.entity;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class TableMetadata {

    private Long id;

    private Long resourceId;

    private String name;

    private String nameAlias;

    private Integer order;

    private Long appId;

    private Long ownerId;

    private Long createTime;

    private Long createUser;

    private Long updateTime;

    private Long updateUser;

    private Boolean deleted;

    private List<TableLink> links;

    private List<FieldMetadata> fields;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAlias() {
        return nameAlias;
    }

    public void setNameAlias(String nameAlias) {
        this.nameAlias = nameAlias;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<TableLink> getLinks() {
        return links;
    }

    public void setLinks(List<TableLink> links) {
        this.links = links;
    }

    public List<FieldMetadata> getFields() {
        return fields;
    }

    public void setFields(List<FieldMetadata> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "TableMetadata{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", name='" + name + '\'' +
                ", nameAlias='" + nameAlias + '\'' +
                ", order=" + order +
                ", appId=" + appId +
                ", ownerId=" + ownerId +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", deleted=" + deleted +
                ", links=" + links +
                ", fields=" + fields +
                '}';
    }
}
