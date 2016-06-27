package com.cloudboy.wechat.web;

import java.util.HashMap;
import java.util.Map;

import com.cloudboy.wechat.messageService.MessageService;
import com.cloudboy.wechat.messageService.MessageServiceBasicImpl;

public class DispatchController {

	public Map<String, MessageService> controllers = new HashMap<String, MessageService>();

	public String dispatch(String uri, ReceiveXmlEntity message) {
		MessageService messageService = controllers.get(uri);
		if (messageService == null) {
			messageService = new MessageServiceBasicImpl();
		}
		
		return messageService.processWechatMessage(message);
	}

	public void setControllers(Map<String, MessageService> controllers) {
		this.controllers = controllers;
	}

}
