package com.cloudboy.study.jaxb.lesson1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cloudboy.study.jaxb.lesson1.jaxb.Customer;

public class JAXBExample {
	
	/**
	 * convert object to xml
	 * @param obj
	 * @return
	 */
	public static String marshal(Customer obj) {
		String xmlData = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
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
	public static Customer unmarshal(String xmldata) {
		Customer result = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Unmarshaller um = jaxbContext.createUnmarshaller();
			result = (Customer) um.unmarshal(new ByteArrayInputStream(xmldata.getBytes("UTF-8")));
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	public static void main(String[] args) {

		Customer customer = new Customer();
		customer.setId(100);
		customer.setName("mkyong");
		customer.setAge(29);

		String result = marshal(customer);
		System.out.println(result);
		
		String xmlData = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><customer id='100'><age>32</age><name>mkyong</name></customer>";
		Customer customerResult = unmarshal(xmlData);
		System.out.println(customerResult.getAge());
	}
}