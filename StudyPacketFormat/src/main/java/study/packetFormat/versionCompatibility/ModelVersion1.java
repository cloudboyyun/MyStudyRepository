package study.packetFormat.versionCompatibility;

/**
 * 序列化版本对比，对象ModelVersion1只有两个字段：userName, password
 * @author yun.xia
 *
 */
public class ModelVersion1 {
	private String userName;
	private String password;
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
}
