package study.packetFormat.model;

import study.packetFormat.model.pb.MessagePB;
import study.packetFormat.model.xml.DeviceInfo;
import study.packetFormat.model.xml.Header;
import study.packetFormat.model.xml.Message;
import study.packetFormat.model.xml.MsgContent;
import study.packetFormat.model.xml.Reimbersement;
import study.packetFormat.model.xml.ReimbersementList;

public class DataFactory {
	
	public final static String password = "18616227982";
	
	public static Message getMessage() {
		DeviceInfo dev = new DeviceInfo();
		dev.setImei("864449001051907");
		dev.setImsi("460005811733151");
		dev.setMac("F8:DB:7F:23:31:6A");
		
		Header header = new Header();
		header.setBizType("M119");
		header.setVersion("4.0");
		header.setAppVersion("IOS_MOBILE_PAYMENT_PLUGIN_20140512");
		header.setReqTime("20150318194800");
		header.setUserName("13671709371");
		header.setDeviceInfo(dev);
		
		MsgContent msgContent = new MsgContent();
		msgContent.setPassword(password);
		msgContent.setReimbersementList(new ReimbersementList());
		Reimbersement reimbersement = null;
		for(int i=0; i<3; i++) {
			reimbersement = new Reimbersement();
			reimbersement.setCreditBankId("BANK" + i);
			reimbersement.setCreditBankName("银行" + i);
			msgContent.getReimbersementList().getReimbersement().add(reimbersement);
		}
		
		Message message = new Message();
		message = new Message();
		message.setSign("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCn1m3KVMDzECIin/vJ2m5+oc"
				+ "O03QKymY7f50y9UZgkrQC+EO5vPAeQ/GtQugxTRHnobDek5th25tQkiKZAzmn4taRgS8mxISyKUYza8Z3KGCsj93Lqp"
				+ "N0s9mU71+QqdymK5atSLd39wb2yW4JXhrqqVUGVOI//UCu4QM8bR3QeMwIDAQAB");
		message.setHeader(header);
		message.setMsgContent(msgContent);
		return message;
	}
	
	public static MessagePB.Message getMessagePB() {
		
		MessagePB.Message.DeviceInfo.Builder deviceInfoBuilder = MessagePB.Message.DeviceInfo.newBuilder();
		deviceInfoBuilder.setImei("864449001051907");
		deviceInfoBuilder.setImsi("460005811733151");
		deviceInfoBuilder.setMac("F8:DB:7F:23:31:6A");
		
		MessagePB.Message.Header.Builder headerBuilder = MessagePB.Message.Header.newBuilder();
		headerBuilder.setBizType("M119");
		headerBuilder.setVersion("4.0");
		headerBuilder.setAppVersion("IOS_MOBILE_PAYMENT_PLUGIN_20140512");
		headerBuilder.setReqTime("20150318194800");
		headerBuilder.setUserName("13671709371");
		headerBuilder.setDeviceInfo(deviceInfoBuilder);
		
		MessagePB.Message.MsgContent.Builder msgContentBuilder = MessagePB.Message.MsgContent.newBuilder();
		msgContentBuilder.setPassword(password);
		MessagePB.Message.Reimbersement.Builder reimbersementBuilder = null;
		for(int i=0; i<3; i++) {
			reimbersementBuilder = MessagePB.Message.Reimbersement.newBuilder();
			reimbersementBuilder.setCreditBankId("BANK" + i);
			reimbersementBuilder.setCreditBankName("银行" + i);
			MessagePB.Message.Reimbersement reimbersement = reimbersementBuilder.build();
			msgContentBuilder.addReimbersementList(reimbersement);
		}
		
		MessagePB.Message.Builder messageBuilder = MessagePB.Message.newBuilder();
		messageBuilder.setSign("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCn1m3KVMDzECIin/vJ2m5+oc"
				+ "O03QKymY7f50y9UZgkrQC+EO5vPAeQ/GtQugxTRHnobDek5th25tQkiKZAzmn4taRgS8mxISyKUYza8Z3KGCsj93Lqp"
				+ "N0s9mU71+QqdymK5atSLd39wb2yW4JXhrqqVUGVOI//UCu4QM8bR3QeMwIDAQAB");
		messageBuilder.setHeader(headerBuilder);
		messageBuilder.setMsgContent(msgContentBuilder);
		
		MessagePB.Message message = messageBuilder.build();
		return message;
	}
}
