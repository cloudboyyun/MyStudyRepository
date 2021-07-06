package com.cloudboy.study.newInJDK8.no4_optional.before;

public class User {
	private String email;
	private Address address;
	
	public User(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
