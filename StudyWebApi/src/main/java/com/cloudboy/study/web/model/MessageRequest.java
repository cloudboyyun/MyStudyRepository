package com.cloudboy.study.web.model;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MessageRequest implements Serializable {
	private static final long serialVersionUID = 8221749651777873009L;
	private static XStream xs = new XStream(new DomDriver());
	
	/**
	 * 请求类型
	 */
	private String requestType;
	/**
	 * 用户信息
	 */
	private User user = null;
	
	/**
	 * 银行信息
	 */
	private Bank bank = null;
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	public String toXML() {
		return xs.toXML(this);
	}
	
	public static MessageRequest fromXML(String xml) {
		return (MessageRequest)xs.fromXML(xml);
	}
}
