package com.cloudboy.study.web.lesson4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudboy.base.AppRTException;

@WebServlet(name = "XMLServlet", urlPatterns = { "/XMLServlet" }, asyncSupported = true)
public class XMLServlet extends HttpServlet {
	
	private static final long serialVersionUID = -7408673724234057039L;

	private XMLRequestHandler xmlRequestHandler;

	/**
	 * 线程池核心处理器大小
	 */
	private int corePoolSize = 100;

	/**
	 * 线程池最大处理器大小
	 */
	private int maximumPoolSize = 200;

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
	 * web请求线程池
	 */
	private ThreadPoolExecutor threadPoolExecutor;

	/**
	 * 被线程池拒绝的Handler
	 */
	private RejectedExecutionHandler rejectedExcutionHandler;

	@Override
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

	private class SimpleThreadFactory implements ThreadFactory {
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		AsyncContext ctx = req.startAsync();
		threadPoolExecutor.execute(new Executor(ctx));
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		AsyncContext ctx = req.startAsync();
		threadPoolExecutor.execute(new Executor(ctx));
	}

	public class Executor implements Runnable {
		
		private AsyncContext asyncContext = null;

		public Executor(AsyncContext asyncContext) {
			this.asyncContext = asyncContext;
			asyncContext.setTimeout(60000);
		}

		public void run() {
			if (xmlRequestHandler == null) {
				xmlRequestHandler = new XMLRequestHandler();
			}

			try {
				xmlRequestHandler.handleRequest(asyncContext);
			} catch (Exception e) {
				if (asyncContext != null) {
					((HttpServletResponse) (asyncContext.getResponse())).setStatus(HttpServletResponse.SC_NOT_FOUND);
					asyncContext.complete();
				}
				throw new AppRTException(e);
			}
		}
	}

}