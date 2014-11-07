package com.cloudboy.study.httpclient.lesson1;

import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class SimpleTest {
	private static Logger logger = Logger.getLogger(SimpleTest.class);
	
	public void testHelloServlet() {
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme("http");
		uriBuilder.setHost("192.168.1.106");
		uriBuilder.setPort(8093);
        uriBuilder.setPath("/studyWeb/HelloServlet");
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
       
		try {
			URI uri = uriBuilder.build();
			HttpGet httpget = new HttpGet(uri);
			logger.info(httpget.getURI());
			response = httpclient.execute(httpget);
			
			logger.info(response.getProtocolVersion());
			logger.info(response.getStatusLine().toString());
			Header[] headers = response.getAllHeaders();
			for(Header header : headers) {
				logger.info("header name(" + header.getName() + ") value(" + header.getValue() + ")");
			}
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			logger.info(result);
			response.close();
			httpclient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
	
	public static void main(String[] args) {
		SimpleTest test = new SimpleTest();
		test.testHelloServlet();		
	}
}
