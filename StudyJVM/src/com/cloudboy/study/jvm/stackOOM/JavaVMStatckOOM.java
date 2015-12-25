package com.cloudboy.study.jvm.stackOOM;

/**
 * VM Args: -Xss2M
 * 说明：栈是线程私有的，Xss设置的是每个线程的栈大小。所以这个值设置的越大，系统内存能支持的最大线程数就越小。因为物理内存的大小是固定的。
 */

public class JavaVMStatckOOM {
	
	private void dontStop() {
		while(true) {
		}
	}
	
	public void stackLeakByThread() {
		while(true) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}

	public static void main(String[] args) {
		JavaVMStatckOOM oom = new JavaVMStatckOOM();
		try {
			oom.stackLeakByThread();
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}

}
