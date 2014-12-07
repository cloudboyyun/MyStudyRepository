package com.cloudboy.study.web.model;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class ModelTest {
	private static XStream xs = new XStream();

	@Test
	public void testMessageRequest() {
		MessageRequest request = new MessageRequest();
		request.setRequestType(RequestType.M001);
		User user = new User();
		user.setUserName("cloudboy");
		user.setPassword("123456");
		request.setUser(user);
		String xml = request.toXML();
		System.out.println(xml);
		
		MessageRequest request2 = MessageRequest.fromXML(xml);
		System.out.println(xs.toXML(request2));
	}
	
	@Test
	public void testMessageResponse() {
		MessageResponse response = new MessageResponse();
		response.setResponseCode("00");
		response.setMessage("hello");
		Bank bank = new Bank();
		bank.setBankId("CCB");
		bank.setBankName("中国银行");
		response.setBank(bank);;
		String xml = response.toXML();
		System.out.println(xml);
		
		MessageResponse response2 = MessageResponse.fromXML(xml);
		System.out.println(xs.toXML(response2));
		
	}

}
