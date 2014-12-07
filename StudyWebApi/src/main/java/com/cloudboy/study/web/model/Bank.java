package com.cloudboy.study.web.model;

import java.io.Serializable;

public class Bank implements Serializable {
	private static final long serialVersionUID = 3342820692691035081L;
	private String bankId = null;
	private String bankName = null;
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
