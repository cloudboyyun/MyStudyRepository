package com.cloudboy.study.newInJDK8;

/**
 * �����빹�캯�������ã�ʹ�÷��ţ�::
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
