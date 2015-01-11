package com.cloudboy.study.hessian.service;

public interface MyService {
	/**
	 * ����������Hessian����
	 * @param yourName
	 * @return
	 */
	public String sayHello(String yourName);
	
	/**
	 * �ϴ��ļ�
	 * @param request
	 * @return
	 */
	public UploadFileResponse uploadFile(UploadFileRequest request);
	
	/**
	 * �����ļ�
	 * @param request
	 * @return
	 */
	public DownloadFileResponse downloadFile(DownloadFileRequest request);
}
