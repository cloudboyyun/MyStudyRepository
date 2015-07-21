package study.packetFormat.versionCompatibility;

/**
 * 序列化版本对比，对象ModelVersion2,增加了一个字段:deviceId
 * @author yun.xia
 *
 */
public class ModelVersion2 {
	private String userName;
	private String password;
	private String deviceId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
