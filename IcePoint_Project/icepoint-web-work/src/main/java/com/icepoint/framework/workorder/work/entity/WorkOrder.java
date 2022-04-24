package com.icepoint.framework.workorder.work.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.math.BigDecimal;

/**
 * 工单
 *
 * @author
 */
@Entity
@Table(name = "work_order_record")
@TableName("work_order_record")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrder extends LongStdEntity {


    /**
     * 工单编号
     */
    @Column(name = "`code`")
    private String code;

    /**
     * 作业任务编号
     */
    @Column(name = "`task_code`")
    private String taskCode;

    /**
     * 工单类型
     */
    @Column(name = "`type`")
    private String type;

    /**
     * 问题类型
     */
    @Column(name = "`defect_type`")
    private Integer defectType;

    /**
     * 问题级别
     */
    @Column(name = "`level`")
    private Integer level;

    /**
     * 影响范围
     */
    @Column(name = "`scope`")
    private Integer scope;

    /**
     * 工单来源
     */
    @Column(name = "`resource`")
    private Integer resource;

    /**
     * 作业对象资产定义
     */
    @Column(name = "`work_obj_asset_def_id`")
    private Long workObjAssetDefId;

    /**
     * 作业对象
     */
    @Column(name = "`work_obj_id`")
    private Long workObjId;

    /**
     * 作业对象名称
     */
    @Column(name = "`work_obj_name`")
    private String workObjName;

    /**
     * 作业对象根节点资产定义
     */
    @Column(name = "`work_root_asset_def_id`")
    private Long workRootAssetDefId;

    /**
     * 作业对象根节点
     */
    @Column(name = "`work_root_id`")
    private Long workRootId;

    /**
     * 作业对象根节点名
     */
    @Column(name = "`work_root_name`")
    private Long workRootName;

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
    @Column(name = "`pile_no`")
    private String pileNo;

    /**
     * 详细地址
     */
    @Column(name = "`location`")
    private String location;

    /**
     * 当前流程环节
     */
    @Column(name = "`flow_node`")
    private String flowNode;

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
     * 问题描述
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 锁定状态
     */
    @Column(name = "`lock_status`")
    private Integer lockStatus;

    /**
     * 锁定人id
     */
    @Column(name = "`lock_user_id`")
    private Long lockUserId;

    /**
     * 锁定时间
     */
    @Column(name = "`locked_time`")
    private Long lockedTime;

    /**
     * 所属组织资产定义
     */
    @Column(name = "`dept_asset_def_id`")
    private Long deptAssetDefId;

    /**
     * 所属组织
     */
    @Column(name = "`dept_id`")
    private Long deptId;

    /**
     * 所属组织名
     */
    @Column(name = "`dept_name`")
    private String deptName;

    /**
     * 所属组织根节点资产定义
     */
    @Column(name = "`dept_root_asset_def_id`")
    private Long deptRootAssetDefId;

    /**
     * 所属组织根节点
     */
    @Column(name = "`dept_root_id`")
    private Long deptRootId;

    /**
     * 所属组织根节点名
     */
    @Column(name = "`dept_root_name`")
    private String deptRootName;

    /**
     * 工单状态
     */
    @Column(name = "`order_status`")
    private Integer orderStatus;

    /**
     * 工单挂起状态
     */
    @Column(name = "`suspended_status`")
    private Integer suspendedStatus;

    /**
     * 工单处理截至时间
     */
    @Column(name = "`deadline`")
    private Integer deadline;

    /**
     * 完工时间
     */
    @Column(name = "`complete_time`")
    private Long completeTime;

    /**
     * 完结说明
     */
    @Column(name = "`complete_content`")
    private String completeContent;

    /**
     * 完工人
     */
    @Column(name = "`complete_user`")
    private Long completeUser;

    /**
     * 申报金额
     */
    @Column(name = "`declared_amount`", length = 10, scale = 2)
    private BigDecimal declaredAmount;

    /**
     * 处置方案
     */
    @Column(name = "`disposal_Note`")
    private String disposalNote;

    /**
     * 出库单组
     */
    @Column(name = "`order_grp_code`")
    private String orderGrpCode;

    /**
     * 备注
     */
    @Column(name = "`note`")
    private String note;
    /**
     * 开始时间
     */
    @Column(name = "`register_time`")
    private Long registerTime;


}
