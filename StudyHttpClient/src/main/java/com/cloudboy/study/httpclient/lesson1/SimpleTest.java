package com.cloudboy.study.httpclient.lesson1;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
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
       
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
	        CloseableHttpResponse response = null;
			URI uri = uriBuilder.build();
			HttpPost httpPost = new HttpPost(uri);
			logger.info("URI:" + httpPost.getURI());
			
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("name", "cloudboy"));
			UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
			httpPost.setEntity(requestEntity);

			response = httpclient.execute(httpPost);
			
			logger.info("response.getProtocolVersion():" + response.getProtocolVersion());
			logger.info("response.getStatusLine():" + response.getStatusLine());
			Header[] headers = response.getAllHeaders();
			for(Header header : headers) {
				logger.info("header name(" + header.getName() + ") value(" + header.getValue() + ")");
			}
			HttpEntity responseEntity = response.getEntity();
			String result = EntityUtils.toString(responseEntity);
			logger.info(result);
			response.close();
			httpclient.close();
		} catch (Exception e) {
			logger.error(null, e);
		}
	}	
	
	public static void main(String[] args) {
		SimpleTest test = new SimpleTest();
		test.testHelloServlet();		
	}
}
