package com.cloudboy.study.web.lesson2;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

@WebListener("My Listener2")
public class Listener2 implements ServletRequestListener {
	private static Logger logger = Logger.getLogger(Listener2.class);
	
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		logger.info("Listener2 created");
	}

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		logger.info("Listener2 destroyed");
	}
}