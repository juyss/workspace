package com.icepoint.framework.workorder.work.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 作业问题记录表
 *
 * @author
 */
@Entity
@Table(name = "work_order_defect")
@TableName("work_order_defect")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkDefect extends LongStdEntity {

    /**
     * 作业任务
     */
    @Column(name = "`task_id`")
    private Long taskId;

    /**
     * 工单
     */
    @Column(name = "`work_order_id`")
    private Long workOrderId;

    /**
     * 问题类型
     */
    @Column(name = "`defect_type`")
    private Integer defectType;

    /**
     * 问题对象资产定义ID
     */
    @Column(name = "`defect_obj_asset_def_id`")
    private Long defectObjAssetDefId;

    /**
     * 问题对象
     */
    @Column(name = "`defect_obj_id`")
    private Long defectObjId;

    /**
     * 问题对象名
     */
    @Column(name = "`defect_obj_name`")
    private String defectObjName;

    /**
     * 关联根对象根节点资产定义ID
     */
    @Column(name = "`defect_root_asset_def_id`")
    private Long defectRootAssetDefId;

    /**
     * 关联根对象
     */
    @Column(name = "`defect_root_id`")
    private Long defectRootId;

    /**
     * 关联根对象名
     */
    @Column(name = "`defect_root_name`")
    private String defectRootName;

    /**
     * 区域类型
     */
    @Column(name = "`regional_type`")
    private Integer regionalType;

    /**
     * 区域信息
     */
    @Column(name = "`region`")
    private String region;

    /**
     * 行政区划
     */
    @Column(name = "`adcode`")
    private String adcode;

    /**
     * 经度
     */
    @Column(name = "`longitude`", length = 10, scale = 6)
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @Column(name = "`latitude`", length = 10, scale = 6)
    private BigDecimal latitude;

    /**
     * 桩号
     */
    @Column(name = "`pile_number`")
    private String pileNumber;

    /**
     * 详细地址
     */
    @Column(name = "`location`")
    private String location;

    /**
     * 描述
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 问题来源
     */
    @Column(name = "`source`")
    private Integer source;

    /**
     * 问题状态
     */
    @Column(name = "`defect_status`")
    private Integer defectStatus;

    /**
     * 上报人id
     */
    @Column(name = "`report_user_id`")
    private Long reportUserId;

    /**
     * 登记时间
     */
    @Column(name = "`report_time`")
    private Long reportTime;

    /**
     * 备注
     */
    @Column(name = "`note`")
    private String note;
}
