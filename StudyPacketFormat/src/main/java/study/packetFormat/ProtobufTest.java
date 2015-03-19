package study.packetFormat;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

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
	        
	        Message message = DataFactory.getMessagePB();
	        byte[] pbResult = message.toByteArray();
			Message messageResult = Message.parseFrom(pbResult);
			if(DataFactory.password.equals(messageResult.getMsgContent().getPassword())) {
				result.setSuccessful(true);
			} else {
				System.out.println(messageResult.getMsgContent().getPassword());
				result.setSuccessful(false);
			}
			result.sampleEnd();
		} catch(Exception e) {
			result.setSuccessful(false);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void teardownTest(JavaSamplerContext arg0) {
	}

}
