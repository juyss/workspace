package com.icepoint.base.web.sys.entity;

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
import java.sql.Timestamp;

/**
 * 数据字典
 * @author BD
 *
 */
@Table(name = "t_sys_dict")
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dict extends BasicEntity<Long> implements OrderedParentIdHierarchy {

    @Length(max = 200, message = "中文类名长度不能超过200")
    private String category;// 

    @Length(max = 100, message = "英文类名长度不能超过100")
    private String categoryEn;// 

    @Length(max = 100, message = "中文标签长度不能超过100")
    private String item;// 

    @Length(max = 64, message = "英文标签长度不能超过64")
    private String itemEn;// 

    @Length(max = 32, message = "值长度不能超过32")
    private String cval;// 

    @Length(max = 32, message = "值英文长度不能超过32")
    private String cvalEn;// 

    private Integer use_icon;//

    @Length(max = 300, message = "图标长度不能超过300")
    private String icon;// 

    private Long parentId;// 

    private Integer level;// 

    private Integer idx;// 

    @Length(max = 1024, message = "备注长度不能超过1024")
    private String note;// 

    private Long ownerId;// 

    private Long appId;// 一个应用维护一个商品列表，免去商家各自维护

    private Long createUser;// 

    private Timestamp createTime;//

    private Long modifyUser;//

    private Timestamp modifyTime;//

    private Integer deleted;// 

    public Dict(

            Long id
            ,
            String category
            ,
            String categoryEn
            ,
            String item
            ,
            String itemEn
            ,
            String cval
            ,
            String cvalEn
            ,
            Integer use_icon
            ,
            String icon
            ,
            Long parentId
            ,
            Integer level
            ,
            Integer idx
            ,
            String note
            ,
            Long ownerId
            ,
            Long appId
            ,
            Long createUser
            ,
            Timestamp createTime
            ,
            Long modifyUser
            ,
            Timestamp modifyTime
            ,
            Integer deleted
    ) {
        setId(id);
        setCategory(category);
        setCategoryEn(categoryEn);
        setItem(item);
        setItemEn(itemEn);
        setCval(cval);
        setCvalEn(cvalEn);
        setUse_icon(use_icon);
        setIcon(icon);
        setParentId(parentId);
        setLevel(level);
        setIdx(idx);
        setNote(note);
        setOwnerId(ownerId);
        setAppId(appId);
        setCreateUser(createUser);
        setCreateTime(createTime);
        setModifyUser(modifyUser);
        setModifyTime(modifyTime);
        setDeleted(deleted);
    }

    public Dict(java.util.Map<String, Object> map) {
        if (map.containsKey("id")) {
            setId((Long) map.get("id"));
        }
        if (map.containsKey("category")) {
            setCategory((String) map.get("category"));
        }
        if (map.containsKey("categoryEn")) {
            setCategoryEn((String) map.get("categoryEn"));
        }
        if (map.containsKey("item")) {
            setItem((String) map.get("item"));
        }
        if (map.containsKey("itemEn")) {
            setItemEn((String) map.get("itemEn"));
        }
        if (map.containsKey("cval")) {
            setCval((String) map.get("cval"));
        }
        if (map.containsKey("cvalEn")) {
            setCvalEn((String) map.get("cvalEn"));
        }
        if (map.containsKey("use_icon")) {
            setUse_icon((Integer) map.get("use_icon"));
        }
        if (map.containsKey("icon")) {
            setIcon((String) map.get("icon"));
        }
        if (map.containsKey("parentId")) {
            setParentId((Long) map.get("parentId"));
        }
        if (map.containsKey("level")) {
            setLevel((Integer) map.get("level"));
        }
        if (map.containsKey("idx")) {
            setIdx((Integer) map.get("idx"));
        }
        if (map.containsKey("note")) {
            setNote((String) map.get("note"));
        }
        if (map.containsKey("ownerId")) {
            setOwnerId((Long) map.get("ownerId"));
        }
        if (map.containsKey("appId")) {
            setAppId((Long) map.get("appId"));
        }
        if (map.containsKey("createUser")) {
            setCreateUser((Long) map.get("createUser"));
        }
        if (map.containsKey("createTime")) {
            setCreateTime((Timestamp) map.get("createTime"));
        }
        if (map.containsKey("modifyUser")) {
            setModifyUser((Long) map.get("modifyUser"));
        }
        if (map.containsKey("modifyTime")) {
            setModifyTime((Timestamp) map.get("modifyTime"));
        }
        if (map.containsKey("deleted")) {
            setDeleted((Integer) map.get("deleted"));
        }
    }

    @JsonIgnore
    @Override
    public int getOrder() {
        return getIdx();
    }
}