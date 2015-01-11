package com.cloudboy.study.hessian.service;

import java.io.Serializable;

public class DownloadFileRequest implements Serializable {
	private static final long serialVersionUID = 3981598148162068784L;
	
	private String fileId = null;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
}
