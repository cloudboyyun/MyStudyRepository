package com.cloudboy.study.hessian.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

public class ByteArraySerializableInputStream implements Serializable{
	private static Logger logger = Logger.getLogger(ByteArraySerializableInputStream.class);
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	private byte buf[];

	private int  count;
    
	public ByteArraySerializableInputStream(){
		this.buf = new byte[]{};
        this.count = new byte[]{}.length;
	}
	
	public ByteArraySerializableInputStream(byte[] buf){
		this.buf   = buf;
        this.count = buf.length;
	}

	public ByteArraySerializableInputStream(InputStream is){
		byte[] inputBuf=null;
		try {
			inputBuf = IOUtils.toByteArray(is);
		} catch (IOException ex) {
			inputBuf=new byte[]{};
		}
		this.buf   = inputBuf;
        this.count = inputBuf.length;
        logger.info("count:" + count);
	}
	
	public ByteArrayInputStream getByteArrayInputStream() {
        return new ByteArrayInputStream(this.buf);
    }
}


