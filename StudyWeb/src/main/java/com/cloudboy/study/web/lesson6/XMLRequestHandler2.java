package com.cloudboy.study.web.lesson6;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.cloudboy.base.AppBizException;
import com.cloudboy.study.web.model.Bank;
import com.cloudboy.study.web.model.MessageRequest;
import com.cloudboy.study.web.model.MessageResponse;
import com.cloudboy.study.web.model.RequestType;
import com.cloudboy.study.web.model.User;

public class XMLRequestHandler2 {
	
	private static Logger logger = Logger.getLogger(XMLRequestHandler2.class);
	private static long TIMEOUT_MS = 10000;
	private static String CHARSET = "UTF-8";

	public boolean handleRequest(AsyncContext asyncContext) throws Exception {
		MessageRequest messageRequest = null;
		MessageResponse messageResponse = null;
		asyncContext.setTimeout(TIMEOUT_MS);
		ServletRequest servletRequest = asyncContext.getRequest();
		logger.info("servletRequest:" + servletRequest.getClass());
		HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
		HttpSession session = httpServletRequest.getSession(true);
		logger.info("session:" + session.getClass());
		logger.info("sessionId:" + session.getId());
		try {
			messageRequest = parseRequest(asyncContext);
			messageResponse = new MessageResponse();
			String requestType = messageRequest.getRequestType();
			if(RequestType.M001.equals(requestType)) {
				messageResponse = login(messageRequest, session);
			} else if(RequestType.M002.equals(requestType)) {
				messageResponse = M002(messageRequest, session);
			} else {
				throw new AppBizException("Not supported request type");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			messageResponse = getErrorException(e);
		}
		
		outputResponse(asyncContext, messageResponse);
		asyncContext.complete();
		return true;
	}
	
	private MessageResponse login(MessageRequest messageRequest, HttpSession session) throws AppBizException {
		User user = messageRequest.getUser();
		if(user == null || user.getUserName() == null || user.getPassword() == null) {
			throw new AppBizException("User information is not correct.");
		}
		if("cloudboy".equalsIgnoreCase(user.getUserName()) && "123456".equals(user.getPassword())) {
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setResponseCode("00");
			messageResponse.setMessage("Welcome " + user.getUserName());	
			session.setAttribute("user",user);
			return messageResponse;
		} else {
			throw new AppBizException("Wrong user name or password.");
		}
	}
	
	private MessageResponse M002(MessageRequest messageRequest, HttpSession session) throws AppBizException {
		User user = (User)session.getAttribute("user");
		if(user == null) {
			throw new AppBizException("Please login first.");
		}
		Bank bank = messageRequest.getBank();
		if(messageRequest.getBank().getBankId().equals("CCB")) {
			bank.setBankName("中国银行");
		}
		MessageResponse messageResponse = new MessageResponse();
		messageResponse.setResponseCode("00");
		messageResponse.setMessage("Welcome " + user.getUserName());
		messageResponse.setBank(bank);
		return messageResponse;
	}
	
	
	/**
	 * parse the request
	 * 
	 * @param ctx 异步请求上下文
	 * @throws Exception
	 */
	private MessageRequest parseRequest(AsyncContext asyncContext) throws Exception {
		String requestString = IOUtils.toString(asyncContext.getRequest().getInputStream(), CHARSET);
		logger.info("requestString:" + requestString);
		MessageRequest request = MessageRequest.fromXML(requestString);
		return request;
	}
	
	/**
	 * 输出响应报文
	 * 
	 * @param ctx 异步请求上下文
	 * @param response 响应报文
	 * @throws Exception
	 */	
	private void outputResponse(AsyncContext asyncContext, MessageResponse response) throws Exception {
		asyncContext.getResponse().setContentType("text/xml;charset=UTF-8");
		String xmlData = response.toXML();
		asyncContext.getResponse().setContentLength(xmlData.getBytes(CHARSET).length);
		logger.info("Response xml:" + xmlData);
		asyncContext.getResponse().getOutputStream().write(xmlData.getBytes(CHARSET));		
	}

	/**
	 * 生成默认的系统错误
	 * 
	 * @param ex 异常对象
	 * @param message 响应报文对象
	 * @return 默认的系统错误
	 */
	private MessageResponse getErrorException(Exception ex) {
		MessageResponse response = new MessageResponse();
		response.setResponseCode("01");
		response.setMessage(ex.getMessage());		
		return response;
	}
}
