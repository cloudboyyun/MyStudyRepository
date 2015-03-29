package study.protobuf;

import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import study.protobuf.model.MessagePB.Message;
import study.protobuf.model.MessagePB.Message.MsgContent;

public class ProtobufServletTest {
	private static Message requestMessage = null;
	private URI uri = null;
	@Before
	public void setUp() throws Exception {
		Message.Builder requestMessageBuilder = Message.newBuilder();
    	Message.Header.Builder headerBuilder = Message.Header.newBuilder();
    	headerBuilder.setVersion("123456");
    	headerBuilder.setBizType("M001");
    	headerBuilder.setReqTime("20150326");
    	headerBuilder.setUserName("cloudboy");
    	
    	MsgContent.Builder messageContent = MsgContent.newBuilder();
    	requestMessageBuilder.setHeader(headerBuilder);
    	requestMessageBuilder.setMsgContent(messageContent);
    	requestMessage = requestMessageBuilder.build();
    	
    	URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme("http");
		uriBuilder.setHost("192.168.1.106");
		uriBuilder.setPort(8093);
		uriBuilder.setPath("/studyProtobuf/ProtobufServlet");
		uri = uriBuilder.build();
	}

	@Test
	public void testServiceHttpServletRequestHttpServletResponse() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(uri);
		System.out.println("URI:" + httpPost.getURI());
		
		ByteArrayEntity requestEntity = new ByteArrayEntity(requestMessage.toByteArray());
		requestEntity.setContentType("application/x-protobuf");
		httpPost.setEntity(requestEntity);

		CloseableHttpResponse response = httpclient.execute(httpPost);

		System.out.println("response.getProtocolVersion():" + response.getProtocolVersion());
		System.out.println("response.getStatusLine():" + response.getStatusLine());
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			System.out.println("header name(" + header.getName() + ") value("
					+ header.getValue() + ")");
		}
		
		HttpEntity responseEntity = response.getEntity();
		System.out.println("content type:" + responseEntity.getContentType());
		byte[] resultBytes = EntityUtils.toByteArray(responseEntity);
		Message responseMessage = Message.parseFrom(resultBytes);
		System.out.println(responseMessage.toString());
		response.close();
		httpclient.close();
	}
}