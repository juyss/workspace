package com.icepoint.framework.workorder.work.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 打卡点
 *
 * @author 
 */
@Entity
@Table(name = "work_order_check_point")
@TableName("work_order_check_point")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CheckPoint extends LongStdEntity {

    @Column(name = "id")
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 检查点编码
     */
    @Column(name = "`code`")
    private String code;

    /**
     * 检查点名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 所属对象资产定义ID
     */
    @Column(name = "`obj_asset_def_id`")
    private Long objAssetDefId;
    
    /**
     * 所属对象
     */
    @Column(name = "`obj_id`")
    private Long objId;
    
    /**
     * 所属对象名称
     */
    @Column(name = "`obj_name`")
    private String objName;
    
    /**
     * 经度
     */
    @Column(name = "`longitude`")
    private Float longitude;
    
    /**
     *  纬度
     */
    @Column(name = "`latitude`")
    private Float latitude;
    
    /**
     *  位置描述
     */
    @Column(name = "`place`")
    private String place;
    
    /**
     *  打卡设备资产定义ID
     */
    @Column(name = "`device_asset_def_id`")
    private Long deviceAssetDefId;

    /**
     *  打卡设备ID	
     */
    @Column(name = "`device_id`")
    private Long deviceId;
    
    /**
     *  打卡设备编码
     */
    @Column(name = "`target_code`")
    private String targetCode;
    
    /**
     *  指标类型
     */
    @Column(name = "`target_type`")
    private Integer targetType;
    
    /**
     *  指标单位
     */
    @Column(name = "`target_unit`")
    private String targetUnit;
}
