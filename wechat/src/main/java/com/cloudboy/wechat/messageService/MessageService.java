package com.cloudboy.wechat.messageService;

import com.cloudboy.wechat.web.ReceiveXmlEntity;

public interface MessageService {
	public String processWechatMessage(ReceiveXmlEntity message);
}
