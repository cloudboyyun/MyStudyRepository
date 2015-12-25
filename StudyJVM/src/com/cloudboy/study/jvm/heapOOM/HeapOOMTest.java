package com.cloudboy.study.jvm.heapOOM;

/*
 * VM Args: -Xms20m -Xmx20m -XX:HeapDumpPath=d:/temp -XX:+HeapDumpOnOutOfMemoryError
 */

import java.util.ArrayList;
import java.util.List;

public class HeapOOMTest {
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true) {
			list.add(new OOMObject());
		}
	}
}
