package com.atguigu.bean;

import java.io.Serializable;

public class Emp implements Serializable {

	private Integer eid;
	
	private String ename;
	
	private Integer age;
	
	private String sex;
	
	private Dept dept;

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", ename=" + ename + ", age=" + age + ", sex=" + sex + ", dept=" + dept + "]";
	}

	public Emp(Integer eid, String ename, Integer age, String sex) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.age = age;
		this.sex = sex;
	}

	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
