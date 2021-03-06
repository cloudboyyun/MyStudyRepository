package com.cloudboy.study.security;


import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Test;

public class CertificateCoderTest {
	private String password = "0okm,lp-";
	private String alias = "www.cloudboy.com";
	private String certificatePath = "d:/doc/key/java/cloudboy.cer";
	private String keyStorePath = "d:/doc/key/java/cloudboy.keystore";

	@Test
	public void test() throws Exception {
		System.out.println("公钥加密——私钥解密");
		String inputStr = "我要加密A";
		byte[] data = inputStr.getBytes();
		byte[] encrypt = CertificateCoder.encryptByPublicKey(data,
				certificatePath);
		byte[] decrypt = CertificateCoder.decryptByPrivateKey(encrypt,
				keyStorePath, alias, password);
		String outputStr = new String(decrypt);
		System.out.println("加密前: " + inputStr + "\n" + "解密后: " + outputStr);
		// 验证数据一致
		assertArrayEquals(data, decrypt);
		// 验证证书有效
		assertTrue(CertificateCoder.verifyCertificate(certificatePath));
	}

	@Test
	public void testSign() throws Exception {
		System.out.println("私钥加密——公钥解密");
		String inputStr = "我要加密B";
		byte[] data = inputStr.getBytes();
		byte[] encodedData = CertificateCoder.encryptByPrivateKey(data,
				keyStorePath, alias, password);
		byte[] decodedData = CertificateCoder.decryptByPublicKey(encodedData,
				certificatePath);
		String outputStr = new String(decodedData);
		System.out.println("加密前: " + inputStr + "\n" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);
		System.out.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = CertificateCoder.sign(encodedData, keyStorePath, alias,
				password);
		System.out.println("签名:" + sign);
		// 验证签名
		boolean status = CertificateCoder.verify(encodedData, sign,
				certificatePath);
		System.out.println("状态:" + status);
		assertTrue(status);
	}
	
	private String clientKeyStorePath = "d:/doc/key/java/cloudboyClient.keystore";
	private String clientPassword = "654321";
	
	@Test
	public void testHttps() throws Exception {
		URL url = new URL("https://cloudboy:8053/studyWeb/HelloServlet");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		CertificateCoder.configSSLSocketFactory(conn, clientPassword,
				clientKeyStorePath, clientKeyStorePath);
		InputStream is = conn.getInputStream();
		int length = conn.getContentLength();
		DataInputStream dis = new DataInputStream(is);
		byte[] data = new byte[length];
		dis.readFully(data);
		dis.close();
		System.out.println(new String(data));
		conn.disconnect();
	}
}