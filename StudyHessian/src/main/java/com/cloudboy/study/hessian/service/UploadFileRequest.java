package com.cloudboy.study.hessian.service;

import java.io.Serializable;

public class UploadFileRequest implements Serializable {
	private static final long serialVersionUID = 5005040446776735998L;

	private String fileName;
//	private InputStream fileStream;
	private ByteArraySerializableInputStream fileStream;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ByteArraySerializableInputStream getFileStream() {
		return fileStream;
	}

	public void setFileStream(ByteArraySerializableInputStream fileStream) {
		this.fileStream = fileStream;
	}
}
