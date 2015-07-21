package study.packetFormat.json;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import study.packetFormat.Constants;
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
		} catch (Exception e) {
			result.setSuccessful(false);
			e.printStackTrace();
		}
		return result;
	}
	
	private Message test() throws JsonGenerationException, JsonMappingException, IOException {
		Message message = DataFactory.getMessage();
		StringWriter stringWriter = new StringWriter();
		mapper.writeValue(stringWriter, message);
		String jsonResult = stringWriter.toString();
		Message messageResult = mapper.readValue(jsonResult, Message.class);
		return messageResult;
	}

	@Override
	public void teardownTest(JavaSamplerContext arg0) {
	}

}
