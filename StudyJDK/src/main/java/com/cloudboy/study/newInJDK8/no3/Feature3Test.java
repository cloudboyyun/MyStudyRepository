package com.cloudboy.study.newInJDK8.no3;

public class Feature3Test {
	
	private static int var2 = 1;
	
	public static void main(String[] args) {
		// 函数式接口
		Converter<String, Integer> converter1 = (from) -> Integer.valueOf(from);
		int result = converter1.convert("789");
		System.out.println(result);
		
		// 构造函数的引用
		PersonFactory<Person> factory = Person::new;
		Person instance = factory.create("Martin", "Xia");
		System.out.println(instance.whatYourName());
		
		// 方法的引用-示例1
		MyFunction myFunction = instance::whatYourName;
		System.out.println(myFunction.saySomething());
		
		// 方法的引用-示例2
		Converter<String, Integer> converter2 = Integer::valueOf;
		result = converter2.convert("987");
		System.out.println(result);
		
		// lambda表达式中访问的局部变量必须是final或隐式final的（即不允许修改）
		final int var1 = 1;
		Converter<Integer, String> converter3 = (from) -> {
			return String.valueOf(from + var1);
		};
		String s = converter3.convert(5);
		System.out.println(s);
		
		// lambda表达式中访问的类的成员变量是允许修改的
		Converter<Integer, String> converter4 = (from) -> {
			var2 = from + var2;
			return String.valueOf(var2);
		};
		s = converter4.convert(5);
		System.out.println(s);
		System.out.println(var2);
	}

}
