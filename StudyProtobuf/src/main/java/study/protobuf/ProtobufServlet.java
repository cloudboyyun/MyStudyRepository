package study.protobuf;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import study.protobuf.model.MessagePB;
import study.protobuf.model.MessagePB.Message;
import study.protobuf.model.MessagePB.Message.MsgContent;

@WebServlet(
	    name="ProtobufServlet",
	    urlPatterns={"/ProtobufServlet"},
	    loadOnStartup=1)  
public class ProtobufServlet extends HttpServlet {
	private static final long serialVersionUID = -3031109442218282414L;
	private static Logger logger = Logger.getLogger(ProtobufServlet.class);

	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	logger.info("content type:" + request.getContentType());
    	byte[] requestBytes = IOUtils.toByteArray(request.getInputStream());
    	Message requestMessage = MessagePB.Message.parseFrom(requestBytes);
    	logger.info(requestMessage.toString());
    	
    	Message.Builder responsMessageBuilder = Message.newBuilder();
    	Message.Header.Builder headerBuilder = Message.Header.newBuilder();
    	headerBuilder.setVersion("123456");
    	headerBuilder.setBizType(requestMessage.getHeader().getBizType());
    	headerBuilder.setReqTime(requestMessage.getHeader().getReqTime());
    	headerBuilder.setUserName(requestMessage.getHeader().getUserName());
    	MsgContent.Builder messageContent = MsgContent.newBuilder();
    	responsMessageBuilder.setHeader(headerBuilder);
    	responsMessageBuilder.setMsgContent(messageContent);
    	Message responseMessage = responsMessageBuilder.build();
    	
    	response.setContentType("application/x-protobuf");
    	response.getOutputStream().write(responseMessage.toByteArray());
    	response.flushBuffer();
    }
}
