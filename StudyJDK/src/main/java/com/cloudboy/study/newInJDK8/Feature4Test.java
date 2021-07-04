package com.cloudboy.study.newInJDK8;

public class Feature4Test {
	
	public static void main(String[] args) {
		// 构造函数的引用
		Feature4Factory<Feature4> factory = Feature4::new;
		Feature4 feature4Instance = factory.create("Martin", "Xia");
		System.out.println(feature4Instance.whatYourName());
		
		// 方法的引用
		Feature4Function feature4Function = feature4Instance::whatYourName;
		System.out.println(feature4Function.saySomething());
	}

}
