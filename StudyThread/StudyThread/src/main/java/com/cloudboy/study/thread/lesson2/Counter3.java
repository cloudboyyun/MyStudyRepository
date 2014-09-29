package com.cloudboy.study.thread.lesson2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用AtomicInteger类，解决这个线程冲突问题。
 * 关键点就是count.incrementAndGet()方法是原子的。
 * 
 * @author cloudboy(yun.xia)
 *
 */
public class Counter3 {

	public static AtomicInteger count = new AtomicInteger(0);

	public static void inc() {

		// 这里延迟1毫秒，使得结果明显
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		}

		count.incrementAndGet();
	}

	public static void main(String[] args) {
		
		// 同时启动1000个线程，去进行i++计算，看看实际结果
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Counter3.inc();
				}
			}).start();
		}

		// 等待2秒，以便上述1000个线程结束
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 这里每次运行的值都有可能不同,可能为1000
		System.out.println("运行结果:Counter.count=" + Counter3.count);
	}
}
