package com.github.tangyi.introduction.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.github.tangyi.common.core.persistence.TreeEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: GeneralIntroduction
 * @Desc: 通用简介实体类
 * @Author Juyss
 * @Date: 2021-04-16 10:04
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_profile")
@ApiModel(value = "GeneralIntroduction", description = "通用简介实体类")
public class GeneralIntroduction extends TreeEntity<GeneralIntroduction>{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 板块，用树形结构存储板块，用于表示在哪里显示内容链接，数据字典定义
     */
    @Column(name = "plate")
    private Long plate;

    /**
     * 终端类型，数据字典定义
     */
    @Column(name = "termType")
    private Long termType;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 图片，当是内容列表，点击跳转时候，为图标。如果是轮播图，是轮播图封面
     */
    @Column(name = "picture")
    private String picture;

    /**
     * 描述，用于鼠标移动上去时候的展示
     */
    @Column(name = "summary")
    private String summary;

    /**
     * 描述，用于鼠标移动上去时候的展示
     */
    @Column(name = "profile")
    private String profile;

    /**
     * 关联组织机构代码，也可以考虑用ID，由应用层决定
     */
    @Column(name = "organization")
    private String organization;

    @Column(name = "map")
    private Long map;

    /**
     * 地图位置，一般是经纬度，如果要素是面，可以考虑用GeoJSON
     */
    @Column(name = "location")
    private String location;

    /**
     * 展示类型，如tab方式展示，平铺展示等。要求必须有一个根节点，用于描述第一级的展示方式,数据字典定义
     */
    @Column(name = "displayType")
    private Long displayType;

    /**
     * 层级，辅助查询
     */
    @Column(name = "level")
    private Long level;

    /**
     * 序号，同一级的显示排序
     */
    @Column(name = "idx")
    private Double idx;

    /**
     * 父节点
     */
    @Column(name = "parentId")
    private Long parentId;

    /**
     * 备注
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * 平台，用于SAAS产品，备用，当前应用中可以填固定值，要求查询时候作为必须条件字段
     */
    @Column(name = "platformId")
    private Long platformId;

    /**
     * 所有者，应用的所有者，业务查询中必须字段，当前使用中可以填固定值
     */
    @Column(name = "ownerId")
    private Long ownerId;

    /**
     * 应用编号，所有者所申请的应用，一般会对应一对AppID、AppSecret，当前应用中可以填固定值
     */
    @Column(name = "appId")
    private Long appId;

    /**
     * 创建人
     */
    @Column(name = "createUser")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "updateUser")
    private String updateUser;

    /**
     * 更新时间
     */
    @Column(name = "updateTime")
    private Date updateTime;

    /**
     * 删除标记，0:正常，1:已删除
     */
    @Column(name = "deleted")
    private Integer deleted;

}
