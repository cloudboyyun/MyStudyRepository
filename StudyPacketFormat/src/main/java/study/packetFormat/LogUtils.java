package study.packetFormat;

import study.packetFormat.model.pb.MessagePB.Message;

import com.google.protobuf.MbpTextFormat;


public class LogUtils {
	public static String getLogMessage(Message message) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(MbpTextFormat.printToUnicodeString(message));
		String result = buffer.toString();
		
//		message.getAllFields().entrySet();
//		for (Map.Entry<FieldDescriptor, Object> field : message.getAllFields().entrySet()) {
//			field.getValue().toString();
//		        printField(field.getKey(), field.getValue(), generator);
//		}
		return result;
	}
}
