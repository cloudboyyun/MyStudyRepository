package com.cloudboy.study.newInJDK8.no3;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Feature3Test {
	
	private static int var2 = 1;
	
	public static void main(String[] args) {
		// 1.1 ����ʽ�ӿ�
		Converter<String, Integer> converter1 = (from) -> Integer.valueOf(from);
		int result = converter1.convert("789");
		System.out.println(result);
		
		// 2.1 ���캯��������
		PersonFactory<Person> factory = Person::new;
		Person instance = factory.create("Martin", "Xia");
		System.out.println(instance.whatYourName());
		
		// 2.2 ����������-ʾ��1
		MyFunction myFunction = instance::whatYourName;
		System.out.println(myFunction.saySomething());
		
		// 2.3 ����������-ʾ��2
		Converter<String, Integer> converter2 = Integer::valueOf;
		result = converter2.convert("987");
		System.out.println(result);
		
		// 3.1 lambda���ʽ�з��ʵľֲ�����������final����ʽfinal�ģ����������޸ģ�
		final int var1 = 1;
		Converter<Integer, String> converter3 = (from) -> {
			return String.valueOf(from + var1);
		};
		String s = converter3.convert(5);
		System.out.println(s);
		
		// 3.2 lambda���ʽ�з��ʵ���ĳ�Ա�����������޸ĵ�
		Converter<Integer, String> converter4 = (from) -> {
			var2 = from + var2;
			return String.valueOf(var2);
		};
		s = converter4.convert(5);
		System.out.println(s);
		System.out.println(var2);
		
		// 4.1 jdk 8�Դ��ĺ���ʽ�ӿ�Predicate
		Predicate<String> predicate = (value) -> {
			return Objects.isNull(value) || value.length() == 0;
		};
		s = null;
		System.out.println(predicate.test(s));
		s = "";
		System.out.println(predicate.test(s));
		s = "abc";
		System.out.println(predicate.test(s));
		
		// 4.2 jdk 8�Դ��ĺ���ʽ�ӿ�Function
		Function<Integer, String> intToString = String::valueOf;
		Function<Integer, Double> intToDouble = intToString.andThen(Double::valueOf);
		Double i42 = intToDouble.apply(789);
		System.out.println(i42);
		
		// 4.3 jdk 8�Դ��ĺ���ʽ�ӿ�Supplier
		Supplier<Person> personSupplier = Person::new;
		Person person = personSupplier.get();
		System.out.println(person);
		
		// 4.4 jdk 8�Դ��ĺ���ʽ�ӿ�Consumer
		Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.getFirstName());
		greeter.accept(new Person("Luke", "Skywalker"));
		
		// 4.5 Optional��������಻�ǽӿ�
		Optional<String> optional = Optional.of("bam");
		optional.isPresent();           // true
		optional.get();                 // "bam"
		optional.orElse("fallback");    // "bam"
		optional.ifPresent((value) -> System.out.println(value.charAt(0))); 
	}

}
