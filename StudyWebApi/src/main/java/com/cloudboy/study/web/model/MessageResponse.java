package com.cloudboy.study.web.model;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MessageResponse implements Serializable {
	private static final long serialVersionUID = -8559364860196998757L;
	private static XStream xs = new XStream(new DomDriver());
	
	/**
	 * 返回代码， 00表示成功
	 */
	private String responseCode;
	
	/**
	 * 返回信息
	 */
	private String message;
	
	/**
	 * 银行信息
	 */
	private Bank bank = null;
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bankInfo) {
		this.bank = bankInfo;
	}
	
	public String toXML() {
		return xs.toXML(this);
	}
	
	public static MessageResponse fromXML(String xml) {
		return (MessageResponse)xs.fromXML(xml);
	}
}
