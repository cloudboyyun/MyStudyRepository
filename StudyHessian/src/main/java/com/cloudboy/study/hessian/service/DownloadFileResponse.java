package com.cloudboy.study.hessian.service;

import java.io.Serializable;

public class DownloadFileResponse implements Serializable {
	private static final long serialVersionUID = 5005040446776735998L;

	private ByteArraySerializableInputStream fileStream;

	public ByteArraySerializableInputStream getFileStream() {
		return fileStream;
	}

	public void setFileStream(ByteArraySerializableInputStream fileStream) {
		this.fileStream = fileStream;
	}
}
