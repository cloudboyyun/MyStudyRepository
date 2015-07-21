package com.cloudboy.exception;

import org.apache.log4j.Logger;

public class TestMyException {
	private static Logger logger = Logger.getLogger(TestMyException.class);
	
	public static void test() throws MyException {
		MyException e = new MyException("123", "出错啦");
		throw e;
	}
	
	public static void main(String[] args) {
		try {
			test();
		} catch (MyException e) {
			logger.error("", e);
		}
	}
}
