package com.cloudboy.study.newInJDK8;

public class Feature1Impl implements Feature1 {

	@Override
	public double calculate(int a) {
		return a * a;
	}
	
//	@Override
//	public double sqrt(int a) {
//		System.out.println("改写了接口的缺省实现");
//		return Math.sqrt(a);
//	}
	
	public static void main(String[] args) {
		Feature1 feature1 = new Feature1Impl();
		System.out.println(feature1.calculate(2));
		System.out.println(feature1.sqrt(9));
	}

}
