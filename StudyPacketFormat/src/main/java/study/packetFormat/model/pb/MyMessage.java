// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: D:\yun.xia\workspace\MA-CPS_DEV\StudyPacketFormat\data\message.proto
package study.packetFormat.model.pb;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;
import java.util.Collections;
import java.util.List;

import static com.squareup.wire.Message.Datatype.STRING;
import static com.squareup.wire.Message.Label.REPEATED;
import static com.squareup.wire.Message.Label.REQUIRED;

public final class MyMessage extends Message {
  private static final long serialVersionUID = 0L;

  public static final String DEFAULT_SIGN = "";

  @ProtoField(tag = 1, type = STRING)
  public final String sign;

  @ProtoField(tag = 2)
  public final Header header;

  @ProtoField(tag = 3)
  public final MsgContent msgContent;

  public MyMessage(String sign, Header header, MsgContent msgContent) {
    this.sign = sign;
    this.header = header;
    this.msgContent = msgContent;
  }

  private MyMessage(Builder builder) {
    this(builder.sign, builder.header, builder.msgContent);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof MyMessage)) return false;
    MyMessage o = (MyMessage) other;
    return equals(sign, o.sign)
        && equals(header, o.header)
        && equals(msgContent, o.msgContent);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = sign != null ? sign.hashCode() : 0;
      result = result * 37 + (header != null ? header.hashCode() : 0);
      result = result * 37 + (msgContent != null ? msgContent.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<MyMessage> {

    public String sign;
    public Header header;
    public MsgContent msgContent;

    public Builder() {
    }

    public Builder(MyMessage message) {
      super(message);
      if (message == null) return;
      this.sign = message.sign;
      this.header = message.header;
      this.msgContent = message.msgContent;
    }

    public Builder sign(String sign) {
      this.sign = sign;
      return this;
    }

    public Builder header(Header header) {
      this.header = header;
      return this;
    }

    public Builder msgContent(MsgContent msgContent) {
      this.msgContent = msgContent;
      return this;
    }

    @Override
    public MyMessage build() {
      return new MyMessage(this);
    }
  }

  public static final class DeviceInfo extends Message {
    private static final long serialVersionUID = 0L;

    public static final String DEFAULT_MAC = "";
    public static final String DEFAULT_IMEI = "";
    public static final String DEFAULT_IMSI = "";
    public static final String DEFAULT_DEVICEID = "";

    @ProtoField(tag = 1, type = STRING)
    public final String mac;

    @ProtoField(tag = 2, type = STRING)
    public final String imei;

    @ProtoField(tag = 3, type = STRING)
    public final String imsi;

    @ProtoField(tag = 4, type = STRING)
    public final String deviceId;

    public DeviceInfo(String mac, String imei, String imsi, String deviceId) {
      this.mac = mac;
      this.imei = imei;
      this.imsi = imsi;
      this.deviceId = deviceId;
    }

    private DeviceInfo(Builder builder) {
      this(builder.mac, builder.imei, builder.imsi, builder.deviceId);
      setBuilder(builder);
    }

    @Override
    public boolean equals(Object other) {
      if (other == this) return true;
      if (!(other instanceof DeviceInfo)) return false;
      DeviceInfo o = (DeviceInfo) other;
      return equals(mac, o.mac)
          && equals(imei, o.imei)
          && equals(imsi, o.imsi)
          && equals(deviceId, o.deviceId);
    }

    @Override
    public int hashCode() {
      int result = hashCode;
      if (result == 0) {
        result = mac != null ? mac.hashCode() : 0;
        result = result * 37 + (imei != null ? imei.hashCode() : 0);
        result = result * 37 + (imsi != null ? imsi.hashCode() : 0);
        result = result * 37 + (deviceId != null ? deviceId.hashCode() : 0);
        hashCode = result;
      }
      return result;
    }

    public static final class Builder extends Message.Builder<DeviceInfo> {

      public String mac;
      public String imei;
      public String imsi;
      public String deviceId;

      public Builder() {
      }

      public Builder(DeviceInfo message) {
        super(message);
        if (message == null) return;
        this.mac = message.mac;
        this.imei = message.imei;
        this.imsi = message.imsi;
        this.deviceId = message.deviceId;
      }

      public Builder mac(String mac) {
        this.mac = mac;
        return this;
      }

      public Builder imei(String imei) {
        this.imei = imei;
        return this;
      }

      public Builder imsi(String imsi) {
        this.imsi = imsi;
        return this;
      }

      public Builder deviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
      }

      @Override
      public DeviceInfo build() {
        return new DeviceInfo(this);
      }
    }
  }

  public static final class Header extends Message {
    private static final long serialVersionUID = 0L;

    public static final String DEFAULT_VERSION = "4.0";
    public static final String DEFAULT_APPVERSION = "";
    public static final String DEFAULT_BIZTYPE = "";
    public static final String DEFAULT_REQTIME = "";
    public static final String DEFAULT_RESPONSECODE = "";
    public static final String DEFAULT_RESPONSEMSG = "";
    public static final String DEFAULT_USERNAME = "";
    public static final String DEFAULT_LOGINTOKEN = "";

    @ProtoField(tag = 1, type = STRING, label = REQUIRED)
    public final String version;

    @ProtoField(tag = 2, type = STRING)
    public final String appVersion;

    @ProtoField(tag = 3, type = STRING, label = REQUIRED)
    public final String bizType;

    @ProtoField(tag = 4, type = STRING, label = REQUIRED)
    public final String reqTime;

    @ProtoField(tag = 5, type = STRING)
    public final String responseCode;

    @ProtoField(tag = 6, type = STRING)
    public final String responseMsg;

    @ProtoField(tag = 7)
    public final DeviceInfo deviceInfo;

    @ProtoField(tag = 8, type = STRING, label = REQUIRED)
    public final String userName;

    @ProtoField(tag = 9, type = STRING)
    public final String loginToken;

    public Header(String version, String appVersion, String bizType, String reqTime, String responseCode, String responseMsg, DeviceInfo deviceInfo, String userName, String loginToken) {
      this.version = version;
      this.appVersion = appVersion;
      this.bizType = bizType;
      this.reqTime = reqTime;
      this.responseCode = responseCode;
      this.responseMsg = responseMsg;
      this.deviceInfo = deviceInfo;
      this.userName = userName;
      this.loginToken = loginToken;
    }

    private Header(Builder builder) {
      this(builder.version, builder.appVersion, builder.bizType, builder.reqTime, builder.responseCode, builder.responseMsg, builder.deviceInfo, builder.userName, builder.loginToken);
      setBuilder(builder);
    }

    @Override
    public boolean equals(Object other) {
      if (other == this) return true;
      if (!(other instanceof Header)) return false;
      Header o = (Header) other;
      return equals(version, o.version)
          && equals(appVersion, o.appVersion)
          && equals(bizType, o.bizType)
          && equals(reqTime, o.reqTime)
          && equals(responseCode, o.responseCode)
          && equals(responseMsg, o.responseMsg)
          && equals(deviceInfo, o.deviceInfo)
          && equals(userName, o.userName)
          && equals(loginToken, o.loginToken);
    }

    @Override
    public int hashCode() {
      int result = hashCode;
      if (result == 0) {
        result = version != null ? version.hashCode() : 0;
        result = result * 37 + (appVersion != null ? appVersion.hashCode() : 0);
        result = result * 37 + (bizType != null ? bizType.hashCode() : 0);
        result = result * 37 + (reqTime != null ? reqTime.hashCode() : 0);
        result = result * 37 + (responseCode != null ? responseCode.hashCode() : 0);
        result = result * 37 + (responseMsg != null ? responseMsg.hashCode() : 0);
        result = result * 37 + (deviceInfo != null ? deviceInfo.hashCode() : 0);
        result = result * 37 + (userName != null ? userName.hashCode() : 0);
        result = result * 37 + (loginToken != null ? loginToken.hashCode() : 0);
        hashCode = result;
      }
      return result;
    }

    public static final class Builder extends Message.Builder<Header> {

      public String version;
      public String appVersion;
      public String bizType;
      public String reqTime;
      public String responseCode;
      public String responseMsg;
      public DeviceInfo deviceInfo;
      public String userName;
      public String loginToken;

      public Builder() {
      }

      public Builder(Header message) {
        super(message);
        if (message == null) return;
        this.version = message.version;
        this.appVersion = message.appVersion;
        this.bizType = message.bizType;
        this.reqTime = message.reqTime;
        this.responseCode = message.responseCode;
        this.responseMsg = message.responseMsg;
        this.deviceInfo = message.deviceInfo;
        this.userName = message.userName;
        this.loginToken = message.loginToken;
      }

      public Builder version(String version) {
        this.version = version;
        return this;
      }

      public Builder appVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
      }

      public Builder bizType(String bizType) {
        this.bizType = bizType;
        return this;
      }

      public Builder reqTime(String reqTime) {
        this.reqTime = reqTime;
        return this;
      }

      public Builder responseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
      }

      public Builder responseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
        return this;
      }

      public Builder deviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
        return this;
      }

      public Builder userName(String userName) {
        this.userName = userName;
        return this;
      }

      public Builder loginToken(String loginToken) {
        this.loginToken = loginToken;
        return this;
      }

      @Override
      public Header build() {
        checkRequiredFields();
        return new Header(this);
      }
    }
  }

  public static final class Reimbersement extends Message {
    private static final long serialVersionUID = 0L;

    public static final String DEFAULT_CREDITBANKID = "";
    public static final String DEFAULT_CREDITBANKNAME = "";
    public static final String DEFAULT_CREDITCARDNO = "";
    public static final String DEFAULT_AMOUNT = "";
    public static final String DEFAULT_REQUESTDATE = "";
    public static final String DEFAULT_STATUS = "";

    @ProtoField(tag = 1, type = STRING)
    public final String creditBankId;

    @ProtoField(tag = 2, type = STRING)
    public final String creditBankName;

    @ProtoField(tag = 3, type = STRING)
    public final String creditCardNo;

    @ProtoField(tag = 4, type = STRING)
    public final String amount;

    @ProtoField(tag = 5, type = STRING)
    public final String requestDate;

    @ProtoField(tag = 6, type = STRING)
    public final String status;

    public Reimbersement(String creditBankId, String creditBankName, String creditCardNo, String amount, String requestDate, String status) {
      this.creditBankId = creditBankId;
      this.creditBankName = creditBankName;
      this.creditCardNo = creditCardNo;
      this.amount = amount;
      this.requestDate = requestDate;
      this.status = status;
    }

    private Reimbersement(Builder builder) {
      this(builder.creditBankId, builder.creditBankName, builder.creditCardNo, builder.amount, builder.requestDate, builder.status);
      setBuilder(builder);
    }

    @Override
    public boolean equals(Object other) {
      if (other == this) return true;
      if (!(other instanceof Reimbersement)) return false;
      Reimbersement o = (Reimbersement) other;
      return equals(creditBankId, o.creditBankId)
          && equals(creditBankName, o.creditBankName)
          && equals(creditCardNo, o.creditCardNo)
          && equals(amount, o.amount)
          && equals(requestDate, o.requestDate)
          && equals(status, o.status);
    }

    @Override
    public int hashCode() {
      int result = hashCode;
      if (result == 0) {
        result = creditBankId != null ? creditBankId.hashCode() : 0;
        result = result * 37 + (creditBankName != null ? creditBankName.hashCode() : 0);
        result = result * 37 + (creditCardNo != null ? creditCardNo.hashCode() : 0);
        result = result * 37 + (amount != null ? amount.hashCode() : 0);
        result = result * 37 + (requestDate != null ? requestDate.hashCode() : 0);
        result = result * 37 + (status != null ? status.hashCode() : 0);
        hashCode = result;
      }
      return result;
    }

    public static final class Builder extends Message.Builder<Reimbersement> {

      public String creditBankId;
      public String creditBankName;
      public String creditCardNo;
      public String amount;
      public String requestDate;
      public String status;

      public Builder() {
      }

      public Builder(Reimbersement message) {
        super(message);
        if (message == null) return;
        this.creditBankId = message.creditBankId;
        this.creditBankName = message.creditBankName;
        this.creditCardNo = message.creditCardNo;
        this.amount = message.amount;
        this.requestDate = message.requestDate;
        this.status = message.status;
      }

      public Builder creditBankId(String creditBankId) {
        this.creditBankId = creditBankId;
        return this;
      }

      public Builder creditBankName(String creditBankName) {
        this.creditBankName = creditBankName;
        return this;
      }

      public Builder creditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
        return this;
      }

      public Builder amount(String amount) {
        this.amount = amount;
        return this;
      }

      public Builder requestDate(String requestDate) {
        this.requestDate = requestDate;
        return this;
      }

      public Builder status(String status) {
        this.status = status;
        return this;
      }

      @Override
      public Reimbersement build() {
        return new Reimbersement(this);
      }
    }
  }

  public static final class MsgContent extends Message {
    private static final long serialVersionUID = 0L;

    public static final String DEFAULT_PASSWORD = "";
    public static final String DEFAULT_USERMEBCODE = "";
    public static final List<Reimbersement> DEFAULT_REIMBERSEMENTLIST = Collections.emptyList();

    @ProtoField(tag = 1, type = STRING)
    public final String password;

    @ProtoField(tag = 2, type = STRING)
    public final String userMebCode;

    @ProtoField(tag = 3, label = REPEATED, messageType = Reimbersement.class)
    public final List<Reimbersement> reimbersementList;

    public MsgContent(String password, String userMebCode, List<Reimbersement> reimbersementList) {
      this.password = password;
      this.userMebCode = userMebCode;
      this.reimbersementList = immutableCopyOf(reimbersementList);
    }

    private MsgContent(Builder builder) {
      this(builder.password, builder.userMebCode, builder.reimbersementList);
      setBuilder(builder);
    }

    @Override
    public boolean equals(Object other) {
      if (other == this) return true;
      if (!(other instanceof MsgContent)) return false;
      MsgContent o = (MsgContent) other;
      return equals(password, o.password)
          && equals(userMebCode, o.userMebCode)
          && equals(reimbersementList, o.reimbersementList);
    }

    @Override
    public int hashCode() {
      int result = hashCode;
      if (result == 0) {
        result = password != null ? password.hashCode() : 0;
        result = result * 37 + (userMebCode != null ? userMebCode.hashCode() : 0);
        result = result * 37 + (reimbersementList != null ? reimbersementList.hashCode() : 1);
        hashCode = result;
      }
      return result;
    }

    public static final class Builder extends Message.Builder<MsgContent> {

      public String password;
      public String userMebCode;
      public List<Reimbersement> reimbersementList;

      public Builder() {
      }

      public Builder(MsgContent message) {
        super(message);
        if (message == null) return;
        this.password = message.password;
        this.userMebCode = message.userMebCode;
        this.reimbersementList = copyOf(message.reimbersementList);
      }

      public Builder password(String password) {
        this.password = password;
        return this;
      }

      public Builder userMebCode(String userMebCode) {
        this.userMebCode = userMebCode;
        return this;
      }

      public Builder reimbersementList(List<Reimbersement> reimbersementList) {
        this.reimbersementList = checkForNulls(reimbersementList);
        return this;
      }

      @Override
      public MsgContent build() {
        return new MsgContent(this);
      }
    }
  }
}
