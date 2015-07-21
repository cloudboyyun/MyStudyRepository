package study.packetFormat;

import java.io.IOException;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import study.packetFormat.model.DataFactory;
import study.packetFormat.model.pb.MyMessage;

import com.squareup.wire.Wire;

public class WireTest implements JavaSamplerClient {
	
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
				MyMessage messageResult = test();
				if (!DataFactory.password.equals(messageResult.msgContent.password)) {
					System.out.println(messageResult.msgContent.password);
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
	
	private MyMessage test() throws IOException {
		MyMessage message = DataFactory.getMessageWire();
        byte[] pbResult = message.toByteArray();
        Wire wire = new Wire();
        MyMessage messageResult = wire.parseFrom(pbResult, MyMessage.class);
		return messageResult;
	}

	@Override
	public void teardownTest(JavaSamplerContext arg0) {
	}
}
