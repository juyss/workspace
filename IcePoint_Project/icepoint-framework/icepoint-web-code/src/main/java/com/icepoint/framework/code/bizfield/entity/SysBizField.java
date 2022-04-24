package com.icepoint.framework.code.bizfield.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (SysBizField)表实体类
 *
 * @author makejava
 * @since 2021-06-04 16:13:23
 */
@SuppressWarnings("serial")
public class SysBizField extends Model<SysBizField> {

    private Long id;

    private String bizCode;

    private Long fieldId;
    //字段名称
    private String name;
    //英文名称
    private String nameEn;
    //是否列表字段，非列表字段仅在详情查询时返回，在列表查询中不返回
    private Integer listField;
    //是否列表字段，非列表字段仅在详情查询时返回，在列表查询中不返回
    private Integer listIdx;
    //界面tab列表展示时，该字段是否可排序
    private Integer showSort;
    //是否查询条件字段
    private Integer queryField;
    //是否查询条件字段
    private Integer queryIdx;

    private Integer detailField;

    private Integer detailIdx;

    private Integer editable;
    //平台，用于SAAS产品，备用，当前应用中可以填固定值，要求查询时候作为必须条件字段
    private Long platformId;
    //所有者，应用的所有者，业务查询中必须字段，当前使用中可以填固定值
    private Long ownerId;
    //应用编号，所有者所申请的应用，一般会对应一对AppID、AppSecret，当前应用中可以填固定值
    private Long appId;

    private Long createUser;

    private Long createTime;

    private Long modifyUser;

    private Long modifyTime;

    private Integer deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getListField() {
        return listField;
    }

    public void setListField(Integer listField) {
        this.listField = listField;
    }

    public Integer getListIdx() {
        return listIdx;
    }

    public void setListIdx(Integer listIdx) {
        this.listIdx = listIdx;
    }

    public Integer getShowSort() {
        return showSort;
    }

    public void setShowSort(Integer showSort) {
        this.showSort = showSort;
    }

    public Integer getQueryField() {
        return queryField;
    }

    public void setQueryField(Integer queryField) {
        this.queryField = queryField;
    }

    public Integer getQueryIdx() {
        return queryIdx;
    }

    public void setQueryIdx(Integer queryIdx) {
        this.queryIdx = queryIdx;
    }

    public Integer getDetailField() {
        return detailField;
    }

    public void setDetailField(Integer detailField) {
        this.detailField = detailField;
    }

    public Integer getDetailIdx() {
        return detailIdx;
    }

    public void setDetailIdx(Integer detailIdx) {
        this.detailIdx = detailIdx;
    }

    public Integer getEditable() {
        return editable;
    }

    public void setEditable(Integer editable) {
        this.editable = editable;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
