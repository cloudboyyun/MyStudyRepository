package study.packetFormat.versionCompatibility;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Before;
import org.junit.Test;

public class VersionCompatibilityTest {
	
	private static ModelVersion2 data = null;
	
	@Before
	public void setUp() throws Exception {
		data = new ModelVersion2();
		data.setUserName("His userName");
		data.setPassword("His password");
		data.setDeviceId("His deviceId");
	}
	
	@Test
	public void testJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		
		// 必须加上这两行代码，才能避免版本不一致的报错。否则会抛异常：org.codehaus.jackson.map.exc.UnrecognizedPropertyException
		DeserializationConfig deserializationConfig = mapper.getDeserializationConfig();
		mapper.setDeserializationConfig(deserializationConfig.without(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES));
		
		StringWriter stringWriter = new StringWriter();
		mapper.writeValue(stringWriter, data);
		String jsonResult = stringWriter.toString();
		
		ModelVersion1 messageResult = mapper.readValue(jsonResult, ModelVersion1.class);
		System.out.println(messageResult);
	}
}
