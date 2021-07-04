package com.cloudboy.study.newInJDK8;

/**
 * 方法与构造函数的引用，使用符号：::
 * @author yunxi
 *
 */
public class Feature4 {
	private String firstName = "Unknow";
	private String lastName  = "Unknow";
	
	public Feature4() {
	}
	
	public Feature4(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String whatYourName() {
		return "" + firstName + " " + lastName;
	}
	
}
