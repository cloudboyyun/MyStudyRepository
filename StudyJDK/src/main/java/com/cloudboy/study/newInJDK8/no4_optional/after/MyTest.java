package com.cloudboy.study.newInJDK8.no4_optional.after;

import java.util.Optional;

public class MyTest {
	
	public static void test1() {
		User user = new User("cloudboy@123.com");
		String isoCode = 
				Optional.ofNullable(user)
				.flatMap(u -> u.getAddress())
				.flatMap(a -> a.getCountry())
				.map(c -> c.getIsoCode())
				.orElse(null);
		System.out.println("isoCode is: " + isoCode);
	}
	
	public static void test2() {
		User user = new User("cloudboy@123.com");
		Address address = new Address();
		user.setAddress(address);
		Country country = new Country();
		country.setIsoCode("0086");
		address.setCountry(country);
		String isoCode = 
				Optional.ofNullable(user)
				.flatMap(u -> u.getAddress())
				.flatMap(a -> a.getCountry())
				.map(c -> c.getIsoCode())
				.orElse(null);
		System.out.println("isoCode is: " + isoCode);
	}

	public static void main(String[] args) {
		test1();
		test2();
	}

}
