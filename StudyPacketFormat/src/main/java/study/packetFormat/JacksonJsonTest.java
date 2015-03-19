package study.packetFormat;

import java.io.StringWriter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import study.packetFormat.model.DataFactory;
import study.packetFormat.model.xml.Message;

public class JacksonJsonTest implements JavaSamplerClient {
	private static ObjectMapper mapper = null;
	
	@Override
	public Arguments getDefaultParameters() {
		return null;
	}
	
	@Override
	public void setupTest(JavaSamplerContext arg0) {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult result = new SampleResult();
		try {
	        result.sampleStart();
	        Message message = DataFactory.getMessage();
			StringWriter stringWriter = new StringWriter();
			mapper.writeValue(stringWriter, message);
			String jsonResult = stringWriter.toString();
			Message messageResult = mapper.readValue(jsonResult, Message.class);
			
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
