package com.github.tangyi.common.core.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.utils.DateUtils;
import com.github.tangyi.common.core.utils.IdGen;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Entity基类
 *
 * @author tangyi
 * @date 2018-08-24 18:58
 */
@SuperBuilder
@Data
@NoArgsConstructor
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id--公共字段")
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    protected Long id;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者--公共字段")
    protected String creator;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期--公共字段")
    @Column(name = "create_date")
    protected Date createDate;

    /**
     * 更新者
     */
    protected String modifier;

    /**
     * 更新日期
     */
    @ApiModelProperty(value = "更新日期--公共字段")
    @Column(name = "modify_date")
    protected Date modifyDate;

    /**
     * 删除标记 0:正常，1-删除
     */
    @Column(name = "del_flag")
    @ApiModelProperty(value = "删除标记 0:正常，1-删除--公共字段")
    protected Integer delFlag = CommonConstant.DEL_FLAG_NORMAL;

    /**
     * 系统编号
     */
    @ApiModelProperty(value = "系统编号 不输也可，有默认值--公共字段")
    @Column(name = "application_code")
    protected String applicationCode;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "系统编号--公共字段")
    @Column(name = "tenant_code")
    protected String tenantCode;

    /**
     * 是否为新记录
     */
    @ApiModelProperty(value = "是否为新记录--公共字段,不映射该属性到数据库")
    @Transient  //不映射该属性到数据库
    protected boolean isNewRecord;

	/**
	 * 扩展字段
	 */
    @Transient  //不映射该属性到数据库
    @JsonIgnore //转json的时候忽略 不输出应字段
	protected String ext;

    public BaseEntity(Long id) {
        this();
        this.id = id;
    }

    /**
     * 是否为新记录
     *
     * @return boolean
     */
    public boolean isNewRecord() {
        return this.isNewRecord || this.getId() == null;
    }

    /**
     * 设置基本属性
     *
     * @param userCode        用户编码
     * @param applicationCode 系统编号
     * @param tenantCode      租户编号
     */
    public void setCommonValue(String userCode, String applicationCode, String tenantCode) {
        Date currentDate = DateUtils.asDate(LocalDateTime.now());
        if (this.isNewRecord()) {
            this.setId(IdGen.snowflakeId());
            this.setNewRecord(true);
            this.creator = userCode;
            this.createDate = currentDate;
        }
        this.modifier = userCode;
        this.modifyDate = currentDate;
        this.delFlag = 0;
        this.applicationCode = applicationCode;
        this.tenantCode = tenantCode;
    }

	/**
	 * 置空属性
	 */
	public void clearCommonValue() {
		this.creator = null;
		this.createDate = null;
		this.modifier = null;
		this.modifyDate = null;
    	this.delFlag = null;
    	this.applicationCode = null;
    	this.tenantCode = null;
	}
}

