package com.cloudboy.study.springProperty;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloudboy.study.springProperty.lesson1.MyBean;
import com.cloudboy.study.springProperty.lesson2.PropertyPlaceholderConfigurer;

public class PropertyPlaceHolderTest {

	@Test
	public void lesson1Test() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("propertyTestContext.xml");
		MyBean bean = (MyBean)ctx.getBean("myBean");
		System.out.println("Lesson1:" + bean.getName());
		ctx.close();
	}
	
	@Test
	public void lesson2Test() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("propertyTestContext.xml");
		PropertyPlaceholderConfigurer config = (PropertyPlaceholderConfigurer)ctx.getBean("extended_propertyConfigurer");
		System.out.println("Lesson2:" + config.getProperty("cloudboy.name"));
		ctx.close();
	}
}
