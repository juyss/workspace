package com.icepoint.framework.workorder.work.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.BaseEntity;
import lombok.*;

import javax.persistence.Column;

/**
 * 作业任务协助人关联表(WorkOrderAssistant)实体类
 *
 * @author makejava
 * @since 2021-07-22 17:44:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("work_order_assistant")
public class WorkOrderAssistant extends BaseEntity<Long> {

    /**
     * 作业任务id
     */
    @Column(name = "`table_id`")
    private Long tableId;
    /**
     * 对象id
     */
    @Column(name = "`obj_id`")
    private Long objId;
    /**
     * 协助人id
     */
    @Column(name = "`assistant_id`")
    private Long assistantId;

    /**
     * 业务类型
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     *协助人姓名
     */
    private String  assistantName;

}
