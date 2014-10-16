package com.cloudboy.study.springRemoting.service;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloudboy.study.springRemoting.service.MyService;
public class MyServiceTest {
    
	@Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void testSayHello() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("clientContext.xml");
        MyService service = (MyService)context.getBean("myServiceClient");
        String result = service.sayHello("cloudboy1");
        System.out.println(result);
        context.close();
    }
}