package com.icepoint.framework.web.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@ApiModel("响应信息")
@TableName("sys_response_message")
@Table(name = "sys_response_message")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class ResponseMessage extends LongStdEntity {

    @ApiModelProperty("名称")
    @Length(max = 16)
    @Column(name = "`name`")
    private String name;

    @ApiModelProperty("消息码")
    @NotNull
    @Length(max = 16)
    @Column(name = "`code`")
    private String code;

    @ApiModelProperty("响应信息")
    @Length(max = 1024)
    @Column(name = "`message`")
    private String message;

    @ApiModelProperty("备注")
    @Length(max = 128)
    @Column(name = "`note`")
    private String note;

}
