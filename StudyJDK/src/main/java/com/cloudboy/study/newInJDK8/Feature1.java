package com.cloudboy.study.newInJDK8;

/**
 * ����1��Interface֧�ֶ���Ĭ��ʵ��
 * @author yunxi
 *
 */
public interface Feature1 {
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
