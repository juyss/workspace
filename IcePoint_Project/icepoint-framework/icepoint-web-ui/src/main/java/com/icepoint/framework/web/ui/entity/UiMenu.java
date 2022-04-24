package com.icepoint.framework.web.ui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.data.domain.ParentIdHierarchy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;

import javax.persistence.Column;

/**
 * (UiMenu)实体类
 *
 * @author makejava
 * @since 2021-06-17 21:38:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ui_menu")
public class UiMenu extends LongStdEntity implements ParentIdHierarchy<Long>, Ordered {

    /**
     * 代码。
     */
    @Column(name = "`code`")
    private String code;
    /**
     * 命令。
     */
    @Column(name = "`command`")
    private String command;
    /**
     * 组件的路径
     */
    @Column(name = "`component`")
    private String component;

    @Column(name = "`cookie`")
    private String cookie;
    /**
     * 字体样式。有菜单模板，可以去掉该属性不要，或者保留，用于在菜单模板应用后，有特定效果时候使用
     */
    @Column(name = "`font`")
    private String font;

    @Column(name = "`header`")
    private String header;
    /**
     * 图标样式。
     */
    @Column(name = "`icon`")
    private String icon;
    /**
     * 次序。
     */
    @Column(name = "`idx`")
    private Integer idx;
    /**
     * 层级。
     */
    @Column(name = "`level`")
    private Integer level;
    /**
     * 名字。
     */
    @Column(name = "`name`")
    private String name;
    /**
     * 备注。
     */
    @Column(name = "`note`")
    private String note;
    /**
     * 菜单打开方式。数据字典，可以是打开一个页面，也可以是弹窗，或者是停靠在界面中的选项卡
     */
    @Column(name = "`open_type`")
    private Integer openType;
    /**
     * 页面路径。
     */
    @Column(name = "`page_path`")
    private String pagePath;
    /**
     * 父菜单。
     */
    @Column(name = "`parent_id`")
    private Long parentId;
    /**
     * 板块。1:子系统, 2:快捷工具, 3:应用中心 ，数据字典
     */
    @Column(name = "`plate`")
    private Integer plate;
    /**
     * 是否显示菜单。打开页面后是否显示菜单
     */
    @Column(name = "`show_menu`")
    private Integer showMenu;
    /**
     * 端类型。PC、安卓、苹果、微信公众号、小程序，字典定义，可扩充
     */
    @Column(name = "`term_type`")
    private Integer termType;

    @Column(name = "`token`")
    private String token;

    @Column(name = "`type`")
    private Integer type;

    @Override
    public int getOrder() {
        return getIdx() == null ? 0 : getIdx();
    }
}
