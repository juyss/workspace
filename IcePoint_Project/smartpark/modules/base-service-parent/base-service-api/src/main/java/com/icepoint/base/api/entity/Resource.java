package com.icepoint.base.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.icepoint.base.api.domain.OrderedParentIdHierarchy;
import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Table(name = "t_res_resource")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resource extends BasicEntity<Long> implements OrderedParentIdHierarchy {

    @NotEmpty(message = "资源名称不能为空")
    @Length(max = 100, message = "资源名称长度不能超过100")
    private String resName;//

    @Length(max = 100, message = "资源编码长度不能超过100")
    private String resCode;//

    private String resKey;//

    @Length(max = 255, message = "资源描述长度不能超过255")
    private String resDesc;//

    @Length(max = 100, message = "资源图标长度不能超过100")
    private String resIcon;//

    @Length(max = 100, message = "业务码长度不能超过100")
    private String bizCode;//

    private Integer geometryType;

    private Integer resIdx;//

    private Integer status;//

    private Long parentId;//

    private Integer resType;// 0-子对象集合 1-默认表单 2-数据库表 3-服务

    // TODO: jiawei: 这个字段理论上应该删除。资源是比表元数据更加抽象的数据，
    //               它们的关联字段不应该出现的资源表中，应该由表元数据进行关联字段的管理
    private Long metaTabId;// 不一定是完整的表数据，可以是表数据子集

    @Length(max = 100, message = "数据类型字段长度不能超过100")
    private String typeField;//

    @Length(max = 100, message = "关联数据类型长度不能超过100")
    private String dataType;

    private Long ownerId;//

    private Long appId;// 一个应用维护一个商品列表，免去商家各自维护

    private Long createTime;//

    private Long createUser;//

    private Long updateTime;//

    private Long updateUser;//

    private Integer deleted;//

    private Integer show;

    public Resource(java.util.Map<String, Object> map) {
        if (map.containsKey("id")) {
            setId((Long) map.get("id"));
        }
        if (map.containsKey("resName")) {
            setResName((String) map.get("resName"));
        }
        if (map.containsKey("resCode")) {
            setResCode((String) map.get("resCode"));
        }
        if (map.containsKey("resDesc")) {
            setResDesc((String) map.get("resDesc"));
        }
        if (map.containsKey("resIcon")) {
            setResIcon((String) map.get("resIcon"));
        }
        if (map.containsKey("resIdx")) {
            setResIdx((Integer) map.get("resIdx"));
        }
        if (map.containsKey("geometryType")) {
            setGeometryType((Integer) map.get("geometryType"));
        }
        if (map.containsKey("status")) {
            setStatus((Integer) map.get("status"));
        }
        if (map.containsKey("parentId")) {
            setParentId((Long) map.get("parentId"));
        }
        if (map.containsKey("resType")) {
            setResType((Integer) map.get("resType"));
        }
        if (map.containsKey("metaTabId")) {
            setMetaTabId((Long) map.get("metaTabId"));
        }
        if (map.containsKey("typeField")) {
            setTypeField((String) map.get("typeField"));
        }
        if (map.containsKey("dataType")) {
            setDataType((String) map.get("dataType"));
        }
        if (map.containsKey("ownerId")) {
            setOwnerId((Long) map.get("ownerId"));
        }
        if (map.containsKey("appId")) {
            setAppId((Long) map.get("appId"));
        }
        if (map.containsKey("createTime")) {
            setCreateTime((Long) map.get("createTime"));
        }
        if (map.containsKey("createUser")) {
            setCreateUser((Long) map.get("createUser"));
        }
        if (map.containsKey("updateTime")) {
            setUpdateTime((Long) map.get("updateTime"));
        }
        if (map.containsKey("updateUser")) {
            setUpdateUser((Long) map.get("updateUser"));
        }
        if (map.containsKey("deleted")) {
            setDeleted((Integer) map.get("deleted"));
        }
        if (map.containsKey("show")) {
            setShow((Integer) map.get("show"));
        }
    }

    @JsonIgnore
    @Override
    public int getOrder() {
        return getResIdx();
    }
}