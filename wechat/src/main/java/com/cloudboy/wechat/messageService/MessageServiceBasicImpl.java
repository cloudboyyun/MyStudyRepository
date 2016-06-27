package com.cloudboy.wechat.messageService;

import com.cloudboy.service.tuling.TulingService;
import com.cloudboy.wechat.web.ReceiveXmlEntity;

public class MessageServiceBasicImpl implements MessageService {
	
	@Override
	public String processWechatMessage(ReceiveXmlEntity message) {
		String result = "";
		if ("text".endsWith(message.getMsgType())) {
			result = TulingService.getTulingResult(message
					.getContent());
		}

		return result;
	}
}