package com.cloudboy.wechat.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WechatServlet extends HttpServlet {

	private static final long serialVersionUID = 3106782286205497126L;

	private static Logger logger = Logger.getLogger(WechatServlet.class);
	
	private DispatchController dispatchController;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String contextPath = request.getContextPath();
		logger.info("contextPath:" + contextPath);
		String uri = request.getRequestURI();
		logger.info("uri:" + uri);
		if(uri.startsWith(contextPath)) {
			uri = uri.substring(contextPath.length(), uri.length());
		}
		logger.info("uri:" + uri);

		String echostr = request.getParameter("echostr");
		String result = "";
		if (echostr != null && echostr.length() > 1) {
			result = echostr;
		} else {
			StringBuffer sb = new StringBuffer();
			InputStream is = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			String xml = sb.toString();
			logger.info("xml:" + xml);
			ReceiveXmlEntity xmlEntity = WechatXmlHandler.parseMessage(xml);
			if (dispatchController == null) {
				ServletContext context = getServletContext();
				WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);
				dispatchController = (DispatchController) wac.getBean("wechat.dispatchController");
				result = dispatchController.dispatch(uri, xmlEntity);
			}
			
			
			result = WechatXmlHandler.formatXmlAnswer(
					xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
		}

		try {
			OutputStream os = response.getOutputStream();
			os.write(result.getBytes("UTF-8"));
			os.flush();
			os.close();
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
