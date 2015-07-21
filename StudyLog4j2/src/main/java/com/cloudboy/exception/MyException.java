package com.cloudboy.exception;


public class MyException extends Exception {

	private static final long serialVersionUID = -8561433096006721628L;

	/**
	 * 应用异常码
	 */
	private String code = null;

	/**
	 * 文本消息
	 */
	private String textMessage = null;
	
	public MyException(String message) {
		super(message);
		this.textMessage = message;
	}
	
	public MyException(String code, String message) {
		super(code + ": " + message);
		this.code = code;
		this.textMessage = message;
	}

	public MyException(String code, String textMessage, Throwable cause) {
		super(code + ": " + textMessage, cause);
		this.code = code;
		this.textMessage = textMessage;
	}
	
	@Override
	public String getMessage() {
		return textMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String string) {
		code = string;
	}

	public String getTextMessage() {
		return textMessage;
	}
}