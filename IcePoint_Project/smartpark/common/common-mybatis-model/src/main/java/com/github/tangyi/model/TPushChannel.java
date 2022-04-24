package com.github.tangyi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * t_push_channel 推送渠道表
 *
 * @author jy
 * @since 2020/11/03
 */
@Table(name = "t_push_channel")
public class TPushChannel implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "id") 
	protected Long id;

	/**
	 * name 渠道名称
	 */
	@Column(name = "name") 
	protected String name;

	/**
	 * code 渠道代码
	 */
	@Column(name = "code") 
	protected String code;

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

    @Override
    public String toString() {
        return "t_push_channel{" +
                "id=" + id +
                ", name=" + name +
                ", code=" + code +
                '}';
    }

}
