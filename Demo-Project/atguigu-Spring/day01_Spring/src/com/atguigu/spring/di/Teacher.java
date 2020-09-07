package com.atguigu.spring.di;

import java.util.List;
import java.util.Map;

public class Teacher {

	private Integer tid;
	
	private String tname;
	
	private List<String> cls;
	
	private List<Student> students;

	private Map<String, String> bossMap;

	public Map<String, String> getBossMap() {
		return bossMap;
	}

	public void setBossMap(Map<String, String> bossMap) {
		this.bossMap = bossMap;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<String> getCls() {
		return cls;
	}

	public void setCls(List<String> cls) {
		this.cls = cls;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tname=" + tname + ", cls=" + cls + ", students=" + students + ", bossMap="
				+ bossMap + "]";
	}
	
}
