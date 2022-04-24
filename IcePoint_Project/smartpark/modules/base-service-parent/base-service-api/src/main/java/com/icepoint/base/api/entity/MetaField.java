package com.icepoint.base.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 字段元数据
 * @author BD
 *
 */
@Table(name = "t_meta_field")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaField extends BasicEntity<Long> {

    private Long tabId;// 

    @Length(max = 100, message = "字段名称长度不能超过100")
    private String name;// 

    @Length(max = 100, message = "英文名称长度不能超过100")
    private String nameEn;// 

    @Length(max = 100, message = "存储字段名长度不能超过100")
    private String storeName;// 指对应表中的字段名称，如果不设置则和字段名称一致

    @Length(max = 100, message = "存储字段英文名称长度不能超过100")
    private String storeNameEn;// 

    private Integer type;//

    @Length(max = 32, message = "数据本类型长度不能超过32")
    private String nativeType;//

    private Integer maxlen;//

    private Integer fractional;//

    @Length(max = 1024, message = "字段说明长度不能超过1024")
    private String description;// 

    private Integer optional;//

    @Length(max = 64, message = "缺省值长度不能超过64")
    private String defaultValue;//

    @Length(max = 255, message = "值域长度不能超过255")
    private String domain;//

    @Length(max = 8, message = "最大值长度不能超过8")
    private String maxVal;// 

    private Integer maxInclude;//
    @Length(max = 8, message = "最小值长度不能超过8")

    private String minVal;// 

    private Integer minInclude;//

    private Integer listField;// 非列表字段仅在详情查询时返回，在列表查询中不返回

    private Integer queryField;// 

    private Integer dictField;//
    @Length(max = 32, message = "大类英文名长度不能超过32")
    private String categoryEn;//

    private Integer inField;// 

    private Integer primaryKey;// 

    private Integer uniqueIdx;// 用于判断记录是否有重复

    private Integer showSort;// 界面tab列表展示时，该字段是否可排序

    private Integer busType;//

    private Integer idx;

    private Integer status;//

    private Long ownerId;//

    private Long appId;// 一个应用维护一个商品列表，免去商家各自维护

    private Long createTime;// 

    private Long createUser;//

    private Long updateTime;// 

    private Long updateUser;// 

    private Integer deleted;// 

    public MetaField(

            Long id
            ,
            Long tabId

            ,
            String nameEn

            ,
            String storeNameEn
            ,
            Integer type
            ,
            String nativeType


            ,
            Integer optional


            ,
            Integer listField
            ,
            Integer queryField
            ,
            Integer dictField

            ,
            Integer inField
            ,
            Integer primaryKey

            ,
            Integer showSort
            ,
            Integer busType
            ,
            Integer status
            ,
            Long ownerId
            ,
            Long appId

            ,
            Long createUser

            ,
            Long updateUser
            ,
            Integer deleted
    ) {
        setId(id);
        setTabId(tabId);

        setNameEn(nameEn);

        setStoreNameEn(storeNameEn);
        setType(type);
        setNativeType(nativeType);


        setOptional(optional);


        setListField(listField);
        setQueryField(queryField);
        setDictField(dictField);

        setInField(inField);
        setPrimaryKey(primaryKey);

        setShowSort(showSort);
        setBusType(busType);
        setStatus(status);
        setOwnerId(ownerId);
        setAppId(appId);

        setCreateUser(createUser);

        setUpdateUser(updateUser);
        setDeleted(deleted);
    }

    public MetaField(java.util.Map<String, Object> map) {
        if (map.containsKey("id")) {
            setId((Long) map.get("id"));
        }
        if (map.containsKey("tabId")) {
            setTabId((Long) map.get("tabId"));
        }
        if (map.containsKey("name")) {
            setName((String) map.get("name"));
        }
        if (map.containsKey("nameEn")) {
            setNameEn((String) map.get("nameEn"));
        }
        if (map.containsKey("storeName")) {
            setStoreName((String) map.get("storeName"));
        }
        if (map.containsKey("storeNameEn")) {
            setStoreNameEn((String) map.get("storeNameEn"));
        }
        if (map.containsKey("type")) {
            setType((Integer) map.get("type"));
        }
        if (map.containsKey("nativeType")) {
            setNativeType((String) map.get("nativeType"));
        }
        if (map.containsKey("maxlen")) {
            setMaxlen((Integer) map.get("maxlen"));
        }
        if (map.containsKey("fractional")) {
            setFractional((Integer) map.get("fractional"));
        }
        if (map.containsKey("description")) {
            setDescription((String) map.get("description"));
        }
        if (map.containsKey("optional")) {
            setOptional((Integer) map.get("optional"));
        }
        if (map.containsKey("defaultValue")) {
            setDefaultValue((String) map.get("defaultValue"));
        }
        if (map.containsKey("domain")) {
            setDomain((String) map.get("domain"));
        }
        if (map.containsKey("maxVal")) {
            setMaxVal((String) map.get("maxVal"));
        }
        if (map.containsKey("maxInclude")) {
            setMaxInclude((Integer) map.get("maxInclude"));
        }
        if (map.containsKey("minVal")) {
            setMinVal((String) map.get("minVal"));
        }
        if (map.containsKey("minInclude")) {
            setMinInclude((Integer) map.get("minInclude"));
        }
        if (map.containsKey("listField")) {
            setListField((Integer) map.get("listField"));
        }
        if (map.containsKey("queryField")) {
            setQueryField((Integer) map.get("queryField"));
        }
        if (map.containsKey("dictField")) {
            setDictField((Integer) map.get("dictField"));
        }
        if (map.containsKey("categoryEn")) {
            setCategoryEn((String) map.get("categoryEn"));
        }
        if (map.containsKey("inField")) {
            setInField((Integer) map.get("inField"));
        }
        if (map.containsKey("primaryKey")) {
            setPrimaryKey((Integer) map.get("primaryKey"));
        }
        if (map.containsKey("uniqueIdx")) {
            setUniqueIdx((Integer) map.get("uniqueIdx"));
        }
        if (map.containsKey("showSort")) {
            setShowSort((Integer) map.get("showSort"));
        }
        if (map.containsKey("busType")) {
            setBusType((Integer) map.get("busType"));
        }
        if (map.containsKey("idx")) {
            setIdx((Integer) map.get("idx"));
        }
        if (map.containsKey("status")) {
            setStatus((Integer) map.get("status"));
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
    }
}