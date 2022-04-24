package com.icepoint.framework.workorder.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

/**
 * 作业记录
 *
 * @author 
 */
@Entity
@Table(name = "work_order_task_note")
@TableName("work_order_task_note")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkTaskNote extends LongStdEntity {


    @Column(name = "id")
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 作业任务
     */
    @Column(name = "`task_id`")
    private Long taskId;

    /**
     * 作业人
     */
    @Column(name = "`cust_id`")
    private Long custId;
 
    /**
     * 类型
     * 英文，标识，可以结合工作流使用，工作流种的相关记录也可以纳入
     * 初始包含作业中记录和完结记录
     */
    @Column(name = "`type`")
    private String type;
    
    /**
     *  内容
     */
    @Column(name = "`content`")
    private String content;
    
    /**
     *  附件
     *  一般是拍照
     */
    @Column(name = "`annexe_ids`")
    private String annexeIds;

    /**
     *  签名
     *  一般为签名图片
     */
    @Column(name = "`signature`")
    private String signature;
    
    /**
     * 备注/描述
     */
    @Column(name = "note")
    private String note;
    
    public WorkTaskNote(Map<String, Object> entity){
    	
    }
}
