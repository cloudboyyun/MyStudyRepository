package com.cloudboy.study.newInJDK8.no3;

public class Person {
	private String firstName = "Unknow";
	private String lastName  = "Unknow";
	
	public Person() {
	}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String whatYourName() {
		return "" + firstName + " " + lastName;
	}
	
}
