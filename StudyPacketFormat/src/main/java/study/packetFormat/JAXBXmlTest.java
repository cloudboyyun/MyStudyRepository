package study.packetFormat;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import study.packetFormat.model.DataFactory;
import study.packetFormat.model.XmlJaxbUtil;
import study.packetFormat.model.xml.Message;

public class JAXBXmlTest implements JavaSamplerClient {
	
	
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
	        Message message = DataFactory.getMessage();
	        String xmlResult = XmlJaxbUtil.marshal(message);
	        Message messageResult = XmlJaxbUtil.unmarshal(xmlResult);
			System.out.println("JAXBXmlTest:" + messageResult.getMsgContent().getReimbersementList().getReimbersement().size());
			
			result.setSuccessful(true);
			result.sampleEnd();
		} catch(Exception e) {
			result.setSuccessful(false);
			e.printStackTrace();
		}
		return result;
	}

	

	@Override
	public void teardownTest(JavaSamplerContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
