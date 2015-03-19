package study.packetFormat.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import study.packetFormat.model.xml.Message;

public class XmlJaxbUtil {
	
	private static Logger logger = Logger.getLogger(XmlJaxbUtil.class);
	public static final String CHARSET = "UTF-8";
	private static JAXBContext jc = null;
	static {
		try {
			jc = JAXBContext.newInstance("study.packetFormat.model.xml");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static Message unmarshal(String xmldata) {
		Message message = null;
		try {
			Unmarshaller um = jc.createUnmarshaller();
			message = (Message) um.unmarshal(new ByteArrayInputStream(xmldata
					.getBytes(CHARSET)));
		} catch (JAXBException e) {
			logger.error( "Error when unmarshal xml data", e);
		} catch (Exception e) {
			logger.error( "Error when unmarshal xml data", e);
		}
		return message;
	}

	public static String marshal(Message message) {
		String xmlData = null;
		try {
			Marshaller m = jc.createMarshaller();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			m.marshal(message, baos);
			xmlData = new String(baos.toByteArray(), CHARSET);
		} catch (JAXBException e) {
			String errmsg = "Error when marshal MasMessage, errmsg: "
					+ e.getMessage();
			logger.error(errmsg, e);
		} catch (UnsupportedEncodingException e) {
			String errmsg = "Error when marshal MasMessage, errmsg: "
					+ e.getMessage();
			logger.error(errmsg, e);
		}
		return xmlData;
	}
	

}
