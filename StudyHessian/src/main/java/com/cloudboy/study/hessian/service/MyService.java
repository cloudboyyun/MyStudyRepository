package com.cloudboy.study.hessian.service;

public interface MyService {
	/**
	 * 基本常量的Hessian调用
	 * @param yourName
	 * @return
	 */
	public String sayHello(String yourName);
	
	/**
	 * 上传文件
	 * @param request
	 * @return
	 */
	public UploadFileResponse uploadFile(UploadFileRequest request);
	
	/**
	 * 下载文件
	 * @param request
	 * @return
	 */
	public DownloadFileResponse downloadFile(DownloadFileRequest request);
}
