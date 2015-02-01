package com.cloudboy.study.threadPoolExecutor;

public class MyExecutor implements Runnable {

	private String executorName = null;
	
	public MyExecutor(String executorName) {
		this.executorName = executorName;
		System.out.println("MyExecutor(" + executorName + ")" + " is created.");
	}
	
	@Override
	public void run() {
		System.out.println("MyExecutor(" + executorName + ")" + " starts.");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("MyExecutor(" + executorName + ")" + " is completed.");
	}
}