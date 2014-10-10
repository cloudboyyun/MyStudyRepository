package com.cloudboy.springRemotingStudy.service.impl;
import org.apache.log4j.Logger;
import com.cloudboy.springRemotingStudy.service.MyService;
public class MyServiceImpl implements MyService {
    private static Logger logger = Logger.getLogger(MyServiceImpl.class);
    
    @Override
    public String sayHello(String yourName) {
        String result = "Hello " + yourName;
        logger.info(result);
        return result; 
    }
}
