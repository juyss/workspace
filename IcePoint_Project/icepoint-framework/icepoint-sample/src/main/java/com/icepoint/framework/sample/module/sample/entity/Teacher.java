package com.icepoint.framework.sample.module.sample.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
@TableName("sample_teacher")
@Table(name = "sample_teacher")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Teacher extends LongStdEntity {

    @TableField("`name`")
    @Column(name = "`name`")
    private String name;

    @JsonIgnoreProperties("teacher")
    @TableField(exist = false)
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Student> students;
}
