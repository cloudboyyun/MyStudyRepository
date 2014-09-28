package com.cloudboy.study.thread.lesson1;

/**
 * 测试2， 主线程启动子线程后，中断子线程的运行
 * 输出如下：
main: Starting MessageLoop thread
main: Waiting for MessageLoop thread to finish
main: Still waiting...
main: Still waiting...
main: Still waiting...
main: Still waiting...
Thread-0: No 1
Parent(main) state:TIMED_WAITING
main: Still waiting...
main: Still waiting...
main: Still waiting...
main: Still waiting...
Thread-0: No 2
Parent(main) state:TIMED_WAITING
main: Still waiting...
main: Still waiting...
main: Tired of waiting!
Thread-0: I wasn't done!
main: Finally!
 * 
 * @author cloudboy(yun.xia)
 */
public class SimpleThreadTest3 {

    // Display a message, preceded by the name of the current thread
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    private static class MessageLoop implements Runnable {
    	Thread parent = null;
    	
    	public MessageLoop(Thread parent) {
    		this.parent = parent;
    	}
    	
        public void run() {
            String importantInfo[] = {"No 1", "No 2", "No 3", "No 4"};
            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    // Pause for 4 seconds
                    Thread.sleep(4000);
                    // Print a message
                    threadMessage(importantInfo[i]);
                    System.out.println("Parent(" + parent.getName() +") state:" + parent.getState());
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {

        threadMessage("Starting MessageLoop thread");
        // Delay, in milliseconds before we interrupt MessageLoop thread(10 seconds)
        long patience = 1000 * 10;
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop(Thread.currentThread()));
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second for MessageLoop thread to finish.
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience)
                    && t.isAlive()) {
                  threadMessage("Tired of waiting!");
                  t.interrupt();
                  // Shouldn't be long now wait indefinitely
                  t.join();
              }
        }
        threadMessage("Finally!");
    }
}

