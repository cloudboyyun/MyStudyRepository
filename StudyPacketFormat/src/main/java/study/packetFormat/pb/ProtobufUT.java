package study.packetFormat.pb;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Before;
import org.junit.Test;

import study.packetFormat.LogUtils;
import study.packetFormat.model.DataFactory;
import study.packetFormat.model.pb.Data1;
import study.packetFormat.model.pb.Data2;
import study.packetFormat.model.pb.MessagePB;
import study.packetFormat.model.pb.MyMessage;
import study.packetFormat.model.pb.MyModelPB;

import com.google.protobuf.InvalidProtocolBufferException;
import com.thoughtworks.xstream.XStream;

public class ProtobufUT {
	private static XStream xs = new XStream();
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testPB() throws InvalidProtocolBufferException {
		MessagePB.Message messagePB = DataFactory.getMessagePB();
		MessagePB.Message.Builder message =  messagePB.toBuilder();
		message.setSign("123");
		System.out.println(LogUtils.getLogMessage(messagePB));
	}
	
	@Test
	public void testCopy() throws IllegalAccessException, InvocationTargetException {
		Data1 data1 = new Data1();
		data1.setUserName("xiayun");
		data1.setPassword("xiayunpw");
		
		Data2 data2 = new Data2();
		BeanUtils.copyProperties(data2, data1);
		
		System.out.println(xs.toXML(data2));
	
		MyModelPB.Data.Builder data3 = MyModelPB.Data.newBuilder();
		BeanUtils.copyProperties(data3, data1);
		System.out.println("result:\n:" + data3.build().toString());
	}
	
	@Test
	public void testWire() throws InvalidProtocolBufferException {
		MyMessage messageWire = DataFactory.getMessageWire();

		System.out.println(messageWire.toString());
		MyMessage.Builder message =  new MyMessage.Builder(messageWire);
		message.sign("123");
		System.out.println(message.toString());
	}
}
