package com.atguigu.ioc.auto;

public class Emp {

	private Integer eid;
	
	private String ename;
	
	private CarExtends car;
	
	private DeptI dept;

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

	public CarExtends getCar() {
		return car;
	}

	public void setCar(CarExtends car) {
		this.car = car;
	}

	public DeptI getDept() {
		return dept;
	}

	public void setDept(DeptI dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", ename=" + ename + ", car=" + car + ", dept=" + dept + "]";
	}

}
