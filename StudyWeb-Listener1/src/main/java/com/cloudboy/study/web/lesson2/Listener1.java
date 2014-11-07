package com.cloudboy.study.web.lesson2;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.apache.log4j.Logger;

public class Listener1 implements ServletRequestListener {
	private static Logger logger = Logger.getLogger(Listener1.class);
	
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		logger.info("FirstListener created");
	}

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		logger.info("FirstListener destroyed");
	}
}