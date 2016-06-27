package com.cloudboy.cloudboyyun.service.tuling;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class TulingService {
	private static Logger logger = Logger.getLogger(TulingService.class);
	
	/**
	 * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
	 * 
	 * @param content
	 * @return
	 */
	public static String getTulingResult(String content) {
		if(content.equals("我是小妞妞")) {
			return "我是老牛牛";
		}
		/** 此处为图灵api接口，参数key需要自己去注册申请，先以11111111代替 */
		String apiUrl = "http://www.tuling123.com/openapi/api?key=2c2c6992755a433dcb2d481606bbe7f0&info=";
		String param = "";
		try {
			param = apiUrl + URLEncoder.encode(content, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}

		/** 发送httpget请求 */
		HttpGet request = new HttpGet(param);
		String result = "";
		try {
			HttpResponse response = HttpClients.createDefault()
					.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		if (null == result) {
			return "对不起，你说的话真是太高深了……";
		}

		try {
			JSONObject json = new JSONObject(result);
			// 以code=100000为例，参考图灵机器人api文档
			if (100000 == json.getInt("code")) {
				result = json.getString("text");
			}
		} catch (JSONException e) {
			logger.error("", e);
		}
		return result;
	}
}
