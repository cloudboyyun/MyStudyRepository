package com.cloudboy.study.jaxb.lesson2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.cloudboy.study.jaxb.lesson2.jaxb.Message;
import com.cloudboy.study.jaxb.lesson2.jaxb.MsgContent;

public class JAXBLesson2 {
	
	/**
	 * convert object to xml
	 * @param obj
	 * @return
	 */
	public static String marshal(Object obj) {
		String xmlData = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("com.cloudboy.study.jaxb.lesson2.jaxb");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(obj, outputStream);
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			xmlData = new String(outputStream.toByteArray(), "UTF-8");
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xmlData;
	}
	
	/**
	 * conver xml to object
	 * @param xmldata
	 * @return
	 */
	public static Message unmarshal(String xmldata) {
		Message result = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("com.cloudboy.study.jaxb.lesson2.jaxb");
			Unmarshaller um = jaxbContext.createUnmarshaller();
			result = (Message) um.unmarshal(new ByteArrayInputStream(xmldata.getBytes("UTF-8")));
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	public static void main(String[] args) throws DatatypeConfigurationException {

		Message message = new Message();
		message.setUserName("cloudboy");
		GregorianCalendar nowGregorianCalendar =new GregorianCalendar(); 
		XMLGregorianCalendar xmlDatetime= DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);  
		message.setReqTime(xmlDatetime);
		MsgContent content = new MsgContent();
		content.setAmount(new BigDecimal(123.56));
		message.setMsgContent(content);

		String result = marshal(message);
		System.out.println(result);
		
		String xmlData = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>"
				+ "<message xmlns='https://com.cloudboy/study'><reqTime>2014-11-02+08:00</reqTime><userName>cloudboy</userName>"
				+ "<msgContent><amount>143.576</amount></msgContent></message>";
		Message messageResult = unmarshal(xmlData);
		System.out.println(messageResult.getMsgContent().getAmount());
	}
}