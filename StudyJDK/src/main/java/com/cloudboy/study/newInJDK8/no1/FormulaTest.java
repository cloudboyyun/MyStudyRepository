package com.cloudboy.study.newInJDK8.no1;

public class FormulaTest implements Formula {

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
		Formula feature1 = new FormulaTest();
		System.out.println(feature1.calculate(2));
		System.out.println(feature1.sqrt(9));
	}

}
