package study.packetFormat;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.google.protobuf.InvalidProtocolBufferException;

import study.packetFormat.model.DataFactory;
import study.packetFormat.model.pb.MessagePB.Message;

public class ProtobufTest implements JavaSamplerClient {
	
	@Override
	public Arguments getDefaultParameters() {
		return null;
	}
	
	@Override
	public void setupTest(JavaSamplerContext arg0) {
	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult result = new SampleResult();
		try {
	        result.sampleStart();
	        result.setSuccessful(true);
	        for (int i = 0; i < Constants.COUNT; i++) {
				Message messageResult = test();
				if (!DataFactory.password.equals(messageResult.getMsgContent()
						.getPassword())) {
					System.out.println(messageResult.getMsgContent()
							.getPassword());
					result.setSuccessful(false);
				}
			}
			result.sampleEnd();
		} catch(Exception e) {
			result.setSuccessful(false);
			e.printStackTrace();
		}
		return result;
	}
	
	private Message test() throws InvalidProtocolBufferException {
		Message message = DataFactory.getMessagePB();
        byte[] pbResult = message.toByteArray();
		Message messageResult = Message.parseFrom(pbResult);
		return messageResult;
	}

	@Override
	public void teardownTest(JavaSamplerContext arg0) {
	}

}
