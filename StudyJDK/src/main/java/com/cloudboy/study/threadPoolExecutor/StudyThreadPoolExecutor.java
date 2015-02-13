package com.cloudboy.study.threadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class StudyThreadPoolExecutor {
	
	private static Logger logger = Logger.getLogger(StudyThreadPoolExecutor.class);
	
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
	 * 如果运行的线程少于 corePoolSize，则 Executor 始终首选添加新的线程，而不进行排队。（什么意思？如果当前运行的线程小于corePoolSize，则任务根本不会存放，添加到queue中，而是直接抄家伙（thread）开始运行）
	 * 如果运行的线程等于或多于 corePoolSize，则 Executor 始终首选将请求加入工作队列，而不添加新的线程。
	 * 如果无法将请求加入工作队列，则创建新的线程，除非创建此线程超出 maximumPoolSize，在这种情况下，任务将被拒绝。
	 */
	private int workQueueNum = 1;

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
		try {
			threadPoolExecutor.execute(new MyExecutor(executorName));
		} catch(RejectedExecutionException e) {
			logger.error(e);
		}
	}
	
	public static void main(String[] args) {
		StudyThreadPoolExecutor pool = new StudyThreadPoolExecutor();
		pool.init();
		int count = 30;
		for(int i = 0; i < count; i++) {
			String executorName = "Executor" + i;
			pool.comeIn(executorName);
		}
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
