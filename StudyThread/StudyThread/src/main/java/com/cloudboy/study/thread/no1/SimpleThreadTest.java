package com.cloudboy.study.thread.no1;

public class SimpleThreadTest {
	
	public void test() {
		MyThread thread = new MyThread();
		System.out.println("Step 1. Thread.state:" + thread.getState());
		thread.start();
		while(true) {
			System.out.println("Thread.state:" + thread.getState());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class MyThread extends Thread {
		
		@Override
	    public void run() {
			System.out.println("I'm running.");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("I'm out.");
		}
	}
	
	public static void main(String[] args) {
		
		SimpleThreadTest test = new SimpleThreadTest();
		test.test();
	}

}
