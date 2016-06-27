package com.cloudboy.cloudboyyun.wechat.process;

import com.cloudboy.cloudboyyun.service.tuling.TulingService;
import com.cloudboy.cloudboyyun.wechat.web.ReceiveXmlEntity;

public class BasicProcess {
	public String processWechatMag(ReceiveXmlEntity message) {
		String result = "";
		if ("text".endsWith(message.getMsgType())) {
			result = TulingService.getTulingResult(message
					.getContent());
		}

		return result;
	}
}