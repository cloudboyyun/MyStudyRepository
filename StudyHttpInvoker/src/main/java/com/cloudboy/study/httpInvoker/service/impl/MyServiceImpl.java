package com.cloudboy.study.httpInvoker.service.impl;
import org.apache.log4j.Logger;

import com.cloudboy.study.httpInvoker.service.MyService;
public class MyServiceImpl implements MyService {
    private static Logger logger = Logger.getLogger(MyServiceImpl.class);
    
    @Override
    public String sayHello(String yourName) {
        String result = "Hello " + yourName;
        logger.info(result);
        return result; 
    }
}