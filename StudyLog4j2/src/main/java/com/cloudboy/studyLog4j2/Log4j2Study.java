package com.cloudboy.studyLog4j2;

import org.apache.log4j.Logger;

public class Log4j2Study {
	private static Logger logger = Logger.getLogger(Log4j2Study.class);
	
	public static void main(String[] args) {
		logger.info("Using log4j2.");
	}

}
