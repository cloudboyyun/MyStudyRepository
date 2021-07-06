package com.cloudboy.study.newInJDK8.no6_map;

import java.util.HashMap;
import java.util.Map;

import com.cloudboy.util.json.FastJsonUtils;

public class MapTest {
	
	private static Map<Integer, String> initData() {
		Map<Integer, String> map = new HashMap<>();
		for(int i=0; i<10; i++) {
			map.put(i, "val" + i);
		}
		System.out.println(FastJsonUtils.toJSONString(map));
		return map;
	}
	
	public static void test1() {
		// No1. 不存在才放入
		Map<Integer, String> map = initData();
		map.putIfAbsent(0, "newValue");
		System.out.println(FastJsonUtils.toJSONString(map));
		
		// No2. forEach
		map = initData();
		map.forEach((k, v) -> System.out.print(v + " "));
		System.out.println();
		
		// No3. computeIfPresent: 如果key存在并且value不为null
		map = initData();
		map.computeIfPresent(2, (k, v) -> "newValue");
		System.out.println(FastJsonUtils.toJSONString(map));
		
		//  No4. computeIfAbsent: 如果key不存在或者value为null，则插入该键值
		map = initData();
		map.computeIfAbsent(10, (key) -> "val" + key);
		System.out.println(FastJsonUtils.toJSONString(map));
		
		// No5. 只有key, value都符合才删除
		map = initData();
		map.remove(0, "val000");
		System.out.println(FastJsonUtils.toJSONString(map));
		map = initData();
		map.remove(0, "val0");
		System.out.println(FastJsonUtils.toJSONString(map));
		
		// No6. getOrDefault
		map = initData();
		String val100 = map.getOrDefault(100, "Val100");
		System.out.println(val100);
		
		// No7. 新、旧值操作
		map = initData();
		map.merge(8, "newValueOf8", (value, newValue) -> value + newValue);
		System.out.println(FastJsonUtils.toJSONString(map));
	}

	public static void main(String[] args) {
		test1();
	}

}
