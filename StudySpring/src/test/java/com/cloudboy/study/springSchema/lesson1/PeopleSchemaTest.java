package com.cloudboy.study.springSchema.lesson1;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PeopleSchemaTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("clientContext.xml");  
		PeopleBean p = (PeopleBean)ctx.getBean("cutesource");
		System.out.println(p.getId());  
		System.out.println(p.getName());  
		System.out.println(p.getAge());
		ctx.close();
	}
}
