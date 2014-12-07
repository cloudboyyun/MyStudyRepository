package com.cloudboy.study.httpclient.lesson3;

import java.io.InputStream;
import java.security.KeyStore;

import org.apache.log4j.Logger;

import com.cloudboy.study.web.model.Bank;
import com.cloudboy.study.web.model.MessageRequest;
import com.cloudboy.study.web.model.MessageResponse;
import com.cloudboy.study.web.model.RequestType;
import com.cloudboy.study.web.model.User;
import com.cloudboy.util.httpClient.HttpClientServiceImpl;
import com.cloudboy.util.security.KeyFileUtil;

public class XmlServletClient3 {
	
	private static Logger logger = Logger.getLogger(XmlServletClient3.class);
	private HttpClientServiceImpl httpClientService = null;
	private final static String url = "https://www.cloudboy.org:8002/studyWeb/XMLServlet2";
	
	public XmlServletClient3() {
		InputStream certFile = XmlServletClient3.class.getResourceAsStream("/ca.crt");
		KeyStore trustKeyStore = null;
		try {
			trustKeyStore = KeyFileUtil.getKeyStoreByCrtFile(certFile);
		} catch (Exception e) {
			throw new RuntimeException("Read the cert file failed.");
		}
		httpClientService = new HttpClientServiceImpl(trustKeyStore);
		httpClientService.init();
	}

	public void testXMLServlet() {
		M001();
		M002();
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
	
	public static void main(String[] args) {
		XmlServletClient3 client = new XmlServletClient3();
		client.testXMLServlet();
	}	
}
