package com.cloudboy.study.hessian.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.cloudboy.study.hessian.service.ByteArraySerializableInputStream;
import com.cloudboy.study.hessian.service.MyService;
import com.cloudboy.study.hessian.service.UploadFileRequest;
import com.cloudboy.study.hessian.service.UploadFileResponse;

public class DirectClient {
	
	private MyService service;
	static final String url = "http://192.168.15.180:8086/hessian/myService";
	
	@Before
	public void setUp() throws Exception {
		HessianProxyFactory factory = new HessianProxyFactory();
		// 设置超时时长：10秒
		factory.setReadTimeout(10000);
		service = (MyService)factory.create(MyService.class, url);
	}
	
	@Test
    public void sayHello() {
        String result = service.sayHello("cloudboy");
        System.out.println(result);
        
    }
	
	@Test
	public void uploadFile() {
		File file = new File("d:\\temp\\身份证正面.jpg");
		UploadFileRequest request = new UploadFileRequest();
		request.setFileName(file.getName());
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			
			request.setFileStream(new ByteArraySerializableInputStream(fin));
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
}
