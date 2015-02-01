package com.cloudboy.study.threadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class StudyThreadPoolExecutor {
	/**
	 * 线程池核心处理器大小
	 */
	private int corePoolSize = 10;

	/**
	 * 线程池最大处理器大小
	 */
	private int maximumPoolSize = 20;

	/**
	 * when the number of threads is greater than the core, this is the maximum
	 * time that excess idle threads will wait for new tasks before terminating.
	 */
	private int keepAliveTime = 65;

	/**
	 * 工作队列的容量
	 */
	private int workQueueNum = 65;

	/**
	 * 线程池
	 */
	private ThreadPoolExecutor threadPoolExecutor;
	
	/**
	 * 被线程池拒绝的Handler
	 */
	private RejectedExecutionHandler rejectedExcutionHandler;
	
	public void init() {
		if (rejectedExcutionHandler == null) {
			threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
					maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
					new ArrayBlockingQueue<Runnable>(workQueueNum),
					new SimpleThreadFactory());
		} else {
			threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
					maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
					new ArrayBlockingQueue<Runnable>(workQueueNum),
					new SimpleThreadFactory(),
					new ThreadPoolExecutor.CallerRunsPolicy());
		}
	}
	
	public void comeIn(String executorName) {
		threadPoolExecutor.execute(new MyExecutor(executorName));
	}
	
	public static void main(String[] args) {
		StudyThreadPoolExecutor pool = new StudyThreadPoolExecutor();
		pool.init();
		int count = 10;
		for(int i = 0; i < count; i++) {
			String executorName = "Executor" + i;
			pool.comeIn(executorName);
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
