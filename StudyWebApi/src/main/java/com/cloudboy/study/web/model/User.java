package com.cloudboy.study.web.model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -3890714832661657263L;
	private String userName = null;
	private String password = null;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
