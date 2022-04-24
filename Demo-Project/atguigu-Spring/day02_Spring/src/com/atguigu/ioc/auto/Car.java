package com.atguigu.ioc.auto;

public class Car extends CarExtends {

	private Integer cid;
	
	private String cname;

	@Override
	public String toString() {
		return "Car [cid=" + cid + ", cname=" + cname + "]";
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
