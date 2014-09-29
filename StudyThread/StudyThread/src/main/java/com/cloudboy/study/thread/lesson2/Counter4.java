package com.cloudboy.study.thread.lesson2;

/**
 * 本例试验了volatile
 * 当不使用volatile时，本例运行正常（无输出）。对i和j的操作，由于使用了synchronized关键字，是原子操作，所以i==j, 不会有输出。
 * 当使用volatile关键字时，就会出现问题了。例如我得到的结果是：
 * i=25359 j=25360
   i=40158 j=40157   
 * @author cloudboy(yun.xia)
 *
 */
class Counter4 {

//	static int i = 0, j = 0;
	static volatile int i = 0, j = 0;

	static synchronized void one() {
		i++;
		j++;
	}

	static void two() {
		if (i != j) {
			System.out.println("i=" + i + " j=" + j);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 50000; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					one();
				}
			});

			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					two();
				}
			});

			t1.start();
			t2.start();
		}
	}
}
