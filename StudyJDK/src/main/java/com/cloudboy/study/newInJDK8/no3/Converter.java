package com.cloudboy.study.newInJDK8.no3;

/**
 * ����ʽ�ӿڣ����ǿ��Խ�lambda���ʽ��������ֻ����һ�����󷽷��Ľӿ����ͣ�ȷ����Ľӿ�һ���ﵽ���Ҫ����ֻ��Ҫ����Ľӿ���� @FunctionalInterface
 * ע�⣬����������������ע�����ע��Ľӿ��ж���һ�����󷽷���ʱ��ᱨ��ġ� 
 * ʵ�ַ���ʾ����
   		
   		Feature3<String, Integer>
  		converter = (from) -> {
  			return Integer.valueOf(from);
  		}; 
   		int result = converter.convert("789"); 

 * @author yunxi
 *
 * @param <F>
 * @param <T>
 */

@FunctionalInterface
public interface Converter<F, T> {
	T convert(F from);
}