package com.cloudboy.study.newInJDK8.no4_optional.before;

public class MyTest {
	
	// 会报NullPointerException
	public static void test1() {
		User user = new User("cloudboy@123.com");
		String isoCode = user.getAddress().getCountry().getIsoCode();
		System.out.println("isoCode is: " + isoCode);
	}
	
	// 为了防止NullPointerException，我们需要进行很多判断
	public static void test2() {
		String isoCode = null;
		User user = new User("cloudboy@123.com");
		Address address = user.getAddress();
		if(address != null) {
			Country country = address.getCountry();
			if(country != null) {
				isoCode = country.getIsoCode();
			}
		}
		System.out.println("isoCode is: " + isoCode);
	}

	public static void main(String[] args) {
		test2();
	}

}
