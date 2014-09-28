package com.cloudboy.study.thread.lesson1;

/**
 * 测试1， 主线程启动子线程后，主线程运行完毕，之后子线程运行完毕，整个进程结束。
 * 输出如下：
  	main: Starting MessageLoop thread
	main: Finally!
	Thread-0: No 1
	Thread-0: No 2
	Thread-0: No 3
	Thread-0: No 4
 * 
 * @author cloudboy(yun.xia)
 */
public class SimpleThreadTest1 {

    // Display a message, preceded by the name of the current thread
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    private static class MessageLoop implements Runnable {
        public void run() {
            String importantInfo[] = {"No 1", "No 2", "No 3", "No 4"};
            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    // Pause for 4 seconds
                    Thread.sleep(4000);
                    // Print a message
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {

        threadMessage("Starting MessageLoop thread");

        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Finally!");
    }
}

