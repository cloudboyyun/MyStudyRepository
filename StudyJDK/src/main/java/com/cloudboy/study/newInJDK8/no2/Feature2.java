package com.cloudboy.study.newInJDK8.no2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 特性2：lambda表达式
 * @author yunxi
 *
 */
public class Feature2 {
	
	// jdk8之前的写法
	static List<String> f1(List<String> list) {
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		return list;
	}
	
	// jdk8后，可使用lambda表达式
	static List<String> f2(List<String> list) {
		Collections.sort(list, (String o1, String o2) -> {
			return o2.compareTo(o1);
		});
		return list;
	}
	
	// 更简短的写法
	static List<String> f3(List<String> list) {
		Collections.sort(list, (String o1, String o2) -> o2.compareTo(o1));
		return list;
	}
	
	// 更更简短的写法
	static List<String> f4(List<String> list) {
		Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
		return list;
	}
	
	public static void main(String[] args) {
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		System.out.println(f1(names));
		System.out.println(f2(names));
		System.out.println(f3(names));
		System.out.println(f4(names));
	}

}
