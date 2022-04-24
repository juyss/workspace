package com.icepoint.framework.workorder.work.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;

import javax.persistence.Column;

/**
 * 子工单实体类
 *
 * @author makejava
 * @since 2021-07-23 17:02:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("work_order_record_sub")
public class WorkOrderRecordSub extends LongStdEntity {
    /**
     * 主键
     */
    @Column(name = "`id`")
    private Long id;
    /**
     * 工单号
     */
    @Column(name = "`code`")
    private String code;
    /**
     * 作业对象，可选，如果填上就是做详细计划，不填那么就是简单管控，再作业内容中做说明
     */
    @Column(name = "`work_obj`")
    private String workObj;
    /**
     * 作业对象，该作业对象可以是作业任务中的对象，也可以是作业对象下面的具体对象，例如设备
     */
    @Column(name = "`work_obj_name`")
    private String workObjName;
    /**
     * 作业对象，可选，如果填上就是做详细计划，不填那么就是简单管控，再作业内容中做说明
     */
    @Column(name = "`work_root_obj`")
    private String workRootObj;
    /**
     * 作业对象，可选，如果填上就是做详细计划，不填那么就是简单管控，再作业内容中做说明
     */
    @Column(name = "`work_root_obj_name`")
    private String workRootObjName;
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
     * 可以通过位置自动预生成
     */
    @Column(name = "`adcode`")
    private String adcode;
    /**
     * 经度
     */
    @Column(name = "`longitude`")
    private Double longitude;
    /**
     * 纬度
     */
    @Column(name = "`latitude`")
    private Double latitude;
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
     * 锁定状态 0：未锁定 1：已锁定
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

    @Column(name = "`defect_org`")
    private String defectOrg;

    @Column(name = "`defect_org_name`")
    private String defectOrgName;

    @Column(name = "`defect_root_org`")
    private String defectRootOrg;

    @Column(name = "`defect_root_org_name`")
    private String defectRootOrgName;
    /**
     * 工单状态 1：待派工 2：待处理 3：待结案 4：已结案
     */
    @Column(name = "`order_status`")
    private Integer orderStatus;
    /**
     * 工单挂起状态 1：已挂起 0：未挂起
     */
    @Column(name = "`suspended_status`")
    private Integer suspendedStatus;
    /**
     * 工单处理截至时间，天数
     */
    @Column(name = "`deadline`")
    private Integer deadline;
    /**
     * 完工时间
     */
    @Column(name = "`complete_time`")
    private Long completeTime;

    @Column(name = "`complete_content`")
    private String completeContent;

    @Column(name = "`complete_user`")
    private Long completeUser;

    @Column(name = "`declared_amount`")
    private Double declaredAmount;

    @Column(name = "`disposal_note`")
    private String disposalNote;

    @Column(name = "`out_order_code`")
    private String outOrderCode;

    @Column(name = "`in_order_code`")
    private String inOrderCode;
    /**
     * 备注
     */
    @Column(name = "`note`")
    private String note;

    /**
     * 父工单id
     */
    @Column(name = "`work_order_record_id`")
    private Long workOrderRecordId;

    @Column(name = "`create_user_id`")
    private Long createUserId;

    @Column(name = "`update_user_id`")
    private Long updateUserId;

    @Column(name = "`work_obj_asset_def_id`")
    private Long workObjAssetDefId;

    @Column(name = "`work_order_id`")
    private Long workOrderId;


}
