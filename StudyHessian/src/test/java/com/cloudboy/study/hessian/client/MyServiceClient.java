package com.cloudboy.study.hessian.client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloudboy.study.hessian.service.ByteArraySerializableInputStream;
import com.cloudboy.study.hessian.service.DownloadFileRequest;
import com.cloudboy.study.hessian.service.DownloadFileResponse;
import com.cloudboy.study.hessian.service.MyService;
import com.cloudboy.study.hessian.service.UploadFileRequest;
import com.cloudboy.study.hessian.service.UploadFileResponse;

public class MyServiceClient {
	
	private MyService service;
	private ClassPathXmlApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("clientContext.xml");
		service = (MyService)context.getBean("myServiceClient");
	}
	
	@After
	public void tearDown() throws Exception {
		context.close();
	}
	
	@Test
    public void sayHello() {
        String result = service.sayHello("cloudboy");
        System.out.println(result);
        
    }
	
	@Test
	public void uploadFile() {
		File file = new File("d:\\temp\\result.jpg");
		UploadFileRequest request = new UploadFileRequest();
		request.setFileName(file.getName());
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			ByteArraySerializableInputStream in = new ByteArraySerializableInputStream(fin);
			request.setFileStream(in);
			UploadFileResponse response = service.uploadFile(request);
			System.out.println(response.isSuccess());
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
	}
	
	@Test
	public void downloadFile() {
		DownloadFileRequest request = new DownloadFileRequest();
		request.setFileId("123");
		DownloadFileResponse response = service.downloadFile(request);
		File file = new File("d:\\temp\\下载的文件" + System.currentTimeMillis() + ".jpg");
		FileOutputStream fileoutstream = null;
		try {
			fileoutstream = new FileOutputStream(file);
			byte[] bytes = new byte[1024];
			int length = 0;
			ByteArrayInputStream inputStream = response.getFileStream().getByteArrayInputStream();
			while ((length = inputStream.read(bytes)) != -1) {
				fileoutstream.write(bytes, 0, length);
			}
			System.out.println("file is saved to:" + file.getPath());
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
	}
}
