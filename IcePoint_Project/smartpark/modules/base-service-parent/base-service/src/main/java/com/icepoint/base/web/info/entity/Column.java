package com.icepoint.base.web.info.entity;

import com.icepoint.base.api.domain.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 板块
 * @author BD
 *
 */
@Table
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Column extends BasicEntity<Long> {
    private Integer columnType;//栏目类型
    @Length(max = 64, message = "栏目名称长度不能超过64")
    private String columnName;// 栏目名称
    private Integer state;//状态
    private Integer idx;//排序
    private Long parentId;// 父栏目
    @Length(max = 128, message = "栏目图标长度不能超过128")
    private String columnIcon;// 栏目图标
    private Long ownerId;//所有者编号
    private Long appId;// 系统编号
    private Long createUser;// 创建用户
    private Long createTime;// 创建时间
    private Long modifyUser;// 更新用户
    private Long modifyTime;//更新时间 
    private Integer deleted;// 是否删除
    private Integer isRoot;

}