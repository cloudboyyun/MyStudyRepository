package com.cloudboy.study.newInJDK8.no1;

public interface Formula {
	double calculate(int a);
	
	/**
	 * ��Ĭ��ʵ�ֵĽӿڷ�������
	 * @param a
	 * @return
	 */
	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}
