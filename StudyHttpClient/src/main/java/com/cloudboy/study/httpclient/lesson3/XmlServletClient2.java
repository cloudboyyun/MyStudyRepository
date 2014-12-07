package com.cloudboy.study.httpclient.lesson3;

import java.io.IOException;
import java.net.URI;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.cloudboy.study.web.model.Bank;
import com.cloudboy.study.web.model.MessageRequest;
import com.cloudboy.study.web.model.RequestType;
import com.cloudboy.study.web.model.User;
import com.cloudboy.util.httpClient.HttpClientFactory;
import com.cloudboy.util.lang.StringUtils;

public class XmlServletClient2 {
	
	private static Logger logger = Logger.getLogger(XmlServletClient2.class);
	private URI uri = null;
//	String url = "http://www.cloudboy.org:8093/studyWeb/XMLServlet2";
	private String url = "https://www.cloudboy.org:8002/studyWeb/XMLServlet2";
		

	public void testXMLServlet() {
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			uri = uriBuilder.build();
			CloseableHttpClient httpclient = HttpClientFactory.getHttpClient();
			String cookieString = M001(httpclient);
			String cookie = getJSESSIONID(cookieString);
			M002(httpclient);
			CloseableHttpClient httpclient1 = HttpClientFactory.getHttpClient();
			M002(httpclient1, cookie);
			httpclient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String M001(CloseableHttpClient httpclient) throws ClientProtocolException, IOException {
		HttpPost httpRequest = new HttpPost(uri);
		CloseableHttpResponse response = null;
		// 第一个请求, 登录
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setRequestType(RequestType.M001);
		User user = new User();
		user.setUserName("cloudboy");
		user.setPassword("123456");
		messageRequest.setUser(user);

		String requestXML = messageRequest.toXML();
		StringEntity reqEntity = new StringEntity(requestXML, Consts.UTF_8);
		reqEntity.setContentType("text/xml;charset=UTF-8");
		reqEntity.setChunked(true);
		httpRequest.setEntity(reqEntity);

		response = httpclient.execute(httpRequest);
		
		Header[] headers = response.getHeaders("Set-Cookie");
		String cookieString = null;
		if(headers != null && headers.length > 0) {
			cookieString = headers[0].getValue();
		}
		logger.info("cookie:" + cookieString);
		HttpEntity entity = response.getEntity();
		logger.info(entity.getContentType());
		String responseXML = EntityUtils.toString(entity);
		logger.info("messageResponse:" + responseXML);
//		MessageResponse messageResponse = MessageResponse.fromXML(responseXML);
		response.close();
		return cookieString;
	}
	
	// 第二个请求, 获取银行名称
	private void M002(CloseableHttpClient httpclient) throws ClientProtocolException, IOException {
		HttpPost httpRequest = new HttpPost(uri);
		CloseableHttpResponse response = null;
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setRequestType(RequestType.M002);
		Bank bank = new Bank();
		bank.setBankId("CCB");
		messageRequest.setBank(bank);

		String requestXML = messageRequest.toXML();
		StringEntity reqEntity = new StringEntity(requestXML, Consts.UTF_8);
		reqEntity.setContentType("text/xml;charset=UTF-8");
		reqEntity.setChunked(true);
		httpRequest.setEntity(reqEntity);

		response = httpclient.execute(httpRequest);
		Header[] headers = httpRequest.getAllHeaders();
		for (Header header : headers) {
			logger.info("header name(" + header.getName() + ") value("
					+ header.getValue() + ")");
		}
		HttpEntity entity2 = response.getEntity();
		logger.info(entity2.getContentType());
		String responseXML2 = EntityUtils.toString(entity2);
		logger.info("messageResponse:" + responseXML2);
//		MessageResponse messageResponse2 = MessageResponse.fromXML(responseXML2);		
		response.close();
	}
	
	// 第二个请求, 获取银行名称
	private void M002(CloseableHttpClient httpclient, String cookie)
			throws ClientProtocolException, IOException {
		HttpPost httpRequest = new HttpPost(uri);
		CloseableHttpResponse response = null;
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setRequestType(RequestType.M002);
		Bank bank = new Bank();
		bank.setBankId("CCB");
		messageRequest.setBank(bank);

		String requestXML = messageRequest.toXML();
		StringEntity reqEntity = new StringEntity(requestXML, Consts.UTF_8);
		reqEntity.setContentType("text/xml;charset=UTF-8");
		reqEntity.setChunked(true);
		httpRequest.setEntity(reqEntity);
		if (cookie != null) {
			httpRequest.setHeader("cookie", cookie);
			httpRequest.setHeader("cookie2", "$Version=1");
		}

		response = httpclient.execute(httpRequest);
		Header[] headers = httpRequest.getAllHeaders();
		for (Header header : headers) {
			logger.info("header name(" + header.getName() + ") value("
					+ header.getValue() + ")");
		}
		HttpEntity entity2 = response.getEntity();
		logger.info(entity2.getContentType());
		String responseXML2 = EntityUtils.toString(entity2);
		logger.info("messageResponse:" + responseXML2);
		// MessageResponse messageResponse2 =
		// MessageResponse.fromXML(responseXML2);
		response.close();
	}
	
	public static void main(String[] args) {
		XmlServletClient2 client = new XmlServletClient2();
		client.testXMLServlet();
	}
	
	private String getJSESSIONID(String cookieString) {
		String[] items = StringUtils.split(cookieString, ";");
		for(String item : items) {
			if(item.contains("JSESSIONID")) {
				return item;
			}
		}
		return null;
	}
}
