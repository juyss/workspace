package com.atguigu.ioc.life;

public class Person {

	private Integer id;
	
	private String sex;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		System.out.println("Two: 依赖注入");
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person() {
		System.out.println("One：创建对象");
	}
	
	@Override
	public String toString() {
		return "Four: Person [id=" + id + ", sex=" + sex + ", name=" + name + "]";
	}

	public void init() {
		System.out.println("Three: 初始化");
	}
	
	public void destory () {
		System.out.println("Five: 销毁");
	}
	
}
