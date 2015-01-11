package com.cloudboy.study.hessian.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.cloudboy.study.hessian.service.ByteArraySerializableInputStream;
import com.cloudboy.study.hessian.service.DownloadFileRequest;
import com.cloudboy.study.hessian.service.DownloadFileResponse;
import com.cloudboy.study.hessian.service.MyService;
import com.cloudboy.study.hessian.service.UploadFileRequest;
import com.cloudboy.study.hessian.service.UploadFileResponse;

public class MyServiceImpl implements MyService {
	private static Logger logger = Logger.getLogger(MyServiceImpl.class);

	@Override
	public String sayHello(String yourName) {
		logger.info("sayHello is invoked by " + yourName);
		return "Hello:" + yourName;
	}

	@Override
	public UploadFileResponse uploadFile(UploadFileRequest request) {
		logger.info("uploadFile is invoking:" + request.getFileName());
		File file = new File("/opt/tmp/studyHessian/" + System.currentTimeMillis() + "_" + request.getFileName());
		FileOutputStream fileoutstream = null;
		try {
			fileoutstream = new FileOutputStream(file);
			byte[] bytes = new byte[1024];
			int length = 0;
			ByteArrayInputStream inputStream = request.getFileStream().getByteArrayInputStream();
			while ((length = inputStream.read(bytes)) != -1) {
				fileoutstream.write(bytes, 0, length);
			}
			logger.info("file is saved to:" + file.getPath());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(fileoutstream != null) {
				try {
					fileoutstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		UploadFileResponse response = new UploadFileResponse();
		response.setSuccess(true);
		return response;
	}

	@Override
	public DownloadFileResponse downloadFile(DownloadFileRequest request) {
		DownloadFileResponse response = new DownloadFileResponse();
		File file = new File("/opt/tmp/studyHessian/身份证正面.jpg");
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			ByteArraySerializableInputStream in = new ByteArraySerializableInputStream(fin);
			response.setFileStream(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return response;
	}
}