package com.cloudboy.study.newInJDK8;

/**
 * 特性1：Interface支持定义默认实现
 * @author yunxi
 *
 */
public interface Feature1 {
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
