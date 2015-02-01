package com.cloudboy.study.threadPoolExecutor;

import org.apache.log4j.Logger;

public class MyExecutor implements Runnable {
	
	private static Logger logger = Logger.getLogger(MyExecutor.class);
	private String executorName = null;
	
	public MyExecutor(String executorName) {
		this.executorName = executorName;
		logger.info("MyExecutor(" + executorName + ")" + " is created.");
	}
	
	@Override
	public void run() {
		logger.info("MyExecutor(" + executorName + ")" + " starts.");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("MyExecutor(" + executorName + ")" + " is completed.");
	}
}