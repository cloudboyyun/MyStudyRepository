package com.cloudboy.study.web.lesson3;
import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

import org.apache.log4j.Logger;

public class MyAsyncListener implements AsyncListener {
	private static Logger logger = Logger.getLogger(MyAsyncListener.class);
	
	@Override
    public void onStartAsync(AsyncEvent event) throws IOException {
    	logger.info("MyAsyncListener is started:" + event.toString());
    }
	
	@Override
    public void onComplete(AsyncEvent event) {
    	logger.info("MyAsyncListener is completed:" + event.toString());
    }
	
	@Override
    public void onTimeout(AsyncEvent event) {
    	logger.info("MyAsyncListener is timeout:" + event.toString());
    }
	
	@Override
    public void onError(AsyncEvent event) {
    	logger.info("MyAsyncListener encouter error:" + event.toString());
    }
}