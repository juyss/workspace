package com.github.tangyi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * sys_user_dept 
 *
 * @author xh
 * @since 2020/12/28
 */
@Table(name = "sys_user_dept")
public class SysUserDept implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@Column(name = "id") 
	protected Long id;

	/**
	 * user_id 用户id
	 */
	@Column(name = "user_id") 
	protected Long userId;

	/**
	 * dept_id 部门id
	 */
	@Column(name = "dept_id") 
	protected Long deptId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

    @Override
    public String toString() {
        return "sys_user_dept{" +
                "id=" + id +
                ", userId=" + userId +
                ", deptId=" + deptId +
                '}';
    }

}
