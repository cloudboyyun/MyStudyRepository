package com.cloudboy.study.httpclient.lesson3;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.cloudboy.util.log.MyLogger;

public class HttpsTest {
	private static MyLogger logger = MyLogger.getLogger(HttpsTest.class);
	
	public void testHelloServlet() {
		System.setProperty("jsse.enableSNIExtension", "false");
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme("https");
		uriBuilder.setHost("www.cloudboy.org");
		uriBuilder.setPort(8002);
        uriBuilder.setPath("/studyWeb/HelloServlet");
        
		try {
			HttpClient httpclient = HttpClientFactory.getHttpClient();
	        HttpResponse response = null;
			URI uri = uriBuilder.build();
			HttpPost httpPost = new HttpPost(uri);
			logger.info("URI:", httpPost.getURI());
			
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("name", "cloudboy"));
			UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
			httpPost.setEntity(requestEntity);

			response = httpclient.execute(httpPost);
			
			logger.info("response.getProtocolVersion():", response.getProtocolVersion());
			logger.info("response.getStatusLine():", response.getStatusLine());
			Header[] headers = response.getAllHeaders();
			for(Header header : headers) {
				logger.info("header name(", header.getName(), ") value(", header.getValue(), ")");
			}
			HttpEntity responseEntity = response.getEntity();
			String result = EntityUtils.toString(responseEntity);
			logger.info(result);
		} catch (Exception e) {
			logger.error(null, e);
		}
	}	
	
	public static void main(String[] args) {
		HttpsTest test = new HttpsTest();
		test.testHelloServlet();		
	}
}
