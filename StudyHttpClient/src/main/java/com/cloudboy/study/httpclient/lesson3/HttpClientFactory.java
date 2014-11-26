package com.cloudboy.study.httpclient.lesson3;

import java.io.FileInputStream;
import java.nio.charset.CodingErrorAction;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.cloudboy.util.log.MyLogger;

public class HttpClientFactory {
	private static MyLogger logger = MyLogger.getLogger(HttpClientFactory.class);
	
	private static HttpClient httpClient;

	public static void init() {
	}

	public static HttpClient getHttpClient() {
		try {
			if (httpClient == null) {
				httpClient = initClient();
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return httpClient;
	}
	
	/**
	 * 当我们有crt证书文件时，采用这个方法初始化KeyStore
	 * @return
	 * @throws Exception
	 */
	protected static KeyStore getKeyStoreByCrtFile() throws Exception {
		String keyStoreType = KeyStore.getDefaultType();
		logger.info("keyStoreType:", keyStoreType);
		KeyStore ks = KeyStore.getInstance(keyStoreType);
		ks.load(null, null);
		
		// 读取crt证书文件(这里使用ca.crt或者server.crt都行)
		String certificationPath = "D:\\doc\\key\\server.crt";
//		String certificationPath = "D:\\doc\\key\\ca.crt";
		FileInputStream fis = new FileInputStream(certificationPath);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		Certificate cert = cf.generateCertificate(fis);
		fis.close();
		logger.info("public key:", cert.getPublicKey());
		ks.setCertificateEntry("myServer", cert);
		return ks;
	}
	
	/**
	 * 当我们已经把证书导入java的密钥库文件时（使用keytool命令）， 使用这个方法初始化KeyStore
	 * @return
	 * @throws Exception
	 */
	protected static KeyStore getKeyStoreByJKSFile() throws Exception {
		String keyStorePath = "D:\\doc\\key\\server.jks";
		String password = "0okm,lp-";
		FileInputStream is = new FileInputStream(keyStorePath);
		String keyStoreType = KeyStore.getDefaultType();
		logger.info("keyStoreType:", keyStoreType);
		KeyStore ks = KeyStore.getInstance(keyStoreType);
		ks.load(is, password.toCharArray());
		is.close();
		return ks;
	}

	private static HttpClient initClient() throws Exception {
//		KeyStore trustStore = getKeyStoreByJKSFile();
		KeyStore trustStore = getKeyStoreByCrtFile();
		
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom()
				.loadTrustMaterial(trustStore)
				.build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

		// Create a registry of custom connection socket factories for supported
		// protocol schemes.
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
				.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https",sslsf)
				.build();
		
		// Create message constraints
		MessageConstraints messageConstraints = MessageConstraints.custom()
				.setMaxHeaderCount(200).setMaxLineLength(2000).build();
		ConnectionConfig connectionConfig = ConnectionConfig.custom()
				.setMalformedInputAction(CodingErrorAction.IGNORE)
				.setUnmappableInputAction(CodingErrorAction.IGNORE)
				.setCharset(Consts.UTF_8)
				.setMessageConstraints(messageConstraints).build();
		
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		connManager.setDefaultMaxPerRoute(10);
		connManager.setMaxTotal(50);
		connManager.setDefaultConnectionConfig(connectionConfig);

		RequestConfig defaultRequestConfig = RequestConfig
				.custom()
				.setCookieSpec(CookieSpecs.BEST_MATCH)
				.setExpectContinueEnabled(true)
				.setStaleConnectionCheckEnabled(true)
				.setTargetPreferredAuthSchemes(
						Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
				.setConnectTimeout(1000 * 30).setSocketTimeout(1000 * 60)
				.build();
		

		CloseableHttpClient hclient = HttpClients.custom()
				.setConnectionManager(connManager)
				.setDefaultRequestConfig(defaultRequestConfig).build();

		return hclient;
	}
}
