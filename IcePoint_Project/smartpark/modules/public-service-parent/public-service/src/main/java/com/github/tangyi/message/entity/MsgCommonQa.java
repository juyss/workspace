package com.github.tangyi.message.entity;

import com.github.tangyi.common.core.persistence.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName MsgCommonQa
 * @description 政民互动常见问题实体类
 * @since 2021-05-20 11:46
 */
@Table(name="msg_common_qa")
@ApiModel(value="MsgCommonQa")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MsgCommonQa extends TreeEntity<MsgCommonQa>{
    /**
     * 主键，常见问题ID
     */
    @Id
    @ApiModelProperty(value="主键，常见问题ID")
    private Long id;

    /**
     * 序号，同一级的显示排序
     */
    @ApiModelProperty(value="序号，同一级的显示排序")
    @Column(name = "idx")
    private Double idx;

    /**
     * 父节点
     */
    @ApiModelProperty(value="父节点")
    @Column(name = "parentId")
    private Long parentId;

    /**
     * 自动回复主题
     */
    @ApiModelProperty(value="自动回复主题")
    @Column(name = "common_qa_theme")
    private String commonQaTheme;

    /**
     * 自动回复内容
     */
    @ApiModelProperty(value="自动回复内容")
    @Column(name = "common_qa_content")
    private String commonQaContent;

    /**
     * 自动回复创建时间
     */
    @ApiModelProperty(value="自动回复创建时间")
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建者id
     */
    @ApiModelProperty(value="创建者id")
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 创建者用户名
     */
    @ApiModelProperty(value="创建者用户名")
    @Column(name = "create_user_name")
    private String createUserName;

    /**
     * 最后更改时间
     */
    @ApiModelProperty(value="最后更改时间")
    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * 删除标记 0:正常;1:删除
     */
    @ApiModelProperty(value="删除标记 0:正常;1:删除")
    @Column(name = "del_flag")
    private Integer delFlag;
}
