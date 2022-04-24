package com.atguigu.ioc.scope;

public class Student {

	private Integer sid;
	
	private String sname;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Student() {
		System.out.println("Student 构造方法");
	}

	/*@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + "]";
	}*/
	
	
}
