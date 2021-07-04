package com.cloudboy.study.newInJDK8;

/**
 * 函数式接口：我们可以将lambda表达式当作任意只包含一个抽象方法的接口类型，确保你的接口一定达到这个要求，你只需要给你的接口添加 @FunctionalInterface
 * 注解，编译器如果发现你标注了这个注解的接口有多于一个抽象方法的时候会报错的。 
 * 实现方法示例：
   		
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
public interface Feature3<F, T> {
	T convert(F from);
}