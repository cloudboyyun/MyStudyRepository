package study.packetFormat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Before;
import org.junit.Test;

import study.packetFormat.model.DataFactory;
import study.packetFormat.model.XmlJaxbUtil;
import study.packetFormat.model.pb.MessagePB;
import study.packetFormat.model.xml.Message;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.google.protobuf.InvalidProtocolBufferException;
import com.thoughtworks.xstream.XStream;

public class PacketFormatSizeUT {
	
	private static Message message;
	private static MessagePB.Message messagePB;
	private static ObjectMapper mapper = null;
	private static XStream xs = new XStream();

	@Before
	public void setUp() throws Exception {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		
		message = DataFactory.getMessage();
		messagePB = DataFactory.getMessagePB();
	}
	
	@Test
	public void testPB() throws InvalidProtocolBufferException {
		byte[] pbResult = messagePB.toByteArray();
		MessagePB.Message pbMessage = MessagePB.Message.parseFrom(pbResult);
		System.out.println(xs.toXML(pbMessage));
		System.out.println(pbMessage.getMsgContent().getReimbersementListCount());
	}
	
	@Test
	public void compareCompressRatio() throws JsonGenerationException, JsonMappingException, IOException {
		String xmlResult = toXML(message);
		write2File(xmlResult.getBytes(), new File("d:\\temp\\compareXML.dat"));
		int xmlSize = xmlResult.getBytes().length;
		
		String jsonResult = toJson(message);
		write2File(jsonResult.getBytes(), new File("d:\\temp\\compareJson.dat"));
		int jsonSize = jsonResult.getBytes().length;
		
		byte[] hessianBytes = toHessian(message);
		write2File(hessianBytes, new File("d:\\temp\\compareHessian.dat"));
		int hessianSize = hessianBytes.length;
		
		byte[] pbResult = messagePB.toByteArray();
		write2File(pbResult, new File("d:\\temp\\comparePB.dat"));
		int pbSize = pbResult.length;
		
		System.out.println("xml size(bytes):" + xmlSize);
		System.out.println("json size(bytes):" + jsonSize);
		System.out.println("hessianSize size(bytes):" + hessianSize);
		System.out.println("pbSize size(bytes):" + pbSize);
		System.out.println("json size/xml size = " + (Math.round(Double.valueOf(jsonSize)/xmlSize * 100)) + "%");
		System.out.println("hessian size/xml size = " + (Math.round(Double.valueOf(hessianSize)/xmlSize * 100)) + "%");
		System.out.println("pbSize size/xml size = " + (Math.round(Double.valueOf(pbSize)/xmlSize * 100)) + "%");
		System.out.println(xmlResult);
		System.out.println(jsonResult);
	}
	
	private String toXML(Message message) {
		String xmlResult = XmlJaxbUtil.marshal(message);
		return xmlResult;
	}
	
	private String toJson(Object message) throws JsonGenerationException, JsonMappingException, IOException {
		StringWriter stringWriter = new StringWriter();
		mapper.writeValue(stringWriter, message);
		String jsonResult = stringWriter.toString();
		return jsonResult;
	}
	
	private byte[] toHessian(Object message) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Hessian2Output out = new Hessian2Output(bos);
		out.writeObject(message);
		out.flush();
		return bos.toByteArray();
	}
	
	protected Object fromHessian(byte[] hessianBytes) throws IOException {
		ByteArrayInputStream bin = new ByteArrayInputStream(hessianBytes);
		Hessian2Input in = new Hessian2Input(bin);
		Object result = in.readObject();
		return result;
	}
	
	protected void write2File(byte[] bytes, File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
}
