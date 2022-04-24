package com.icepoint.framework.workorder.work.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("work_order_plan_send")
@Table(name = "work_order_plan_send")
public class WorkPlanSend extends LongStdEntity {
    /**
     * 作业计划
     */
    @Column(name = "`plan_code`")
    private String planCode;
    /**
     * 作业接收者类型
     */
    @Column(name = "`receive_type`")
    private Integer receiveType;
    /**
     * 接收者
     */
    @Column(name = "receiver")
    private String receiver;
    /**
     * 备注
     */
    @Column(name = "note")
    private String note;
    
    public WorkPlanSend(Map<String, Object> entity){
    	
    }
}
