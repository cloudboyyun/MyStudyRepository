package com.cloudboy.study.jvm.MethodAreaOOM;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 
 */
public class RumtimeConstantPoolOOM {

	public void oom() {
		// 使用List保持着常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();

		// 10MB的PermSize在Integer范围内足够产生OOM了
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}

	public static void main(String[] args) {
		RumtimeConstantPoolOOM oom = new RumtimeConstantPoolOOM();
		try {
			oom.oom();
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}

}
