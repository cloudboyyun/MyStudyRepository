package com.cloudboy.study.web.lesson4;

/** 
 * 此工具类只适用于Servlet 3.0 
 * 为了弥补 Servlet 3.0 文件上传时获取文件类型的困难问题 
 */
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

public class UploadUtil {
	private static Logger logger = Logger.getLogger(UploadUtil.class);

	public static String getFileType(Part p) {
		String name = p.getHeader("content-disposition");
		logger.info("name:" + name);
		String fileNameTmp = name.substring(name.indexOf("filename=") + 10);
		logger.info("fileNameTmp:" + fileNameTmp);
		String type = fileNameTmp.substring(fileNameTmp.indexOf(".") + 1,
				fileNameTmp.indexOf("\""));
		logger.info("type:" + type);
		return type;
	}

	public static String getFileName(Part p) {
		String name = p.getHeader("content-disposition");
		String fileNameTmp = name.substring(name.indexOf("filename=") + 10);
		String fileName = fileNameTmp.substring(0, fileNameTmp.indexOf("\""));
		return fileName;
	}
}