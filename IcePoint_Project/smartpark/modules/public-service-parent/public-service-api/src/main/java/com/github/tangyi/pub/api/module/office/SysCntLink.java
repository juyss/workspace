package com.github.tangyi.pub.api.module.office;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "sys_cnt_link", description = "办事大厅配置")
public class SysCntLink extends TreeEntity<SysCntLink> {
    /**
     * "主键Id"
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 板块，用树形结构存储板块，用于表示在哪里显示内容链接，数据字典定义
     */
    @ApiModelProperty(value = "板块")
    private Integer plate;

    /**
     * 终端类型，数据字典定义
     * 0:手机app 1:小程序 2:web端 默认为 2 web
     */
    @ApiModelProperty(value = "终端类型 0:手机app 1:小程序 2:web端 默认为 2 web 3 WECAT 微信小程序")
    private Integer termType;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 封面类型，是图片、视频，还是VR等，数据字典定义
     */
    @ApiModelProperty(value = "封面类型，是图片、视频，还是VR等 0:图片 1:视频 2:VR 默认0 图片")
    private Integer coverType;

    /**
     * 图片，当是内容列表，点击跳转时候，为图标。如果是轮播图，是轮播图封面
     */
    @ApiModelProperty(value = "图片，当是内容列表，点击跳转时候，为图标。如果是轮播图，是轮播图封面")
    private String picture;

    /**
     * 视频，当是内容列表，该项无效。如果是轮播图，可以是视频或者VR等用于轮播显示内容
     */
    @ApiModelProperty(value = "视频")
    private String video;

    /**
     * 根据封面类型，配合前端显示自定义的参数，如视频可能存储时长等，以JSON字符串方式存储
     */
    @ApiModelProperty(value = "根据封面类型，配合前端显示自定义的参数，如视频可能存储时长等，以JSON字符串方式存储")
    private String coverParam;

    /**
     * 描述，用于鼠标移动上去时候的展示
     */
    @ApiModelProperty(value = "描述，用于鼠标移动上去时候的展示")
    private String summary;

    /**
     * 跳转类型，字典定义，可以是页面，也可以是小程序，某个内容板块等，数据字典定义
     * 0, "页面"
     *  1, "app",
     * 2, "微信小程序",
     * 3, "板块";
     */
    @ApiModelProperty(value = "跳转类型")
    private Integer jumpType;

    /**
     * 链接，根据具体应用实现确定，可以是跳转地址，也可以是跳转参数，根据类型确定跳转位置，如轮播图，点击调整某个商品板块
     */
    @ApiModelProperty(value = "链接")
    private String target;

    /**
     * 展示类型，如tab方式展示，平铺展示等。要求必须有一个根节点，用于描述第一级的展示方式，数据字典定义
     */
    @ApiModelProperty(value = "展示类型")
    private Integer displayType;

    /**
     * 层级，辅助查询
     */
    @ApiModelProperty(value = "层级")
    private Integer level;

    /**
     * 序号，同一级的显示排序
     */
    @ApiModelProperty(value = "序号")
    private Double idx;

    /**
     * 父节点
     */
    @ApiModelProperty(value = "父节点")
    private Long parentId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 平台，用于SAAS产品，备用，当前应用中可以填固定值，要求查询时候作为必须条件字段
     */
    @ApiModelProperty(value = "平台")
    private Long platformId;

    /**
     * 所有者，应用的所有者，业务查询中必须字段，当前使用中可以填固定值
     */
    @ApiModelProperty(value = "应用的所有者")
    private Long ownerId;

    /**
     * 应用编号，所有者所申请的应用，一般会对应一对AppID、AppSecret，当前应用中可以填固定值
     */
    @ApiModelProperty(value = "应用编号")
    private Long appId;

    /**
     * 创建用户
     */
    @ApiModelProperty(value = "创建用户")
    private Long createUser;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改用户
     */
    @ApiModelProperty(value = "修改用户")
    private Long updateUser;

    /**
     * 修改日期
     */
    @ApiModelProperty(value = "修改日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除")
    private int deleted;

    @ApiModelProperty(value = "父节点名称")
    private String parentName;

    public SysCntLink(SysCntLink sysCntLink) {
        this.id = sysCntLink.getId();
        this.plate = sysCntLink.getPlate();
        this.termType = sysCntLink.getTermType();
        this.name = sysCntLink.getName();
        this.coverType = sysCntLink.getCoverType();
        this.picture = sysCntLink.getPicture();
        this.video = sysCntLink.getVideo();
        this.coverParam = sysCntLink.getCoverParam();
        this.jumpType = sysCntLink.getJumpType();
        this.displayType = sysCntLink.getDisplayType();
        this.level = sysCntLink.getLevel();
        this.idx = sysCntLink.getIdx();
        this.parentId = sysCntLink.getParentId();
        this.remarks = sysCntLink.getRemarks();
        this.platformId = sysCntLink.getPlatformId();
        this.ownerId = sysCntLink.getOwnerId();
        this.appId = sysCntLink.getAppId();
        this.createUser = sysCntLink.getCreateUser();
        this.updateUser = sysCntLink.getUpdateUser();
        this.updateTime = sysCntLink.getUpdateTime();
        this.deleted = sysCntLink.getDeleted();
        this.parentName = sysCntLink.getParentName();

    }
}
