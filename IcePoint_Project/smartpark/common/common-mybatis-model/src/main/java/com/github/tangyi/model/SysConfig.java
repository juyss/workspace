package com.github.tangyi.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * sys_config 系统配置
 *
 * @author xh
 * @since 2020/10/30
 */
@Table(name = "sys_config")
public class SysConfig extends BaseEntity<SysConfig> {

    private static final long serialVersionUID = 1L;

	/**
	 * code 配置标识
	 */
	@Column(name = "code") 
	protected String code;

	/**
	 * parent_code 父配置标识
	 */
	@Column(name = "parent_code") 
	protected String parentCode;

	/**
	 * name 名称
	 */
	@Column(name = "name") 
	protected String name;

	/**
	 * value 配置值，若配置信息较短，使用这个字段
	 */
	@Column(name = "value") 
	protected String value;

	/**
	 * config 配置内容，若配置信息太多，使用这个字段
	 */
	@Column(name = "config") 
	protected String config;

	/**
	 * remark 备注
	 */
	@Column(name = "remark") 
	protected String remark;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    @Override
    public String toString() {
        return "sys_config{" +
                "id=" + id +
                ", code=" + code +
                ", parentCode=" + parentCode +
                ", name=" + name +
                ", value=" + value +
                ", config=" + config +
                ", remark=" + remark +
                ", creator=" + creator +
                ", createDate=" + createDate +
                ", modifier=" + modifier +
                ", modifyDate=" + modifyDate +
                ", delFlag=" + delFlag +
                ", applicationCode=" + applicationCode +
                ", tenantCode=" + tenantCode +
                '}';
    }

}
