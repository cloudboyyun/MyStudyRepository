package com.cloudboy.study.httpclient.lesson2;

import java.net.URI;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class XmlServletClient {
	
	private static Logger logger = Logger.getLogger(XmlServletClient.class);

	public void testXMLServlet() {
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme("http");
		uriBuilder.setHost("192.168.1.106");
		uriBuilder.setPort(8093);
        uriBuilder.setPath("/studyWeb/XMLServlet");
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
       
		try {
			URI uri = uriBuilder.build();
			HttpPost httpRequest = new HttpPost(uri);
			logger.info(httpRequest.getURI());
			String requestXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
					+ "<requestType>M001</requestType><userName>cloudboy</userName>";
			StringEntity reqEntity = new StringEntity(requestXML, Consts.UTF_8);
			reqEntity.setContentType("text/xml;charset=UTF-8");
	        reqEntity.setChunked(true);
	        httpRequest.setEntity(reqEntity);
	        
			response = httpclient.execute(httpRequest);	        
			logger.info(response.getProtocolVersion());
			logger.info(response.getStatusLine().toString());
			Header[] headers = response.getAllHeaders();
			for(Header header : headers) {
				logger.info("header name(" + header.getName() + ") value(" + header.getValue() + ")");
			}
			HttpEntity entity = response.getEntity();
			logger.info(entity.getContentType());
			String result = EntityUtils.toString(entity);
			logger.info(result);
			response.close();
			httpclient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		XmlServletClient client = new XmlServletClient();
		client.testXMLServlet();
	}

}
