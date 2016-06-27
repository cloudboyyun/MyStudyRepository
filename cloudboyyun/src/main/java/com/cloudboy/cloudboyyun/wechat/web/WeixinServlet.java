package com.cloudboy.cloudboyyun.wechat.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cloudboy.cloudboyyun.wechat.process.BasicProcess;

@WebServlet(name = "WeixinServlet", urlPatterns = { "/WeixinServlet" }, loadOnStartup = 1)
public class WeixinServlet extends HttpServlet {

	private static final long serialVersionUID = 3106782286205497126L;

	private static Logger logger = Logger.getLogger(WeixinServlet.class);

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
			result = new BasicProcess().processWechatMag(xmlEntity);
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
