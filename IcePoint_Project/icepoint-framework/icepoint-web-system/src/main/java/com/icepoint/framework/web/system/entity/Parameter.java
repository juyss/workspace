package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.StdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统参数类
 *
 * @author ck
 * @since 2021-05-25 16:48:03
 */
@TableName("sys_parameter")
@Table(name = "sys_parameter")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Parameter extends StdEntity<Long, Long> {

	/**
	 * 系统参数类型
	 * 通过数据字典定义
	 * 1-app级别
	 * 2-platform级别
	 * 3-owner级别
	 */
    @Column(name = "`type`")
	private Integer type;
	
    @Column(name = "`param_code`")
    private String paramCode;

    @Column(name = "`param_name`")
    private String paramName;

    @Column(name = "`param_name_en`")
    private String paramNameEn;

    @Column(name = "`cval`")
    private String cval;

    @Column(name = "`use_type`")
    private Integer useType;

    @Column(name = "`audit_flag`")
    private Integer auditFlag;

    @Column(name = "`remarks`")
    private String remarks;
}
