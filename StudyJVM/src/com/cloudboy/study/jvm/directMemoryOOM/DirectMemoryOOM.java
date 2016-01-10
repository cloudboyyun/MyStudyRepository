package com.cloudboy.study.jvm.directMemoryOOM;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * VM args: -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 */
public class DirectMemoryOOM {
	private static final int _1MB = 1024*1024;
	
	public void oom() throws IllegalArgumentException, IllegalAccessException {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		while(true) {
			unsafe.allocateMemory(_1MB);
		}
		
	}
	
	public static void main(String[] args) {
		DirectMemoryOOM oom = new DirectMemoryOOM();
		try {
			oom.oom();
		} catch(Throwable e) {
			e.printStackTrace();
		}

	}

}
