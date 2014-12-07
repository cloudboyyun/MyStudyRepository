package com.cloudboy.study.httpclient.lesson3;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloudboy.study.web.model.Bank;
import com.cloudboy.study.web.model.MessageRequest;
import com.cloudboy.study.web.model.MessageResponse;
import com.cloudboy.study.web.model.RequestType;
import com.cloudboy.study.web.model.User;
import com.cloudboy.util.httpClient.HttpClientService;

public class XmlServletClient3 {
	
	private static Logger logger = Logger.getLogger(XmlServletClient3.class);
	private HttpClientService httpClientService = null;
//	private final static String url = "http://www.cloudboy.org:8093/studyWeb/XMLServlet2";
	private final static String url = "https://www.cloudboy.org:8002/studyWeb/XMLServlet2";

	public void testXMLServlet() {
		M001();
		M002();
		for(int i = 0; i<10; i++) {
			logger.info(i);
			sleep(10);
			if(!M002()) {
				break;
			}
		}
	}
	
	private void sleep(int seconds) {
		logger.info("休息:" + seconds + "秒");
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void M001() {
		// 第一个请求, 登录
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setRequestType(RequestType.M001);
		User user = new User();
		user.setUserName("cloudboy");
		user.setPassword("123456");
		messageRequest.setUser(user);

		String requestXML = messageRequest.toXML();
		String responseXML = httpClientService.postXML(url, requestXML);
		
		logger.info("messageResponse:" + responseXML);
	}
	
	// 第二个请求, 获取银行名称
	private boolean M002() {
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setRequestType(RequestType.M002);
		Bank bank = new Bank();
		bank.setBankId("CCB");
		messageRequest.setBank(bank);
		String requestXML = messageRequest.toXML();
		String responseXML = httpClientService.postXML(url, requestXML);
		MessageResponse messageResponse = MessageResponse.fromXML(responseXML);
		if("00".equals(messageResponse.getResponseCode())) {
			logger.info(messageResponse.getBank().getBankName());
			return true;
		} else {
			logger.info(messageResponse.getMessage());
			return false;
		}
	}
	
	@Autowired
	public void setHttpClientService(HttpClientService httpClientService) {
		this.httpClientService = httpClientService;
	}
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
		XmlServletClient3 client = (XmlServletClient3)context.getBean("shc.XmlServletClient3");
		client.testXMLServlet();
		context.close();
	}	
}
