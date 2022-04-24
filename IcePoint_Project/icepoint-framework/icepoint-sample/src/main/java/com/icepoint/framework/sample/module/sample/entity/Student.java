package com.icepoint.framework.sample.module.sample.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * @author Jiawei Zhao
 */
@TableName("sample_student")
@Table(name = "sample_student")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Student extends LongStdEntity {

    @TableField("`teacher_id`")
    @Column(name = "`teacher_id`")
    private Long teacherId;

    @TableField("`name`")
    @Column(name = "`name`")
    private String name;

    @JsonIgnoreProperties("students")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`teacher_id`", insertable = false, updatable = false)
    @TableField(exist = false)
    private Teacher teacher;
}
