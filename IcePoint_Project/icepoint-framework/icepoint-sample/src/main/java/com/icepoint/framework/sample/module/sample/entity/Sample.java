package com.icepoint.framework.sample.module.sample.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.List;

@ApiModel("示例")
@TableName("sample_sample")
@Table(name = "sample_sample")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@Generated("com.icepoint.framework.generator.EntityGenerator")
public class Sample extends LongBaseEntity {

    @ApiModelProperty("字符串")
    @Column(name = "`text`")
    private String text;

    @ApiModelProperty("一对一")
    @Column(name = "sample_one_id", updatable = false, insertable = false)
    private Long sampleOneId;

    @ApiModelProperty("多对一")
    @Column(name = "sample_many_id", updatable = false, insertable = false)
    private Long sampleManyId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_one_id")
    @TableField(exist = false)
    private SampleOne sampleOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_many_id")
    @TableField(exist = false)
    private SampleMany sampleMany;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sample_sample_many",
            joinColumns = @JoinColumn(name = "sample_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sample_many_id", referencedColumnName = "id")
    )
    @TableField(exist = false)
    private List<SampleManyMany> sampleManyManies;

}
