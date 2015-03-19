package study.packetFormat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import study.packetFormat.model.DataFactory;
import study.packetFormat.model.xml.Message;

public class HessianTest implements JavaSamplerClient {
	
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
	        
	        byte[] pbResult = toHessian(message);
			Message messageResult = (Message)fromHessian(pbResult);
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
	
	private byte[] toHessian(Object message) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Hessian2Output out = new Hessian2Output(bos);
		out.writeObject(message);
		out.flush();
		bos.close();
		return bos.toByteArray();
	}
	
	protected Object fromHessian(byte[] hessianBytes) throws IOException {
		ByteArrayInputStream bin = new ByteArrayInputStream(hessianBytes);
		Hessian2Input in = new Hessian2Input(bin);
		Object result = in.readObject();
		bin.close();
		return result;
	}

	@Override
	public void teardownTest(JavaSamplerContext arg0) {
	}

}
