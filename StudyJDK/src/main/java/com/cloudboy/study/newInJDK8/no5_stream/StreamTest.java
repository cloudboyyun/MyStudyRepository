package com.cloudboy.study.newInJDK8.no5_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.cloudboy.util.json.FastJsonUtils;

public class StreamTest {

	public static void test1() {
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("121");
		stringCollection.add("221");
		stringCollection.add("123");
		stringCollection.add("3214");
		stringCollection.add("5212");
		stringCollection.add("4212");
		stringCollection.add("1000");
		stringCollection.add("100");
		System.out.println(FastJsonUtils.toJSONString(stringCollection));

		// filter
		stringCollection.stream().filter((s) -> s.startsWith("1")).forEach(System.out::println);

		// sort，注意不会改变源数据组
		Stream<String> stream1 = stringCollection.stream().sorted();
		System.out.println(FastJsonUtils.toJSONString(stream1.toArray()));

		// map, map会将元素根据指定的Function接口来依次将元素转成另外的对象
		Stream<Integer> stream2 = stringCollection.stream().map(Integer::valueOf).sorted();
		System.out.println(FastJsonUtils.toJSONString(stream2.toArray()));

		// match
		boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("1"));
		System.out.println(anyStartsWithA); // true

		boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("1"));
		System.out.println(allStartsWithA); // false

		boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("9"));
		System.out.println(noneStartsWithZ); // true
		
		// count
		long count = stringCollection.stream().filter(s -> s.startsWith("1")).count();
		System.out.println(count);
		
		// reduce: 将stream中的多个元素规约为一个元素，规越后的结果是通过Optional接口表示
		Optional<String> reduceResult = stringCollection.stream().reduce((s1, s2) -> s1 + "#" + s2);
		reduceResult.ifPresent(System.out::println);
		System.out.println(reduceResult.get());
	}
	
	public static void test2() {
		int max = 2000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
		    UUID uuid = UUID.randomUUID();
		    values.add(uuid.toString());
		}
		
		// 串行排序
		long t0 = System.nanoTime();
		long count = values.stream().sorted().count();
		System.out.println(count);
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));
		
		// 并行排序
		t0 = System.nanoTime();
		count = values.parallelStream().sorted().count();
		System.out.println(count);
		t1 = System.nanoTime();
		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("parallel sort took: %d ms", millis));
	}

	public static void main(String[] args) {
		test2();
	}
}
