package com.cloudboy.study.jvm.gc;

/**
 * VM参数：-XX:+UseConcMarkSweepGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 * 使用ParNew + CMS + Serial Old组合(XX:PretenureSizeThreshold参数对Parallel Scavenge无效)
 * 20M堆内存，不可扩展。新生代大小是10M = 10240K(剩下的10M就是老年代的大小了)。 打印GC日志。
 * 老年代内存：20M-10M = 10M = 10240K
 * 新生代中Eden区和一个Survivor区的比例是8:1，所以Eden区的大小是10240*8/10=8192K，
 * 注意有两个Survivor区，一个可用，一个是来实现复制算法的：
 * 新生代总的可用内存：8192 + 1024 = 9216K
 * 堆的可用内存：20480k - 1024 = 19456k
 *
 */

public class GCTest2 {
	private static final int _1MB = 1024*1024;
	
	public static void testPretenureSizeThreshold() {
		byte[] allocation;
		allocation = new byte[4*_1MB];
	}
	
	public static void main(String[] args) {
		testPretenureSizeThreshold();
	}

}
