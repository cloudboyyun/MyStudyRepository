package com.cloudboy.study.thread.lesson2;

/**
 * 1000个线程，每个线程执行给count变量加1的操作，期望的结果是1000.
 * 但实际的结果是随机的，例如我运行了3次，结果分别是：
 * 运行结果:Counter.count=995
 * 运行结果:Counter.count=986
 * 运行结果:Counter.count=999
 * 
 * 这是因为 count++操作实际被编译器分解为至少3个动作：(1) 取得count的值 （2） 计算加1 （3）将结果放入count变量。
 * 当多个线程同时进行这些操作时，各个操作犬牙交错，结果就无法预期了。 
 * 注意，使用volatile来生命count变量，无法解决这个问题。volatile关键字只是解决线程的可见性问题，不能解决线程冲突问题。
 * 
 * @author cloudboy(yun.xia)
 *
 */
public class Counter1 {

	public static int count = 0;

	public static void inc() {

		// 这里延迟1毫秒，使得结果明显
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		}

		count++;
	}

	public static void main(String[] args) {
		
		// 同时启动1000个线程，去进行i++计算，看看实际结果
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Counter1.inc();
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
		System.out.println("运行结果:Counter.count=" + Counter1.count);
	}
}
