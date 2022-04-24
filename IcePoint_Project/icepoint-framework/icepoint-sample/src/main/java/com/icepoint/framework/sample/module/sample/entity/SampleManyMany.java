package com.icepoint.framework.sample.module.sample.entity;

import com.icepoint.framework.data.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
@ApiModel("示例")
@Table(name = "sample_many_many")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class SampleManyMany extends BaseEntity<Long> {

    @ApiModelProperty("字符串")
    @Column(name = "text")
    private String text;

    @ManyToMany(mappedBy = "sampleManyManies")
    private List<Sample> samples;
}
