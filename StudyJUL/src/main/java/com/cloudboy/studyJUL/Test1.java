package com.cloudboy.studyJUL;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Test1 {
	private static Logger logger = Logger.getLogger(Test1.class.getName());
	
	public void test() {
		logger.log(Level.INFO, "Hello");
		try {
			doSomeThing();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "", e);
		}
	}
	
	public void doSomeThing() throws Exception {
		throw new Exception("A test Exception.");
	}
	
	public static void main(String[] args) {
		Test1 obj = new Test1();
		obj.test();
	}
}
