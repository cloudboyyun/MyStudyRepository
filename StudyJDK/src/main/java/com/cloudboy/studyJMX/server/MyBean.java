package com.cloudboy.studyJMX.server;

public class MyBean implements MyBeanMBean {
	private String name;
	private int age;
	
	public MyBean(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public String doChange() {
		return "Name=" + name + " and age=" + age;
	}
}
