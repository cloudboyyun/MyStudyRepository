package com.cloudboy.study.security;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DESCoderTest {
	@Test
	public void test() throws Exception {
		String inputStr = "1234567890123456";
		String key = DESCoder.initKey();
		System.out.println("原文:\t" + inputStr);
		System.out.println("密钥:\t" + key);
		byte[] inputData = inputStr.getBytes();
		inputData = DESCoder.encrypt(inputData, key);
		System.out.println("加密后:\t" + Base64.encode(inputData));
		byte[] outputData = DESCoder.decrypt(inputData, key);
		String outputStr = new String(outputData);
		System.out.println("解密后:\t" + outputStr);
		assertEquals(inputStr, outputStr);
	}
}