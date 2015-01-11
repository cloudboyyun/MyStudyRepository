package com.cloudboy.study.hessian.service;

import java.io.Serializable;

public class UploadFileResponse implements Serializable {

	private static final long serialVersionUID = -1231752256020997555L;
	
	private boolean success = false;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
