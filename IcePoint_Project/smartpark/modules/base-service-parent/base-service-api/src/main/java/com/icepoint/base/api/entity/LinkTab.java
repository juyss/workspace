package com.icepoint.base.api.entity;

import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Table(name = "t_res_link_tab")
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LinkTab extends BasicEntity<Long> {

    private Long tabId;

    private Long fieldId;// 

    private Integer linkType;// 1-文件 2-字段关联,就是本字段是另外一个表的主键，可以关联查询出对象  3-虚拟字段，本表未存关联信息，关联表中存储关联字段信息

    private Integer resType;

    private Long linkTabId;//

    @NotEmpty(message = "关联查询信息不能为空")
    @Length(max = 1024, message = "关联查询信息长度不能超过1024")
    private String linkInfo;// 如果是文件字段，则存储诸如文件表对象类型等

    private Integer list;// 列表还是对象

    @Length(max = 32, message = "标签名长度不能超过32")
    private String tagEn;// 

    @Length(max = 32, message = "标签中文名长度不能超过32")
    private String tag;//

    private Integer displayType;

    private Long ownerId;// 

    private Long appId;// 一个应用维护一个商品列表，免去商家各自维护

    private Long createTime;//

    private Long createUser;// 

    private Long updateTime;// 

    private Long updateUser;//

    private Integer deleted;// 

}