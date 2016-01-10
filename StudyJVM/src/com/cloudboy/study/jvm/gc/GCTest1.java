package com.cloudboy.study.jvm.gc;

/**
 * JVM参数：
 * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * 
 * 20M堆内存，不可扩展。新生代大小是10M = 10240K(剩下的10M就是老年代的大小了)。 打印GC日志。
 * 新生代中Eden区和一个Survivor区的比例是8:1，所以Eden区的大小是10240*8/10=8192K，
 * 注意有两个Survivor区，一个可用，一个是来实现复制算法的：
 * 新生代总的可用内存：8192 + 1024 = 9216K
 * 堆的可用内存：20480k - 1024 = 19456k
 */

public class GCTest1 {
	private static final int _1MB = 1024*1024;
	
	public static void testAllocation() {
		byte[] allocation;
		for(int i=1; i<=10; i++) {
			System.out.println("i=" + i + "(used mem:" + (i*_1MB/1024) + "k)");
			allocation = new byte[_1MB];
		}
		System.gc();  // 这里主动触发一次full gc是为了让读者能对比一下Minor GC和Full GC的输出日志的不同。
	}
	
	public static void main(String[] args) {
		testAllocation();
	}

}
