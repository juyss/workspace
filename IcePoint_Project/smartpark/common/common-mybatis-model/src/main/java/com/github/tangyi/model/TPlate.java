package com.github.tangyi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_plate 板块
 *
 * @author jy
 * @since 2020/11/03
 */
@Table(name = "t_plate")
public class TPlate implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@Column(name = "id") 
	protected Long id;

	/**
	 * name 板块名称
	 */
	@Column(name = "name") 
	protected String name;

	/**
	 * code 板块code
	 */
	@Column(name = "code") 
	protected String code;

	/**
	 * create_time 板块创建时间
	 */
	@Column(name = "create_time") 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	protected Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    @Override
    public String toString() {
        return "t_plate{" +
                "id=" + id +
                ", name=" + name +
                ", code=" + code +
                ", createTime=" + createTime +
                '}';
    }

}
