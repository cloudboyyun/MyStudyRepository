package com.cloudboy.study.newInJDK8.no1;

public interface Formula {
	double calculate(int a);
	
	/**
	 * 带默认实现的接口方法定义
	 * @param a
	 * @return
	 */
	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}
