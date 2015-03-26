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
import study.packetFormat.model.GzipUtil;
import study.packetFormat.model.XmlJaxbUtil;
import study.packetFormat.model.pb.MessagePB;
import study.packetFormat.model.xml.Message;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.google.protobuf.InvalidProtocolBufferException;
import com.thoughtworks.xstream.XStream;

public class SizeCompareUT {
	
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
		System.out.println(pbMessage.toString());
		System.out.println("--------------");
		System.out.println(xs.toXML(pbMessage));
		System.out.println(pbMessage.getMsgContent().getReimbersementListCount());
	}
	
	@Test
	public void testZippedJson() throws JsonGenerationException, JsonMappingException, IOException {
		String jsonResult = toJson(message);
		byte[] jsonBytes = jsonResult.getBytes();
		byte[] jsonZippedBytes = GzipUtil.compress(jsonBytes);
		
		byte[] jsonBytes2 = GzipUtil.decompress(jsonZippedBytes);
		String jsonResult2 = new String(jsonBytes2);
		System.out.println(jsonResult);
		System.out.println(jsonResult2);
		System.out.println(jsonResult.equals(jsonResult2));
	}
	
	@Test
	public void compareCompressRatio() throws JsonGenerationException, JsonMappingException, IOException {
		byte[] xml = toXML(message).getBytes();
		int xmlSize = xml.length;
		
		byte[] xmlZipped = GzipUtil.compress(xml);
		int xmlZippedSize = xmlZipped.length;
		
		byte[] json = toJson(message).getBytes();
		int jsonSize = json.length;
		
		byte[] jsonZipped = GzipUtil.compress(json);
		int jsonZippedSize = jsonZipped.length;
		
		byte[] hessian = toHessian(message);
		int hessianSize = hessian.length;
		
		byte[] hessianZipped = GzipUtil.compress(hessian);
		int hessianZippedSize = hessianZipped.length;
		
		byte[] pb = messagePB.toByteArray();
		int pbSize = pb.length;
		
		byte[] pbZipped = GzipUtil.compress(pb);
		int pbZippedSize = pbZipped.length;
		
		System.out.println("xml size(bytes):" + xmlSize);
		System.out.println("xmlZippedSize(bytes):" + xmlZippedSize);
		System.out.println("json size(bytes):" + jsonSize);
		System.out.println("zippedJson size(bytes):" + jsonZippedSize);
		System.out.println("hessianSize size(bytes):" + hessianSize);
		System.out.println("hessianZippedSize size(bytes):" + hessianZippedSize);
		System.out.println("pbSize size(bytes):" + pbSize);
		System.out.println("pbZippedSize size(bytes):" + pbZippedSize);
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
