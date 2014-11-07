package com.cloudboy.study.web.lesson4;

import java.text.MessageFormat;

import javax.servlet.AsyncContext;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.cloudboy.base.AppRTException;

public class XMLRequestHandler {
	
	private static Logger logger = Logger.getLogger(XMLRequestHandler.class);
	private static long TIMEOUT_MS = 10000;
	private static String CHARSET = "UTF-8";

	public boolean handleRequest(AsyncContext asyncContext) throws Exception {
		String requestXml = null;
		String responseXml = null;
		asyncContext.setTimeout(TIMEOUT_MS);
		try {
			requestXml = parseRequest(asyncContext);
			logger.info("Request:" + requestXml);
			responseXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
					+ "<responseCode>00</responseCode><responseMsg>Great!!!</responseMsg>";
		} catch (AppRTException e) {
			logger.error(e.getMessage(), e);
			responseXml = getErrorException(e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseXml = getErrorException(e);
		}
		
		outputResponse(asyncContext, responseXml);
		asyncContext.complete();
		return true;
	}
	
	
	
	/**
	 * parse the request
	 * 
	 * @param ctx 异步请求上下文
	 * @throws Exception
	 */
	private String parseRequest(AsyncContext asyncContext) throws Exception {
		String requestString = IOUtils.toString(asyncContext.getRequest().getInputStream(), CHARSET);
		return requestString;
	}
	
	/**
	 * 输出响应报文
	 * 
	 * @param ctx 异步请求上下文
	 * @param xmlData 响应报文
	 * @throws Exception
	 */
	private void outputResponse(AsyncContext asyncContext, String xmlData) throws Exception {
		asyncContext.getResponse().setContentType("text/xml;charset=UTF-8");
		asyncContext.getResponse().setContentLength(xmlData.getBytes(CHARSET).length);
		asyncContext.getResponse().getOutputStream().write(xmlData.getBytes(CHARSET));
		logger.info("Response xml:" + xmlData);
	}

	/**
	 * 生成默认的系统错误
	 * 
	 * @param ex 异常对象
	 * @param message 响应报文对象
	 * @return 默认的系统错误
	 */
	private String getErrorException(Exception ex) {			
		String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<responseCode>{0}</responseCode><responseMsg>{1}</responseMsg>";
		result = MessageFormat.format(result, "11", ex.getMessage());
		
		return result;
	}

}
