package com.cloudboy.study.httpclient.lesson3;

import java.security.KeyStore;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpClientFactory {
	private static final String LOG_TAG = HttpClientFactory.class.getSimpleName();
	
	private static HttpClient httpClient;

	public static void init() {
	}

	public static HttpClient getHttpClient() {
		try {
			if (httpClient == null)
				httpClient = initClient();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return httpClient;
	}

	private static HttpClient initClient() throws Exception {
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		trustStore.load(null, null);

		SSLSocketFactory sf = new CustomSSLSocketFactory(trustStore);
		sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 1000 * 30);// 连接超时
		HttpConnectionParams.setSoTimeout(params, 1000 * 60);// 读取超时
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

		// 协议设置
		Scheme http = new Scheme("http", PlainSocketFactory.getSocketFactory(),
				80);
		Scheme https = new Scheme("https", sf, 11443);
		SchemeRegistry sr = new SchemeRegistry();
		sr.register(http);
		sr.register(https);

		// 创建访问服务
		ThreadSafeClientConnManager conMgr = new ThreadSafeClientConnManager(
				params, sr);
		httpClient = new DefaultHttpClient(conMgr, params);
		return httpClient;
	}
}
