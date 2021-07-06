package com.cloudboy.study.newInJDK8.no4_optional.after;

import java.util.Optional;

public class User {
	private String email;
	private Address address;
	
	public User(String email) {
		this.email = email;
	}

	public Optional<Address> getAddress() {
		return Optional.ofNullable(address);
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
