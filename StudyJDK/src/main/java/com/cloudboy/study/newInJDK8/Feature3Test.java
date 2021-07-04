package com.cloudboy.study.newInJDK8;

public class Feature3Test {
	
	static public void testFeature3() {
		
	}
	
	static public void testFeature4() {
		Feature3<String, Integer> converter = Integer::valueOf;
		int result = converter.convert("789");
		System.out.println(result);
	}

	public static void main(String[] args) {
		Feature3<String, Integer> converter = (from) -> {
			return Integer.valueOf(from);
		};
		int result = converter.convert("789");
		System.out.println(result);
	}

}
