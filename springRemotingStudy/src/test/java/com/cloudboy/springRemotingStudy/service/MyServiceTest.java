package com.cloudboy.springRemotingStudy.service;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MyServiceTest {
    
	@Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void testSayHello() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("clientContext.xml");
        MyService service = (MyService)context.getBean("myServiceClient");
        String result = service.sayHello("cloudboy");
        System.out.println(result);
        context.close();
    }
}